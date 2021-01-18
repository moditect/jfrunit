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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

import jdk.jfr.consumer.RecordedEvent;

public class ExpectedEvent implements Predicate<RecordedEvent> {

    private static final System.Logger LOGGER = System.getLogger(ExpectedEvent.class.getName());

    private String name;
    private Map<String, Object> withProps = new HashMap<>();
    private List<String> hasProps = new ArrayList<>();
    private List<String> hasNotProps = new ArrayList<>();


    private ExpectedEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<String, Object> getWithProps() {
        return withProps;
    }

    public List<String> getHasProps() {
        return hasProps;
    }

    public List<String> getHasNotProps() {
        return hasNotProps;
    }

    public ExpectedEvent with(String name, Object value) {
        this.withProps.put(name, value);
        return this;
    }

    public ExpectedEvent has(String name) {
        this.hasProps.add(name);
        return this;
    }

    public ExpectedEvent hasNot(String name) {
        this.hasNotProps.add(name);
        return this;
    }

    public static ExpectedEvent event(String name) {
        return new ExpectedEvent(name);
    }

    /* default */ static boolean matches(ExpectedEvent expectedEvent, RecordedEvent recordedEvent) {
        if (!recordedEvent.getEventType().getName().equals(expectedEvent.getName())) {
            return false;
        }

        if(!expectedEvent.getHasProps().isEmpty()) {
            for(String name: expectedEvent.getHasProps()) {
                if(!hasPropertyMatching(recordedEvent, name))
                    return false;
            }
        }

        if(!expectedEvent.getHasNotProps().isEmpty()) {
            for(String name: expectedEvent.getHasNotProps()) {
                if(hasPropertyMatching(recordedEvent, name)) {
                    return false;
                }
            }
        }

        if (!expectedEvent.getWithProps().isEmpty()) {
            for (Entry<String, Object> prop : expectedEvent.getWithProps().entrySet()) {
                if (!withPropertyMatching(recordedEvent, prop.getKey(), prop.getValue())) {
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
        return value!=null;
    }

    private static boolean withPropertyMatching(RecordedEvent recordedEvent, String name, Object value) {
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

    @Override
    public boolean test(RecordedEvent recordedEvent) {
        return matches(this, recordedEvent);
    }
}
