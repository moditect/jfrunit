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

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.moditect.jfrunit.events.ThreadSleep;

import jdk.jfr.consumer.RecordedEvent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.moditect.jfrunit.ExpectedEvent.event;

@JfrEventTest
public class ThreadSleepTest {
    public JfrEvents jfrEvents = new JfrEvents();

    @Test
    @EnableEvent(value = "jdk.ThreadSleep", stackTrace = EnableEvent.StacktracePolicy.INCLUDED)
    public void testWithStackTrace() throws Exception {
        Thread.sleep(10);

        jfrEvents.awaitEvents();

        StackTraceElement[] elements = new Exception().getStackTrace();

        List<RecordedEvent> threadSleepEvents = jfrEvents.filter(
                event("jdk.ThreadSleep")
                        .with(ThreadSleep.EVENT_THREAD, new ExpectedThread(Thread.currentThread()))
                        .containStackFrame(new ExpectedStackFrame(elements[0])))
                .collect(Collectors.toList());
        assertThat(threadSleepEvents.size()).isEqualTo(1);
        assertThat(threadSleepEvents.get(0).getStackTrace() != null);
    }

    @Test
    @EnableEvent(value = "jdk.ThreadSleep", stackTrace = EnableEvent.StacktracePolicy.EXCLUDED)
    public void testWithoutStackTrace() throws Exception {
        Thread.sleep(10);

        jfrEvents.awaitEvents();

        List<RecordedEvent> threadSleepEvents = jfrEvents.filter(
                event("jdk.ThreadSleep")
                        .with(ThreadSleep.EVENT_THREAD, new ExpectedThread(Thread.currentThread())))
                .collect(Collectors.toList());
        assertThat(threadSleepEvents.size()).isEqualTo(1);
        assertThat(threadSleepEvents.get(0).getStackTrace() == null);
    }

    @Test
    @EnableEvent(value = "jdk.ThreadSleep", threshold = 100)
    public void testWithThreshold() throws Exception {
        Thread.sleep(10);
        Thread.sleep(200);

        jfrEvents.awaitEvents();

        StackTraceElement[] elements = new Exception().getStackTrace();

        List<RecordedEvent> threadSleepEvents = jfrEvents.filter(
                event("jdk.ThreadSleep")
                        .with(ThreadSleep.EVENT_THREAD, new ExpectedThread(Thread.currentThread()))
                        .containStackFrame(new ExpectedStackFrame(elements[0])))
                .collect(Collectors.toList());
        assertThat(threadSleepEvents.size()).isEqualTo(1);
        assertThat(threadSleepEvents.get(0).getStackTrace() != null);
    }
}
