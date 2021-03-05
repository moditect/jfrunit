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

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension;
import org.spockframework.runtime.extension.AbstractMethodInterceptor;
import org.spockframework.runtime.extension.IMethodInvocation;
import org.spockframework.runtime.model.FieldInfo;
import org.spockframework.runtime.model.SpecInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JfrEventTestSpockExtension extends AbstractAnnotationDrivenExtension<JfrEventTest> {

    @Override
    public void visitSpecAnnotation(JfrEventTest annotation, SpecInfo spec) {
        final JfrMethodInterceptor interceptor = new JfrMethodInterceptor();
        spec.getBottomSpec().getAllFeatures().stream().forEach(featureInfo -> {
            featureInfo.getFeatureMethod().addInterceptor(interceptor);
        });
    }

    private static class JfrMethodInterceptor extends AbstractMethodInterceptor {

        @Override
        public void interceptFeatureMethod(IMethodInvocation invocation) throws Throwable {
            String enabledConfiguration = getEnabledConfiguration(invocation);
            List<EventConfiguration> enabledEvents = getEnabledEvents(invocation);

            getJfrEvents(invocation).stream().map(JfrEventsContext::getJfrEvents).forEach(jfrEvents -> {
                jfrEvents.startRecordingEvents(enabledConfiguration, enabledEvents, invocation.getMethod().getReflection());
            });
            try {
                invocation.proceed();
            } finally {
                getJfrEvents(invocation).stream().forEach(jfrEventsContext -> {
                    JfrEvents jfrEvents = jfrEventsContext.getJfrEvents();
                    jfrEvents.stopRecordingEvents();
                    if (!jfrEventsContext.getFieldInfo().isShared()) {
                        jfrEvents.clear();
                    }
                });
            }
        }

        private String getEnabledConfiguration(IMethodInvocation invocation) {
            return Optional.ofNullable(invocation.getMethod().getAnnotation(EnableConfiguration.class))
                    .map(EnableConfiguration::value)
                    .map(String::trim)
                    .orElse(null);
        }

        private List<EventConfiguration> getEnabledEvents(IMethodInvocation invocation) {
            return Arrays.stream(invocation.getMethod().getReflection().getAnnotationsByType(EnableEvent.class))
                    .map(e -> new EventConfiguration(e.value(), e.stackTrace(), e.threshold(), e.period()))
                    .collect(Collectors.toList());
        }

        private List<JfrEventsContext> getJfrEvents(IMethodInvocation invocation) {
            return invocation.getSpec().getAllFields().stream()
                    .filter(fieldInfo -> JfrEvents.class.equals(fieldInfo.getType()))
                    .map(fieldInfo -> new JfrEventsContext((JfrEvents) fieldInfo.readValue(invocation.getInstance()), fieldInfo))
                    .collect(Collectors.toList());
        }

    }

    private static class JfrEventsContext {

        private final JfrEvents jfrEvents;

        private final FieldInfo fieldInfo;

        public JfrEventsContext(JfrEvents jfrEvents, FieldInfo fieldInfo) {
            this.jfrEvents = jfrEvents;
            this.fieldInfo = fieldInfo;
        }

        public JfrEvents getJfrEvents() {
            return jfrEvents;
        }

        public FieldInfo getFieldInfo() {
            return fieldInfo;
        }
    }

}
