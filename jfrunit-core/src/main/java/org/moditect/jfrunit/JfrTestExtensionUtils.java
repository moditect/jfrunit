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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.platform.commons.support.AnnotationSupport;

/**
 * Utility methods used by Junit4/5 extensions that start/stop recording before/after each test (method) execution.
 *
 * @see JfrJunit4TestWatcher#starting
 * @see JfrJunit4TestWatcher#finished
 * @see JfrEventTestExtension#beforeEach
 * @see JfrEventTestExtension#afterEach
 */
public class JfrTestExtensionUtils {

    private JfrTestExtensionUtils() {
        super();
    }

    /**
     * gather JfrEvents instance variables declared into test classes that will be used to store the events fired by the current test execution.
     *
     * @param testInstance
     * @return
     */
    private static List<JfrEvents> getJfrEvents(Object testInstance) {
        return getAllFields(testInstance.getClass())
                .filter(f -> f.getType() == JfrEvents.class)
                .map(f -> {
                    try {
                        return (JfrEvents) f.get(testInstance);
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
     * <p>This is necessary because some test frameworks may implicitly subclass a test class with a proxy
     * class.  It is also useful for test fixtures.
     */
    private static Stream<Field> getAllFields(Class<?> c) {
        Class<?> superclass = c.getSuperclass();
        return Stream.concat(
                Arrays.stream(c.getDeclaredFields()),
                superclass == null ? Stream.empty() : getAllFields(superclass));
    }

    public static void beforeEach(Method testMethod, Object testInstance) {
        String enabledConfiguration = AnnotationSupport.findAnnotation(testMethod, EnableConfiguration.class)
                .map(EnableConfiguration::value)
                .map(String::trim)
                .orElse(null);

        List<EventConfiguration> enabledEvents = AnnotationSupport.findRepeatableAnnotations(testMethod, EnableEvent.class)
                .stream()
                .map(e -> new EventConfiguration(e.value(), e.stackTrace(), e.threshold(), e.period()))
                .collect(Collectors.toList());

        List<JfrEvents> allJfrEvents = getJfrEvents(testInstance);
        for (JfrEvents jfrEvents : allJfrEvents) {
            jfrEvents.startRecordingEvents(enabledConfiguration, enabledEvents, testMethod, null);
        }
    }

    public static void afterEach(Object testInstance) {
        List<JfrEvents> allJfrEvents = getJfrEvents(testInstance);
        for (JfrEvents jfrEvents : allJfrEvents) {
            jfrEvents.stopRecordingEvents();
        }
    }
}
