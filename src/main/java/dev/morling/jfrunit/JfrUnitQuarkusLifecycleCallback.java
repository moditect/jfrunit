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
package dev.morling.jfrunit;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.quarkus.test.junit.callback.QuarkusTestAfterEachCallback;
import io.quarkus.test.junit.callback.QuarkusTestBeforeEachCallback;
import io.quarkus.test.junit.callback.QuarkusTestMethodContext;
import java.util.stream.Stream;

public class JfrUnitQuarkusLifecycleCallback implements QuarkusTestBeforeEachCallback, QuarkusTestAfterEachCallback {

    @Override
    public void beforeEach(QuarkusTestMethodContext context) {
        Object instance = context.getTestInstance();
        String enabledConfiguration = getEnabledConfiguration(context.getTestMethod());
        List<EventConfiguration> enabledEvents = getEnabledEvents(context.getTestMethod());

        List<JfrEvents> allJfrEvents = getJfrEvents(instance);
        for (JfrEvents jfrEvents : allJfrEvents) {
            jfrEvents.startRecordingEvents(enabledConfiguration, enabledEvents, context.getTestMethod());
        }
    }

    @Override
    public void afterEach(QuarkusTestMethodContext context) {
        Object instance = context.getTestInstance();

        List<JfrEvents> allJfrEvents = getJfrEvents(instance);
        for (JfrEvents jfrEvents : allJfrEvents) {
            jfrEvents.stopRecordingEvents();
        }
    }

    private String getEnabledConfiguration(Method testMethod) {
        return Optional.ofNullable(testMethod.getAnnotation(EnableConfiguration.class))
                .map(EnableConfiguration::value)
                .map(String::trim)
                .orElse(null);
    }

    private List<EventConfiguration> getEnabledEvents(Method testMethod) {
        return Arrays.stream(testMethod.getAnnotationsByType(EnableEvent.class))
            .map(e -> new EventConfiguration(e.value(), e.stackTrace(), e.threshold(), e.period()))
            .collect(Collectors.toList());
    }

    private List<JfrEvents> getJfrEvents(Object instance) {
        return getAllFields(instance.getClass())
            .filter(f -> f.getType() == JfrEvents.class)
            .map(f -> {
                try {
                    return (JfrEvents) f.get(instance);
                }
                catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            })
            .collect(Collectors.toList());
    }

    /**
     * Gets all fields declared anywhere on the inheritance path between the given class and {@link Object}.
     *
     * This is necessary because Quarkus may implicitly subclass a test class with a proxy class.
     */
    private static Stream<Field> getAllFields(Class<?> c) {
        Class<?> superclass = c.getSuperclass();
        return Stream.concat(
            Arrays.stream(c.getDeclaredFields()),
            superclass == null ? Stream.empty() : getAllFields(superclass));
    }
}
