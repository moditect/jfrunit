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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.moditect.jfrunit.internal.EventConfiguration;
import org.spockframework.runtime.extension.AbstractMethodInterceptor;
import org.spockframework.runtime.extension.IGlobalExtension;
import org.spockframework.runtime.extension.IMethodInvocation;
import org.spockframework.runtime.model.FeatureInfo;
import org.spockframework.runtime.model.IterationInfo;
import org.spockframework.runtime.model.NameProvider;
import org.spockframework.runtime.model.SpecInfo;

public class JfrUnitSpockGlobalExtension implements IGlobalExtension {

    private static final JfrMethodInterceptor JFR_METHOD_INTERCEPTOR = new JfrMethodInterceptor();

    @Override
    public void visitSpec(SpecInfo spec) {
        if (hasJfrEvents(spec)) {
            spec.getBottomSpec().getAllFeatures().stream().forEach(featureInfo -> {
                featureInfo.getFeatureMethod().addInterceptor(JFR_METHOD_INTERCEPTOR);
            });
        }
    }

    private boolean hasJfrEvents(SpecInfo spec) {
        return spec.getAllFields().stream()
                .anyMatch(fieldInfo -> JfrEvents.class.isAssignableFrom(fieldInfo.getType()));
    }

    private static class JfrMethodInterceptor extends AbstractMethodInterceptor {

        @Override
        public void interceptFeatureMethod(IMethodInvocation invocation) throws Throwable {
            String enabledConfiguration = getEnabledConfiguration(invocation);
            List<EventConfiguration> enabledEvents = getEnabledEvents(invocation);

            getJfrEvents(invocation).stream().forEach(jfrEvents -> {
                jfrEvents.startRecordingEvents(enabledConfiguration, enabledEvents,
                        invocation.getMethod().getReflection(), getDumpFileName(invocation));
            });
            try {
                invocation.proceed();
            }
            finally {
                getJfrEvents(invocation).stream().forEach(JfrEvents::stopRecordingEvents);
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

        private List<JfrEvents> getJfrEvents(IMethodInvocation invocation) {
            return invocation.getSpec().getAllFields().stream()
                    .filter(fieldInfo -> JfrEvents.class.isAssignableFrom(fieldInfo.getType()))
                    .map(fieldInfo -> (JfrEvents) fieldInfo.readValue(
                            fieldInfo.isShared() ? invocation.getSharedInstance() : invocation.getInstance()))
                    .collect(Collectors.toList());
        }

        private String getDumpFileName(IMethodInvocation invocation) {
            FeatureInfo featureInfo = invocation.getFeature();
            if (featureInfo != null) {
                StringBuilder dumpFileName = new StringBuilder();
                dumpFileName.append(featureInfo.getSpec().getReflection().getName());
                dumpFileName.append('-');
                if (featureInfo.isParameterized()) {
                    NameProvider<IterationInfo> nameProvider = featureInfo.getIterationNameProvider();
                    if (nameProvider != null && invocation.getIteration() != null) {
                        dumpFileName.append(nameProvider.getName(invocation.getIteration()));
                    }
                    else if (invocation.getIteration() != null) {
                        dumpFileName.append(featureInfo.getDisplayName());
                        dumpFileName.append('_');
                        dumpFileName.append(invocation.getIteration().getIterationIndex());
                    }
                }
                else {
                    dumpFileName.append(featureInfo.getDisplayName());
                }
                return dumpFileName.toString();
            }
            else {
                return null;
            }
        }

    }

}
