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
package dev.morling.jfrunit;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import dev.morling.jfrunit.EnableEvent.StacktracePolicy;
import dev.morling.jfrunit.internal.SyncEvent;
import jdk.jfr.Configuration;
import jdk.jfr.EventSettings;
import jdk.jfr.EventType;
import jdk.jfr.FlightRecorder;
import jdk.jfr.Recording;
import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.consumer.RecordingStream;

public class JfrEvents {

    private static final Logger LOGGER = System.getLogger(JfrEvents.class.getName());

    private Method testMethod;
    private Queue<RecordedEvent> events = new ConcurrentLinkedQueue<>();
    private AtomicLong sequence = new AtomicLong();
    private AtomicLong watermark = new AtomicLong();
    private RecordingStream stream;
    private Recording recording;

    public JfrEvents() {
    }

    void startRecordingEvents(String configurationName, List<EventConfiguration> enabledEvents, Method testMethod) {
        if (configurationName != null && !enabledEvents.isEmpty()) {
            throw new IllegalArgumentException("Either @EnableConfiguration or @EnableEvent may be given, but not both at the same time");
        }

        LOGGER.log(Level.INFO, "Starting recording");

        CountDownLatch streamStarted = new CountDownLatch(1);

        List<EventConfiguration> allEnabledEventTypes = matchEventTypes(enabledEvents);

        try {
            this.testMethod = testMethod;
            stream = startRecordingStream(configurationName, allEnabledEventTypes, streamStarted);
            recording = startRecording(configurationName, allEnabledEventTypes);

            awaitStreamStart(streamStarted);
            LOGGER.log(Level.INFO, "Event stream started");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void stopRecordingEvents() {
        try {
            Path dumpDir = Files.createDirectories(Path.of(testMethod.getDeclaringClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParent().resolve("jfrunit"));
            LOGGER.log(Level.INFO, "Stop recording: " + dumpDir.resolve(testMethod.getDeclaringClass().getName() + "-" + testMethod.getName() + ".jfr"));
            recording.stop();
            recording.dump(dumpDir.resolve(testMethod.getDeclaringClass().getName() + "-" + testMethod.getName() + ".jfr"));
            recording.close();

            stream.close();
        }
        catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
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
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void reset() {
        awaitEvents();
        events.clear();
    }

    public Stream<RecordedEvent> getEvents() {
        return events.stream();
    }

    public Stream<RecordedEvent> filter(Predicate<RecordedEvent> predicate) {
        return events.stream().filter(predicate);
    }

    public Stream<RecordedEvent> stream() {
        return events.stream();
    }

    private void awaitStreamStart(CountDownLatch streamStarted) throws InterruptedException {
        while(streamStarted.getCount() != 0) {
            SyncEvent event = new SyncEvent();
            event.sequence = sequence.incrementAndGet();
            event.cause = "awaiting stream start";
            event.begin();
            event.commit();
            Thread.sleep(100);
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
                re.getDuration("time").equals(Duration.ofMillis(100));
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
}
