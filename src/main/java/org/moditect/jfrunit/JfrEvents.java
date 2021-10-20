/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2020 - 2021 The JfrUnit authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.moditect.jfrunit;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.moditect.jfrunit.EnableEvent.StacktracePolicy;
import org.moditect.jfrunit.internal.JmcAutomaticAnalysis;
import org.moditect.jfrunit.internal.SyncEvent;
import org.openjdk.jmc.flightrecorder.rules.IResult;
import org.openjdk.jmc.flightrecorder.rules.Severity;

import jdk.jfr.Configuration;
import jdk.jfr.EventSettings;
import jdk.jfr.EventType;
import jdk.jfr.FlightRecorder;
import jdk.jfr.Recording;
import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.consumer.RecordingStream;

public class JfrEvents {

    private static final Logger LOGGER = System.getLogger(JfrEvents.class.getName());

    private static final long INTERNAL_WAIT_TIME = 97;

    private Method testMethod;
    private String dumpFileName;
    private Queue<RecordedEvent> events = new ConcurrentLinkedQueue<>();
    private AtomicLong sequence = new AtomicLong();
    private AtomicLong watermark = new AtomicLong();
    private RecordingStream stream;
    private Recording recording;
    private boolean capturing;

    private AtomicInteger analysisCounter = new AtomicInteger(0);

    public JfrEvents() {
    }

