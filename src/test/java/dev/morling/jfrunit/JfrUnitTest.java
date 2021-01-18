/**
 * Copyright 2020 The JfrUnit authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.morling.jfrunit;

import org.junit.jupiter.api.Test;

import java.time.Duration;

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

    @Test
    @EnableEvent("jdk.ThreadSleep")
    public void shouldHaveStackTraceCapturedWithNoStackTracePolicyDefined() throws Exception {
        Thread.sleep(1000);

        jfrEvents.awaitEvents();

        assertThat(jfrEvents).contains(event("jdk.ThreadSleep").has("stackTrace"));
    }

    @Test
    @EnableEvent(value = "jdk.ThreadSleep", stackTrace = EnableEvent.StacktracePolicy.INCLUDED)
    public void shouldHaveStackTraceCapturedWithStackTracePolicyIncluded() throws Exception {
        Thread.sleep(1000);

        jfrEvents.awaitEvents();

        assertThat(jfrEvents).contains(event("jdk.ThreadSleep").has("stackTrace"));
    }

    @Test
    @EnableEvent(value = "jdk.ThreadSleep", stackTrace = EnableEvent.StacktracePolicy.EXCLUDED)
    public void shouldNotHaveStackTraceCapturedWithStackTracePolicyExcluded() throws Exception {
        Thread.sleep(1000);

        jfrEvents.awaitEvents();

        assertThat(jfrEvents).contains(event("jdk.ThreadSleep").hasNot("stackTrace"));
    }
}
