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

import static dev.morling.jfrunit.ExpectedEvent.event;
import static dev.morling.jfrunit.JfrEventsAssert.assertThat;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class TestSuperclass {
    public JfrEvents jfrEvents = new JfrEvents();
}

@JfrEventTest
public class TestSubclassTest extends TestSuperclass {

    @Test
    @EnableEvent("jfrunit.test.StackTraceDisabledSampleEvent")
    @DisplayName(
        "Can detect a submitted event when instantiated as a subclass of the test class holding the JfrEvents field")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void simple() {
        StackTraceDisabledSampleEvent event = new StackTraceDisabledSampleEvent();
        event.commit();

        jfrEvents.awaitEvents();

        assertThat(jfrEvents).contains(event("jfrunit.test.StackTraceDisabledSampleEvent"));
    }
}
