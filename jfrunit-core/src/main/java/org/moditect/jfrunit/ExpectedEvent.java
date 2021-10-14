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
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.consumer.RecordedFrame;
import jdk.jfr.consumer.RecordedStackTrace;

public class ExpectedEvent implements Predicate<RecordedEvent> {

    private String name;
    private Map<Attribute<?, ?>, Object> withProps = new HashMap<>();
    private List<Attribute<?, ?>> hasProps = new ArrayList<>();
    private List<Attribute<?, ?>> hasNotProps = new ArrayList<>();
    private List<ExpectedStackFrame> frames = new ArrayList<>();

    private ExpectedEvent(String name) {
        this.name = name;
    }

    public static ExpectedEvent event(String name) {
        return new ExpectedEvent(name);
    }

    public static ExpectedEvent event(JfrEventType jfrEventType) {
        return new ExpectedEvent(jfrEventType.getName());
    }

    /* default */ static boolean matches(ExpectedEvent expectedEvent, RecordedEvent recordedEvent) {
        if (expectedEvent.getName() != null
                && !recordedEvent.getEventType().getName().equals(expectedEvent.getName())) {
            return false;
        }

        if (!expectedEvent.getHasProps().isEmpty()) {
            for (Attribute<?, ?> attribute : expectedEvent.getHasProps()) {
                if (!hasPropertyMatching(recordedEvent, attribute.getName()))
                    return false;
            }
        }

        if (!expectedEvent.getHasNotProps().isEmpty()) {
            for (Attribute<?, ?> attribute : expectedEvent.getHasNotProps()) {
                if (hasPropertyMatching(recordedEvent, attribute.getName())) {
                    return false;
                }
            }
        }

        if (!expectedEvent.getWithProps().isEmpty()) {
            for (Map.Entry<Attribute<?, ?>, Object> prop : expectedEvent.getWithProps().entrySet()) {
                if (!withPropertyMatching(recordedEvent, prop.getKey().getName(), prop.getValue())) {
                    return false;
                }
            }
        }

        if (!expectedEvent.getFrames().isEmpty()) {
            RecordedStackTrace stackTrace = recordedEvent.getStackTrace();
            if (stackTrace == null) {
                return false;
            }
            for (ExpectedStackFrame expectedStackFrame : expectedEvent.getFrames()) {
                List<RecordedFrame> recordedFrames = stackTrace.getFrames();
                if (recordedFrames.stream().noneMatch(expectedStackFrame::test)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean hasPropertyMatching(RecordedEvent recordedEvent, String name) {
        if (!recordedEvent.hasField(name)) {
            return false;
        }

        Object value = recordedEvent.getValue(name);
        return value != null;
    }

    private static boolean withPropertyMatching(RecordedEvent recordedEvent, String name, Object value) {
        if (!recordedEvent.hasField(name)) {
            return false;
        }

        if ("objectClass".equalsIgnoreCase(name) && !(value instanceof ExpectedClass)) {
            return Objects.equals(recordedEvent.getClass("objectClass").getName(), value.toString());
        }

        if (value instanceof Duration) {
            return Objects.equals(recordedEvent.getDuration(name), value);
        }
        else if (value instanceof String) {
            return Objects.equals(recordedEvent.getString(name), value);
        }
        else if (value instanceof Boolean) {
            return Objects.equals(recordedEvent.getBoolean(name), value);
        }
        else if (value instanceof Byte) {
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
        else if (value instanceof ExpectedClass) {
            return ((ExpectedClass) value).test(recordedEvent.getClass(name));
        }
        else if (value instanceof ExpectedThread) {
            return ((ExpectedThread) value).test(recordedEvent.getThread(name));
        }
        else {
            throw new IllegalArgumentException(String.format("Unsupported property type: %s, %s", name, value));
        }
    }

    public String getName() {
        return name;
    }

    public Map<Attribute<?, ?>, Object> getWithProps() {
        return withProps;
    }

    public List<Attribute<?, ?>> getHasProps() {
        return hasProps;
    }

    public List<Attribute<?, ?>> getHasNotProps() {
        return hasNotProps;
    }

    public List<ExpectedStackFrame> getFrames() {
        return frames;
    }

    public <A extends JfrEventType, B> ExpectedEvent with(Attribute<A, B> attribute, B value) {
        this.withProps.put(attribute, value);
        return this;
    }

    public ExpectedEvent with(String name, Object value) {
        this.withProps.put(new Attribute<>(name), value);
        return this;
    }

    public ExpectedEvent has(Attribute<?, ?> attribute) {
        this.hasProps.add(attribute);
        return this;
    }

    public ExpectedEvent has(String name) {
        this.hasProps.add(new Attribute<>(name));
        return this;
    }

    public ExpectedEvent hasNot(Attribute<?, ?> attribute) {
        this.hasNotProps.add(attribute);
        return this;
    }

    public ExpectedEvent hasNot(String name) {
        this.hasNotProps.add(new Attribute<>(name));
        return this;
    }

    public ExpectedEvent containStackFrame(ExpectedStackFrame value) {
        this.frames.add(value);
        return this;
    }

    @Override
    public boolean test(RecordedEvent recordedEvent) {
        return matches(this, recordedEvent);
    }
}
