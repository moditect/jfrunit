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
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Predicate;

import jdk.jfr.consumer.RecordedEvent;

public class ExpectedEvent implements Predicate<RecordedEvent> {

    private String name;
    private Map<String, Object> props = new HashMap<>();

    private ExpectedEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<String, Object> getProps() {
        return props;
    }

    public ExpectedEvent with(String name, Object value) {
        this.props.put(name, value);
        return this;
    }

    public static ExpectedEvent event(String name) {
        return new ExpectedEvent(name);
    }

    /* default */ static boolean matches(ExpectedEvent expectedEvent, RecordedEvent recordedEvent) {
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

    private static boolean hasMatchingProperty(RecordedEvent recordedEvent, String name, Object value) {
        if (!recordedEvent.hasField(name)) {
            return false;
        }

        // TODO all event attribute types

        if (value instanceof Duration) {
            return Objects.equals(recordedEvent.getDuration(name), value);
        }
        else if (value instanceof String) {
            return Objects.equals(recordedEvent.getString(name), value);
        }
        else if (value instanceof Boolean) {
            return Objects.equals(recordedEvent.getBoolean(name), value);
        }
        else if (value instanceof Boolean) {
            return Objects.equals(recordedEvent.getByte(name), value);
        }
        else if (value instanceof Character) {
            return Objects.equals(recordedEvent.getChar(name), value);
        }
        else if (value instanceof Double) {
            return Objects.equals(recordedEvent.getDouble(name), value);
        }
        else if (value instanceof Float) {
            return Objects.equals(recordedEvent.getFloat(name), value);
        }
        else if (value instanceof Integer) {
            return Objects.equals(recordedEvent.getInt(name), value);
        }
        else if (value instanceof Long) {
            return Objects.equals(recordedEvent.getLong(name), value);
        }
        else if (value instanceof Short) {
            return Objects.equals(recordedEvent.getShort(name), value);
        }
        else if (value instanceof Instant) {
            return Objects.equals(recordedEvent.getInstant(name), value);
        }
        else {
            throw new IllegalArgumentException(String.format("Unsupported property type: %s, %s", name, value));
        }
    }

    @Override
    public boolean test(RecordedEvent recordedEvent) {
        return matches(this, recordedEvent);
    }
}
