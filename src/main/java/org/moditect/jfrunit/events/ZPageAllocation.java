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
 * Allocation of a ZPage
 */
public class ZPageAllocation extends JfrEventType {
    public static final ZPageAllocation INSTANCE = new ZPageAllocation();
    public static final String EVENT_NAME = "jdk.ZPageAllocation";
    public static final Attribute<ZPageAllocation, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<ZPageAllocation, java.time.Duration> DURATION = new Attribute("duration");
    public static final Attribute<ZPageAllocation, org.moditect.jfrunit.ExpectedThread> EVENT_THREAD = new Attribute("eventThread");
    public static final Attribute<ZPageAllocation, org.moditect.jfrunit.ExpectedStackTrace> STACK_TRACE = new Attribute("stackTrace");
    public static final Attribute<ZPageAllocation, java.lang.String> TYPE = new Attribute("type");
    public static final Attribute<ZPageAllocation, Long> SIZE = new Attribute("size");
    public static final Attribute<ZPageAllocation, Long> FLUSHED = new Attribute("flushed");
    public static final Attribute<ZPageAllocation, Long> COMMITTED = new Attribute("committed");
    public static final Attribute<ZPageAllocation, Integer> SEGMENTS = new Attribute("segments");
    public static final Attribute<ZPageAllocation, Boolean> NON_BLOCKING = new Attribute("nonBlocking");

    public ZPageAllocation() {
        super(EVENT_NAME);
    }
}
