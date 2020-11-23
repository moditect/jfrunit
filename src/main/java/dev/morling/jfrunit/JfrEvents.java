/**
 *  Copyright 2020 The JfrUnit authors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package dev.morling.jfrunit;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.platform.commons.support.AnnotationSupport;

import dev.morling.jfrunit.internal.SyncEvent;
import jdk.jfr.Recording;
import jdk.jfr.consumer.EventStream;
import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.consumer.RecordingStream;

public class JfrEvents implements Extension, BeforeEachCallback, AfterEachCallback {

    private static final Namespace NAMESPACE = Namespace.create(JfrEvents.class);
    private static final String EVENT_STREAM = "EVENT_STREAM";
    private static final String RECORDING = "RECORDING";

    private Queue<RecordedEvent> events = new ConcurrentLinkedQueue<>();
    private AtomicLong sequence = new AtomicLong();
    private AtomicLong watermark = new AtomicLong();

    public Queue<RecordedEvent> getEvents() {
        return events;
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        List<EnableEvent> enabledEvents = AnnotationSupport.findRepeatableAnnotations(context.getRequiredTestMethod(), EnableEvent.class);
        CountDownLatch streamStarted = new CountDownLatch(1);

        RecordingStream stream = startRecordingStream(enabledEvents, streamStarted);
        Recording recording = startRecording(enabledEvents);
        awaitStreamStart(streamStarted);

        context.getStore(NAMESPACE).put(RECORDING, recording);
        context.getStore(NAMESPACE).put(EVENT_STREAM, stream);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Path dumpDir = Files.createDirectories(Path.of(context.getRequiredTestClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParent().resolve("jfrunit"));

        Recording recording = context.getStore(NAMESPACE).get(RECORDING, Recording.class);
        recording.stop();
        recording.dump(dumpDir.resolve(context.getRequiredTestClass().getName() + "-" + context.getRequiredTestMethod().getName() + ".jfr"));
        recording.close();

        EventStream stream = context.getStore(NAMESPACE).get(EVENT_STREAM, EventStream.class);
        stream.close();
    }

    /**
     * Ensures all previously emitted events have been consumed.
     */
    public void awaitEvents() {
        SyncEvent event = new SyncEvent();
        event.begin();
        long seq = sequence.incrementAndGet();
        event.sequence = seq;
        event.commit();

        while (watermark.get() < seq) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void awaitStreamStart(CountDownLatch streamStarted) throws InterruptedException {
        while(streamStarted.getCount() != 0) {
            SyncEvent event = new SyncEvent();
            long seq = sequence.incrementAndGet();
            event.sequence = seq;
            event.begin();
            event.commit();
            Thread.sleep(100);
        }
    }

    private Recording startRecording(List<EnableEvent> enabledEvents) {
        Recording recording = new Recording();
        recording.enable(SyncEvent.JFRUNIT_SYNC_EVENT_NAME);

        for (EnableEvent enableEvent : enabledEvents) {
            recording.enable(enableEvent.value());
        }
        recording.start();
        return recording;
    }

    private RecordingStream startRecordingStream(List<EnableEvent> enabledEvents, CountDownLatch streamStarted) {
        RecordingStream stream = new RecordingStream();
        stream.enable(SyncEvent.JFRUNIT_SYNC_EVENT_NAME);
        for (EnableEvent enableEvent : enabledEvents) {
            stream.enable(enableEvent.value());
        }

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
}
