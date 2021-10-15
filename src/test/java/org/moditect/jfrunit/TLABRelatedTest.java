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

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.moditect.jfrunit.events.ObjectAllocationInNewTLAB;
import org.moditect.jfrunit.events.ObjectAllocationOutsideTLAB;

import jdk.jfr.consumer.RecordedEvent;

import static org.moditect.jfrunit.ExpectedEvent.event;
import static org.moditect.jfrunit.JfrEventsAssert.assertThat;

@JfrEventTest
public class TLABRelatedTest {
    private static final int BYTE_ARRAY_OVERHEAD = 16;
    private static final int OBJECT_SIZE = 102400;
    public static byte[] tmp;
    public JfrEvents jfrEvents = new JfrEvents();

    @Test
    @EnableEvent("jdk.ObjectAllocationOutsideTLAB")
    @EnableEvent("jdk.ObjectAllocationInNewTLAB")
    public void testSlowAllocation() throws InterruptedException {
        System.gc();
        for (int i = 0; i < 512; ++i) {
            tmp = new byte[OBJECT_SIZE - BYTE_ARRAY_OVERHEAD];
        }
        jfrEvents.awaitEvents();

        StackTraceElement[] elements = new Exception().getStackTrace();

        assertThat(jfrEvents).contains(event("jdk.ObjectAllocationOutsideTLAB"));
        assertThat(jfrEvents).contains(event("jdk.ObjectAllocationInNewTLAB"));

        List<RecordedEvent> allocation100KBInNewTLABEvents = jfrEvents.filter(event("jdk.ObjectAllocationInNewTLAB")
                .with(ObjectAllocationInNewTLAB.ALLOCATION_SIZE, (long) OBJECT_SIZE)
                .with(ObjectAllocationInNewTLAB.OBJECT_CLASS, new ExpectedClass(byte[].class))
                .with(ObjectAllocationInNewTLAB.EVENT_THREAD, new ExpectedThread(Thread.currentThread()))
                .containStackFrame(new ExpectedStackFrame(elements[0]))).collect(Collectors.toList());
        List<RecordedEvent> allocation100KBOutsideTLABEvents = jfrEvents.filter(event("jdk.ObjectAllocationOutsideTLAB")
                .with(ObjectAllocationOutsideTLAB.ALLOCATION_SIZE, (long) OBJECT_SIZE)
                .with(ObjectAllocationOutsideTLAB.OBJECT_CLASS, new ExpectedClass(byte[].class))
                .with(ObjectAllocationOutsideTLAB.EVENT_THREAD, new ExpectedThread(Thread.currentThread()))
                .containStackFrame(new ExpectedStackFrame(elements[0]))).collect(Collectors.toList());
        Assertions.assertThat(allocation100KBInNewTLABEvents.size()).isGreaterThan(0);
        Assertions.assertThat(allocation100KBOutsideTLABEvents.size()).isGreaterThan(0);
    }
}
