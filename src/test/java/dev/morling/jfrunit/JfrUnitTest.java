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

import jdk.jfr.consumer.RecordedEvent;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static dev.morling.jfrunit.ExpectedEvent.event;
import static dev.morling.jfrunit.JfrEventsAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

@JfrEventTest
public class JfrUnitTest {

    public JfrEvents jfrEvents = new JfrEvents();

    @Test
    @EnableEvent("jdk.GarbageCollection")
    @EnableEvent("jdk.ThreadSleep")
    public void shouldHaveGcAndSleepEvents() throws Exception {
        System.gc();
        Thread.sleep(1000);

        jfrEvents.awaitEvents();

        assertThat(jfrEvents).contains(event("jdk.GarbageCollection"));
        assertThat(jfrEvents).contains(
                event("jdk.GarbageCollection").with("cause", "System.gc()"));
        assertThat(jfrEvents).contains(
                event("jdk.ThreadSleep").with("time", Duration.ofSeconds(1)));

        assertThat(jfrEvents.filter(event("jdk.GarbageCollection"))).hasSize(1);
    }

    @Test
    @EnableConfiguration("profile")
    public void shouldHaveGcAndSleepEventsWithDefaultConfiguration() throws Exception {
        System.gc();
        Thread.sleep(1000);

        jfrEvents.awaitEvents();

        assertThat(jfrEvents).contains(event("jdk.GarbageCollection"));
        assertThat(jfrEvents).contains(
                event("jdk.GarbageCollection").with("cause", "System.gc()"));
        assertThat(jfrEvents).contains(
                event("jdk.ThreadSleep").with("time", Duration.ofSeconds(1)));

        assertThat(jfrEvents.filter(
                event("jdk.GarbageCollection").with("cause", "System.gc()")))
                .hasSize(1);

        long allocated = jfrEvents.filter(event("jdk.ObjectAllocationInNewTLAB"))
                .mapToLong(e -> e.getLong("tlabSize"))
                .sum();

        assertThat(allocated).isGreaterThan(0);
    }

    private static final int BYTE_ARRAY_OVERHEAD = 16;
    private static final int OBJECT_SIZE = 102400;
    private static final String BYTE_ARRAY_CLASS_NAME = new byte[0].getClass().getName();
    public static byte[] tmp;

    @Test
    @EnableEvent("jdk.ObjectAllocationOutsideTLAB")
    @EnableEvent("jdk.ObjectAllocationInNewTLAB")
    public void testTLAB() throws InterruptedException {
        System.gc();
        Thread.sleep(1000);
        for (int i = 0; i < 512; ++i) {
            tmp = new byte[OBJECT_SIZE - BYTE_ARRAY_OVERHEAD];
        }
        jfrEvents.awaitEvents();
        assertThat(jfrEvents).contains(event("jdk.ObjectAllocationOutsideTLAB"));
        assertThat(jfrEvents).contains(event("jdk.ObjectAllocationInNewTLAB"));
        List<RecordedEvent> allocation100KBInNewTLABEvents = jfrEvents.filter(event("jdk.ObjectAllocationInNewTLAB")
                .with("allocationSize", (double) OBJECT_SIZE)
                .with("objectClass", BYTE_ARRAY_CLASS_NAME)
        ).collect(Collectors.toList());
        List<RecordedEvent> allocation100KBOutsideTLABEvents = jfrEvents.filter(event("jdk.ObjectAllocationOutsideTLAB")
                .with("allocationSize", (double) OBJECT_SIZE)
                .with("objectClass", BYTE_ARRAY_CLASS_NAME)
        ).collect(Collectors.toList());
        assertThat(allocation100KBInNewTLABEvents.size()).isGreaterThan(0);
        assertThat(allocation100KBInNewTLABEvents.size()).isGreaterThan(0);
        RecordedEvent allocation100KBInNewTLABEvent = allocation100KBInNewTLABEvents.get(0);
        double tlabSize = allocation100KBInNewTLABEvent.getDouble("tlabSize");
        allocation100KBInNewTLABEvent.getThread().getId();

    }
}
