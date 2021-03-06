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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;

public class JfrEventTestExtension implements Extension, BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        String enabledConfiguration = AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), EnableConfiguration.class)
            .map(EnableConfiguration::value)
            .map(String::trim)
            .orElse(null);

        List<EventConfiguration> enabledEvents = AnnotationSupport.findRepeatableAnnotations(context.getRequiredTestMethod(), EnableEvent.class)
                .stream()
                .map(e -> new EventConfiguration(e.value(), e.stackTrace(), e.threshold(), e.period()))
                .collect(Collectors.toList());


        Object instance = context.getRequiredTestInstance();
        List<JfrEvents> allJfrEvents = getJfrEvents(instance);
        for (JfrEvents jfrEvents : allJfrEvents) {
            jfrEvents.startRecordingEvents(enabledConfiguration, enabledEvents, context.getRequiredTestMethod());
        }
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Object instance = context.getRequiredTestInstance();

        List<JfrEvents> allJfrEvents = getJfrEvents(instance);
        for (JfrEvents jfrEvents : allJfrEvents) {
            jfrEvents.stopRecordingEvents();
        }
    }

    private List<JfrEvents> getJfrEvents(Object instance) {
        return Arrays.stream(instance.getClass().getDeclaredFields())
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
}
