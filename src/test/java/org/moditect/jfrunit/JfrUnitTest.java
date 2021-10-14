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

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.moditect.jfrunit.events.GarbageCollection;
import org.moditect.jfrunit.events.ThreadSleep;

import static org.assertj.core.api.Assertions.assertThat;
import static org.moditect.jfrunit.ExpectedEvent.event;
import static org.moditect.jfrunit.JfrEventsAssert.assertThat;

@JfrEventTest
public class JfrUnitTest {

    public JfrEvents jfrEvents = new JfrEvents();

    @Test
    @EnableEvent(GarbageCollection.EVENT_NAME)
    @EnableEvent(ThreadSleep.EVENT_NAME)
    @DisplayName("Should have Gc and Sleep events recorded when explicitly enabled individually with @EnableEvent")
    public void shouldHaveGcAndSleepEvents() throws Exception {
        System.gc();
        Thread.sleep(50);

        jfrEvents.awaitEvents();

        assertThat(jfrEvents).contains(event(GarbageCollection.EVENT_NAME));
        assertThat(jfrEvents).contains(
                event(GarbageCollection.EVENT_NAME).with(GarbageCollection.ATTRIBUTE_CAUSE_NAME, "System.gc()"));
        assertThat(jfrEvents).contains(
                event(ThreadSleep.EVENT_NAME).with(ThreadSleep.ATTRIBUTE_TIME_NAME, Duration.ofMillis(50)));

        assertThat(jfrEvents.filter(event(GarbageCollection.EVENT_NAME))).hasSize(1);
    }

    @Test
    @EnableConfiguration("profile")
    @DisplayName("Should have Gc and Sleep events recorded when enabled with configuration 'profile'")
    public void shouldHaveGcAndSleepEventsWithDefaultConfiguration() throws Exception {
        System.gc();
        Thread.sleep(50);

        jfrEvents.awaitEvents();

        assertThat(jfrEvents).contains(event("jdk.GarbageCollection"));
        assertThat(jfrEvents).contains(
                event("jdk.GarbageCollection").with("cause", "System.gc()"));
        assertThat(jfrEvents).contains(
                event("jdk.ThreadSleep").with("time", Duration.ofMillis(50)));

        assertThat(jfrEvents.filter(
                event("jdk.GarbageCollection").with("cause", "System.gc()")))
                        .hasSize(1);
    }

    @Test
    @EnableEvent("jdk.ThreadSleep")
    @DisplayName("Should have StackTrace captured for StackTrace-Enabled Events by default with StackTrace policy Default")
    public void captureTracesWhenEnabledWithPolicyDefault() throws Exception {
        Thread.sleep(50);

        jfrEvents.awaitEvents();

        assertThat(jfrEvents).contains(event("jdk.ThreadSleep").has("stackTrace"));
    }

    @Test
    @EnableEvent("jfrunit.test.StackTraceDisabledSampleEvent")
    @DisplayName("Should not have StackTrace captured for StackTrace-Disabled Events by default with StackTrace policy Default")
    public void doNotCaptureTracesWhenDisabledWithPolicyDefault() {
        StackTraceDisabledSampleEvent event = new StackTraceDisabledSampleEvent();
        event.commit();

        jfrEvents.awaitEvents();

        assertThat(jfrEvents).contains(event("jfrunit.test.StackTraceDisabledSampleEvent").hasNot("stackTrace"));
    }

    @Test
    @EnableEvent(value = "jdk.ThreadSleep", stackTrace = EnableEvent.StacktracePolicy.INCLUDED)
    @DisplayName("Should have StackTrace captured irrespective of Event StackTrace Configuration(Enabled) with StackTrace policy Included")
    public void captureTraceWhenEnabledWithStackTracePolicyIncluded() throws Exception {
        Thread.sleep(50);

        jfrEvents.awaitEvents();

        assertThat(jfrEvents).contains(event("jdk.ThreadSleep").has("stackTrace"));
    }

    @Test
    @EnableEvent(value = "jfrunit.test.StackTraceDisabledSampleEvent", stackTrace = EnableEvent.StacktracePolicy.INCLUDED)
    @DisplayName("Should have StackTrace captured irrespective of Event StackTrace Configuration(Disabled) with StackTrace policy Included")
    public void captureTraceWhenDisabledWithStackTracePolicyIncluded() {
        StackTraceDisabledSampleEvent event = new StackTraceDisabledSampleEvent();
        event.commit();

        jfrEvents.awaitEvents();

        assertThat(jfrEvents).contains(event("jfrunit.test.StackTraceDisabledSampleEvent").has("stackTrace"));
    }

    @Test
    @EnableEvent(value = "jdk.ThreadSleep", stackTrace = EnableEvent.StacktracePolicy.EXCLUDED)
    @DisplayName("Should not have StackTrace captured irrespective of Event StackTrace Configuration(Enabled) with StackTrace policy Excluded")
    public void doNotCaptureTraceWhenEnabledWithStackTracePolicyExcluded() throws Exception {
        Thread.sleep(50);

        jfrEvents.awaitEvents();

        assertThat(jfrEvents).contains(event("jdk.ThreadSleep").hasNot("stackTrace"));
    }

    @Test
    @EnableEvent(value = "jfrunit.test.StackTraceDisabledSampleEvent", stackTrace = EnableEvent.StacktracePolicy.EXCLUDED)
    @DisplayName("Should not have StackTrace captured irrespective of Event StackTrace Configuration(Disabled) with StackTrace policy Excluded")
    public void doNotCaptureTraceWhenDisabledWithStackTracePolicyExcluded() {
        StackTraceDisabledSampleEvent event = new StackTraceDisabledSampleEvent();
        event.commit();

        jfrEvents.awaitEvents();

        assertThat(jfrEvents).contains(event("jfrunit.test.StackTraceDisabledSampleEvent").hasNot("stackTrace"));
    }
}
