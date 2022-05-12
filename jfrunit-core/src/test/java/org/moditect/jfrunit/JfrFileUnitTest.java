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

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Percentage;
import org.moditect.jfrunit.events.JfrEventTypes;

import static org.moditect.jfrunit.JfrEventsAssert.assertThat;

@JfrEventTest
public class JfrFileUnitTest {

    public JfrEvents jfrEvents =
            new JfrFileEvents(Paths.get("src/test/resources/rest-post-consumer-0f08.jfr")); // 1st execution
    //new JfrFileEvents(Paths.get("src/test/resources/rest-post-consumer-1d3e.jfr")); // 2nd execution

    double jvmUserAverageBaseline = 3.9898221101611853;
    double jvmSystemAverageBaseline = 0.7211646879754531;
    double mbHeapUsedAverageBaseline = 42.28000774383545;

    Percentage percentageThreshold = Percentage.withPercentage(5);

    @Test
    public void shouldHaveGcAndSleepEvents() throws Exception {
        assertThat(jfrEvents).contains(JfrEventTypes.GARBAGE_COLLECTION);

        // PRINT EVENT COUNT IN JFR
//        System.out.println("EVENTS -> " + jfrEvents.stream().count());

        // PRINT DISTINCT EVENT TYPE NAME
//         jfrEvents.stream().map(recordedEvent -> recordedEvent.getEventType().getName()).distinct().forEach(s -> System.out.println(s));

        List<Float> jvmUserCpuLoad = new ArrayList<>();
        List<Float> jvmSystemCpuLoad = new ArrayList<>();

        List<Double> heapUsed = jfrEvents.filter(JfrEventTypes.GC_HEAP_SUMMARY)
                .map(recordedEvent -> getMB(recordedEvent.getLong("heapUsed")))
                .collect(Collectors.toList());

        jfrEvents.filter(JfrEventTypes.THREAD_CPU_LOAD)
                .forEach(recordedEvent -> {
                    jvmSystemCpuLoad.add(getPercentage(recordedEvent.getFloat("system")));
                    jvmUserCpuLoad.add(getPercentage(recordedEvent.getFloat("user")));
                });

        System.out.println("JVM USER AVERAGE -> " + jvmUserCpuLoad.stream().mapToDouble(d -> d).average().getAsDouble());
        System.out.println("JVM SYSTEM AVERAGE -> " + jvmSystemCpuLoad.stream().mapToDouble(d -> d).average().getAsDouble());
        System.out.println("MB HEAP USED AVERAGE -> " + heapUsed.stream().mapToDouble(d -> d).average().getAsDouble());

        Assertions
                .assertThat(jvmUserCpuLoad.stream().mapToDouble(d -> d).average().getAsDouble())
                .isCloseTo(jvmUserAverageBaseline, percentageThreshold);

        Assertions
                .assertThat(jvmSystemCpuLoad.stream().mapToDouble(d -> d).average().getAsDouble())
                .isCloseTo(jvmSystemAverageBaseline, percentageThreshold);

        Assertions
                .assertThat(heapUsed.stream().mapToDouble(d -> d).average().getAsDouble())
                .isCloseTo(mbHeapUsedAverageBaseline, percentageThreshold);
    }

    public static double getMB(long amount) {
        int exp = (int) (Math.log(Math.abs(amount)) / Math.log(1024));
        return amount / Math.pow(1024, exp);
    }

    public static float getPercentage(float amount) {
        return amount * 100;
    }
}
