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
 * GC phases for parallel workers
 */
public class GCPhaseParallel extends JfrEventType {
    public static final GCPhaseParallel INSTANCE = new GCPhaseParallel();
    public static final String EVENT_NAME = "jdk.GCPhaseParallel";
    public static final Attribute<GCPhaseParallel, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<GCPhaseParallel, java.time.Duration> DURATION = new Attribute("duration");
    public static final Attribute<GCPhaseParallel, org.moditect.jfrunit.ExpectedThread> EVENT_THREAD = new Attribute("eventThread");
    public static final Attribute<GCPhaseParallel, Integer> GC_ID = new Attribute("gcId");
    public static final Attribute<GCPhaseParallel, Integer> GC_WORKER_ID = new Attribute("gcWorkerId");
    public static final Attribute<GCPhaseParallel, java.lang.String> NAME = new Attribute("name");

    public GCPhaseParallel() {
        super(EVENT_NAME);
    }
}
