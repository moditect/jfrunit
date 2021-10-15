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
package org.moditect.jfrunit.events;

import org.moditect.jfrunit.Attribute;
import org.moditect.jfrunit.JfrEventType;
import org.moditect.jfrunit.events.model.*;

/**
 * A set of container specific attributes
 */
public class ContainerConfiguration extends JfrEventType {
    public static final ContainerConfiguration INSTANCE = new ContainerConfiguration();
    public static final String EVENT_NAME = "jdk.ContainerConfiguration";
    public static final Attribute<ContainerConfiguration, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<ContainerConfiguration, java.time.Duration> DURATION = new Attribute("duration");
    public static final Attribute<ContainerConfiguration, org.moditect.jfrunit.ExpectedThread> EVENT_THREAD = new Attribute("eventThread");
    public static final Attribute<ContainerConfiguration, org.moditect.jfrunit.ExpectedStackTrace> STACK_TRACE = new Attribute("stackTrace");
    public static final Attribute<ContainerConfiguration, java.lang.String> CONTAINER_TYPE = new Attribute("containerType");
    public static final Attribute<ContainerConfiguration, java.time.Duration> CPU_SLICE_PERIOD = new Attribute("cpuSlicePeriod");
    public static final Attribute<ContainerConfiguration, java.time.Duration> CPU_QUOTA = new Attribute("cpuQuota");
    public static final Attribute<ContainerConfiguration, Long> CPU_SHARES = new Attribute("cpuShares");
    public static final Attribute<ContainerConfiguration, Long> EFFECTIVE_CPU_COUNT = new Attribute("effectiveCpuCount");
    public static final Attribute<ContainerConfiguration, Long> MEMORY_SOFT_LIMIT = new Attribute("memorySoftLimit");
    public static final Attribute<ContainerConfiguration, Long> MEMORY_LIMIT = new Attribute("memoryLimit");
    public static final Attribute<ContainerConfiguration, Long> SWAP_MEMORY_LIMIT = new Attribute("swapMemoryLimit");

    public ContainerConfiguration() {
        super(EVENT_NAME);
    }
}