    void startRecordingEvents(String configurationName, List<EventConfiguration> enabledEvents, Method testMethod, String dumpFileName) {
        if (configurationName != null && !enabledEvents.isEmpty()) {
            throw new IllegalArgumentException("Either @EnableConfiguration or @EnableEvent may be given, but not both at the same time");
        }

        LOGGER.log(Level.INFO, "Starting recording");

        CountDownLatch streamStarted = new CountDownLatch(1);

        List<EventConfiguration> allEnabledEventTypes = matchEventTypes(enabledEvents);

        try {
            this.testMethod = testMethod;
            this.dumpFileName = dumpFileName;
            stream = startRecordingStream(configurationName, allEnabledEventTypes, streamStarted);
            recording = startRecording(configurationName, allEnabledEventTypes);

            awaitStreamStart(streamStarted);
            capturing = true;
            LOGGER.log(Level.INFO, "Event stream started");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void stopRecordingEvents() {
        try {
            URI testSourceUri = testMethod.getDeclaringClass().getProtectionDomain().getCodeSource().getLocation().toURI();
            Path dumpDir;
            try {
                dumpDir = Files.createDirectories(Path.of(testSourceUri).getParent().resolve("jfrunit"));
            }
            catch (FileSystemNotFoundException e) {
                dumpDir = Files.createTempDirectory(null);
                LOGGER.log(Level.WARNING, "'" + testSourceUri.getScheme() + "' is not a valid file system, dumping recording to a temporary location.");
            }

            Path recordingPath = getRecordingFilePath();

            LOGGER.log(Level.INFO, "Stop recording: " + recordingPath);
            capturing = false;
            recording.stop();
            try {
                recording.dump(recordingPath);
            }
            catch (IOException ex) {
                LOGGER.log(Level.WARNING, "Could not dump to: " + recordingPath, ex);
                String defaultFileName = getDefaultDumpFileName();
                if (!defaultFileName.equals(recordingPath.getFileName().toString())) {
                    // perhaps the FS was not able to handle special characters
                    recordingPath = dumpDir.resolve(defaultFileName);
                    LOGGER.log(Level.INFO, "Retrying dump: " + recordingPath);
                    recording.dump(recordingPath);
                }
                else {
                    throw ex;
                }
            }
            recording.close();

            stream.close();
        }
        catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private Path getRecordingFilePath() throws URISyntaxException, IOException {
        return getRecordingFilePath(null);
    }

    private Path getRecordingFilePath(String suffix) throws URISyntaxException, IOException {
        URI testSourceUri = testMethod.getDeclaringClass().getProtectionDomain().getCodeSource().getLocation().toURI();
        Path dumpDir;
        try {
            dumpDir = Files.createDirectories(Path.of(testSourceUri).getParent().resolve("jfrunit"));

        }
        catch (FileSystemNotFoundException e) {
            dumpDir = Files.createTempDirectory(null);
            LOGGER.log(Level.WARNING, "'" + testSourceUri.getScheme() + "' is not a valid file system, dumping recording to a temporary location.");
        }
        String fileName = getDumpFileName(suffix);
        return dumpDir.resolve(fileName);
    }

    void dumpRecording(Path jfrPath) throws IOException {
        recording.dump(jfrPath);
    }

    /**
     * Ensures all previously emitted events have been consumed.
     */
    public void awaitEvents() {
        SyncEvent event = new SyncEvent();
        event.begin();
        long seq = sequence.incrementAndGet();
        event.sequence = seq;
        event.cause = "awaiting events";
        event.commit();

        while (watermark.get() < seq) {
            try {
                Thread.sleep(INTERNAL_WAIT_TIME);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void reset() {
        awaitEvents();
        events.clear();
    }

    public Stream<RecordedEvent> events() {
        return stream();
    }

    public Stream<RecordedEvent> filter(Predicate<RecordedEvent> predicate) {
        return stream().filter(predicate);
    }

    private Stream<RecordedEvent> stream() {
        // avoid blocking when called outside of a test such as new JfrEvents().stream()
        if (capturing) {
            awaitEvents();
        }
        return events.stream();
    }

    private void awaitStreamStart(CountDownLatch streamStarted) throws InterruptedException {
        while (streamStarted.getCount() != 0) {
            SyncEvent event = new SyncEvent();
            event.sequence = sequence.incrementAndGet();
            event.cause = "awaiting stream start";
            event.begin();
            event.commit();
            Thread.sleep(INTERNAL_WAIT_TIME);
        }
    }

    private Recording startRecording(String configurationName, List<EventConfiguration> enabledEvents) throws Exception {
        Recording recording;

        if (configurationName != null) {
            recording = new Recording(Configuration.getConfiguration(configurationName));
        }
        else {
            recording = new Recording();
            for (EventConfiguration enabledEvent : enabledEvents) {
                EventSettings settings = recording.enable(enabledEvent.name);
                if (enabledEvent.stackTrace == StacktracePolicy.INCLUDED) {
                    settings.withStackTrace();
                }
                else if (enabledEvent.stackTrace == StacktracePolicy.EXCLUDED) {
                    settings.withoutStackTrace();
                }

                if (enabledEvent.threshold != -1) {
                    settings.withThreshold(Duration.ofMillis(enabledEvent.threshold));
                }

                if (enabledEvent.period != -1) {
                    settings.withPeriod(Duration.ofMillis(enabledEvent.period));
                }
            }
        }

        recording.enable(SyncEvent.JFRUNIT_SYNC_EVENT_NAME);

        recording.start();
        return recording;
    }

    private RecordingStream startRecordingStream(String configurationName, List<EventConfiguration> enabledEvents, CountDownLatch streamStarted) throws Exception {
        RecordingStream stream;

        if (configurationName != null) {
            stream = new RecordingStream(Configuration.getConfiguration(configurationName));
        }
        else {
            stream = new RecordingStream();
            for (EventConfiguration enabledEvent : enabledEvents) {
                EventSettings settings = stream.enable(enabledEvent.name);
                if (enabledEvent.stackTrace == StacktracePolicy.INCLUDED) {
                    settings.withStackTrace();
                }
                else if (enabledEvent.stackTrace == StacktracePolicy.EXCLUDED) {
                    settings.withoutStackTrace();
                }

                if (enabledEvent.threshold != -1) {
                    settings.withThreshold(Duration.ofMillis(enabledEvent.threshold));
                }
            }
        }

        // we need this as we keep reference to events - otherwise they could be changed after the capture
        // see RecordingStream#setReuse (the default is true)
        stream.setReuse(false);
        stream.enable(SyncEvent.JFRUNIT_SYNC_EVENT_NAME);

        stream.onEvent(re -> {
            if (isSyncEvent(re)) {
                watermark.set(re.getLong("sequence"));
                streamStarted.countDown();
            }
            else if (!isInternalSleepEvent(re)) {
                events.add(re);
            }
        });

        stream.startAsync();
        return stream;
    }

    private boolean isSyncEvent(RecordedEvent re) {
        return re.getEventType().getName().equals(SyncEvent.JFRUNIT_SYNC_EVENT_NAME);
    }

    private boolean isInternalSleepEvent(RecordedEvent re) {
        return re.getEventType().getName().equals("jdk.ThreadSleep") &&
                re.getDuration("time").equals(Duration.ofMillis(INTERNAL_WAIT_TIME));
    }

    private List<EventConfiguration> matchEventTypes(List<EventConfiguration> enabledEvents) {
        List<EventConfiguration> allEvents = new ArrayList<>();
        List<EventType> allEventTypes = FlightRecorder.getFlightRecorder().getEventTypes();

        for (EventConfiguration event : enabledEvents) {
            if (event.name.contains("*")) {
                Pattern pattern = Pattern.compile(event.name.replace("*", ".*"));
                for (EventType eventType : allEventTypes) {
                    if (pattern.matcher(eventType.getName()).matches()) {
                        allEvents.add(new EventConfiguration(eventType.getName(), event.stackTrace, event.threshold, event.period));
                    }
                }
            }
            else {
                allEvents.add(event);
            }
        }

        return allEvents;
    }

    private String getDumpFileName(String suffix) {
        if (dumpFileName == null) {
            return getDefaultDumpFileName(suffix);
        }
        else {
            return dumpFileName.endsWith(".jfr") ? dumpFileName : dumpFileName + ".jfr";
        }
    }

    private String getDefaultDumpFileName() {
        return getDefaultDumpFileName(null);
    }

    private String getDefaultDumpFileName(String suffix) {
        return testMethod.getDeclaringClass().getName() + "-" + testMethod.getName() + (suffix != null ? "-" + suffix : "") + ".jfr";
    }

    // TODO: Do we move out of JfrEvents?
    public List<IResult> automaticAnalysis() {
        try {
            awaitEvents();

            int counter = analysisCounter.getAndIncrement();
            Path recordingPath = getRecordingFilePath("analysis" + (counter != 0 ? "-" + counter : ""));

            LOGGER.log(Level.INFO, "Analysis recording: " + recordingPath.toAbsolutePath());
            dumpRecording(recordingPath);

            return JmcAutomaticAnalysis.analysisRecording(recordingPath.toAbsolutePath().toString(), Severity.INFO);

        }
        catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Unable to analyse jfr recording", e);
        }
    }
}
