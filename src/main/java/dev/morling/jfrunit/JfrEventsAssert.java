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

import java.time.Duration;
import java.util.Map.Entry;

import org.assertj.core.api.AbstractAssert;

import jdk.jfr.consumer.RecordedEvent;

public class JfrEventsAssert extends AbstractAssert<JfrEventsAssert, JfrEvents> {

    public JfrEventsAssert(JfrEvents actual) {
        super(actual, JfrEventsAssert.class);
    }

    public static JfrEventsAssert assertThat(JfrEvents actual) {
        return new JfrEventsAssert(actual);
    }

    public JfrEventsAssert contains(ExpectedEvent expectedEvent) {
        isNotNull();

        boolean found = false;
        for (RecordedEvent recordedEvent : actual.getEvents()) {
            found = matches(expectedEvent, recordedEvent);
            if (found) {
                break;
            }
        }

        if (!found) {
            if (expectedEvent.getProps().isEmpty()) {
                failWithMessage("No JFR event of type <%s>", expectedEvent.getName());
            }
            else {
                failWithMessage("No JFR event of type <%s> with attributes <%s>", expectedEvent.getName(), expectedEvent.getProps());
            }
        }

        return this;
    }

    private boolean matches(ExpectedEvent expectedEvent, RecordedEvent recordedEvent) {
        if (!recordedEvent.getEventType().getName().equals(expectedEvent.getName())) {
            return false;
        }

        if (!expectedEvent.getProps().isEmpty()) {
            for (Entry<String, Object> prop : expectedEvent.getProps().entrySet()) {
                if (!hasMatchingProperty(recordedEvent, prop.getKey(), prop.getValue())) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean hasMatchingProperty(RecordedEvent recordedEvent, String name, Object value) {
        if (!recordedEvent.hasField(name)) {
            return false;
        }

        // TODO all event attribute types

        if (value instanceof Duration) {
            return recordedEvent.getDuration(name).equals(value);
        }
        else if (value instanceof String) {
            return recordedEvent.getString(name).equals(value);
        }
        else {
            throw new IllegalArgumentException(String.format("Unsupported property type: %s, %s", name, value));
        }
    }

    public static ExpectedEvent event(String name) {
        return new ExpectedEvent(name);
    }
}
