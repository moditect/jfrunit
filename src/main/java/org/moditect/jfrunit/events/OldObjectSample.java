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
 * A potential memory leak
 */
public class OldObjectSample extends JfrEventType {
    public static final OldObjectSample INSTANCE = new OldObjectSample();
    public static final String EVENT_NAME = "jdk.OldObjectSample";
    public static final Attribute<OldObjectSample, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<OldObjectSample, java.time.Duration> DURATION = new Attribute("duration");
    public static final Attribute<OldObjectSample, org.moditect.jfrunit.ExpectedThread> EVENT_THREAD = new Attribute("eventThread");
    public static final Attribute<OldObjectSample, org.moditect.jfrunit.ExpectedStackTrace> STACK_TRACE = new Attribute("stackTrace");
    public static final Attribute<OldObjectSample, Long> ALLOCATION_TIME = new Attribute("allocationTime");
    public static final Attribute<OldObjectSample, Long> OBJECT_AGE = new Attribute("objectAge");
    public static final Attribute<OldObjectSample, Long> LAST_KNOWN_HEAP_USAGE = new Attribute("lastKnownHeapUsage");
    public static final Attribute<OldObjectSample, OldObject> OBJECT = new Attribute("object");
    public static final Attribute<OldObjectSample, Integer> ARRAY_ELEMENTS = new Attribute("arrayElements");
    public static final Attribute<OldObjectSample, OldObjectGcRoot> ROOT = new Attribute("root");

    public OldObjectSample() {
        super(EVENT_NAME);
    }
}
