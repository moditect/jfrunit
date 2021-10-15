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
 * The configuration of the garbage collector
 */
public class GCConfiguration extends JfrEventType {
    public static final GCConfiguration INSTANCE = new GCConfiguration();
    public static final String EVENT_NAME = "jdk.GCConfiguration";
    public static final Attribute<GCConfiguration, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<GCConfiguration, java.lang.String> YOUNG_COLLECTOR = new Attribute("youngCollector");
    public static final Attribute<GCConfiguration, java.lang.String> OLD_COLLECTOR = new Attribute("oldCollector");
    public static final Attribute<GCConfiguration, Integer> PARALLEL_G_C_THREADS = new Attribute("parallelGCThreads");
    public static final Attribute<GCConfiguration, Integer> CONCURRENT_G_C_THREADS = new Attribute("concurrentGCThreads");
    public static final Attribute<GCConfiguration, Boolean> USES_DYNAMIC_G_C_THREADS = new Attribute("usesDynamicGCThreads");
    public static final Attribute<GCConfiguration, Boolean> IS_EXPLICIT_G_C_CONCURRENT = new Attribute("isExplicitGCConcurrent");
    public static final Attribute<GCConfiguration, Boolean> IS_EXPLICIT_G_C_DISABLED = new Attribute("isExplicitGCDisabled");
    public static final Attribute<GCConfiguration, java.time.Duration> PAUSE_TARGET = new Attribute("pauseTarget");
    public static final Attribute<GCConfiguration, Integer> GC_TIME_RATIO = new Attribute("gcTimeRatio");

    public GCConfiguration() {
        super(EVENT_NAME);
    }
}
