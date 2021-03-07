/**
 *  Copyright 2020 - 2021 The JfrUnit authors
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

import jdk.jfr.consumer.RecordedEvent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static dev.morling.jfrunit.ExpectedEvent.event;
import static dev.morling.jfrunit.JfrEventsAssert.assertThat;

@JfrEventTest
public class TestTLABRelated {
    public JfrEvents jfrEvents = new JfrEvents();

    private static final int BYTE_ARRAY_OVERHEAD = 16;
    private static final int OBJECT_SIZE = 102400;
    private static final String BYTE_ARRAY_CLASS_NAME = byte[].class.getName();
    public static byte[] tmp;

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
                .with("allocationSize", (double) OBJECT_SIZE)
                .with("objectClass", new ExpectedClass(byte[].class))
                .with("eventThread", new ExpectedThread(Thread.currentThread()))
                .containStackFrame(new ExpectedStackFrame(elements[0]))
        ).collect(Collectors.toList());
        List<RecordedEvent> allocation100KBOutsideTLABEvents = jfrEvents.filter(event("jdk.ObjectAllocationOutsideTLAB")
                .with("allocationSize", (double) OBJECT_SIZE)
                .with("objectClass", new ExpectedClass(byte[].class))
                .with("eventThread", new ExpectedThread(Thread.currentThread()))
                .containStackFrame(new ExpectedStackFrame(elements[0]))
        ).collect(Collectors.toList());
        Assertions.assertThat(allocation100KBInNewTLABEvents.size()).isGreaterThan(0);
        Assertions.assertThat(allocation100KBOutsideTLABEvents.size()).isGreaterThan(0);
    }
}
