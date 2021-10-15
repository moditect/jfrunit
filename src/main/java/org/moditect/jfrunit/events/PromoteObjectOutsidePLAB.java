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
 * Object survived scavenge and was copied directly to the heap. Supported GCs are Parallel Scavange, G1 and CMS with Parallel New. Due to promotion being done in parallel an object might be reported multiple times as the GC threads race to copy all objects.
 */
public class PromoteObjectOutsidePLAB extends JfrEventType {
    public static final PromoteObjectOutsidePLAB INSTANCE = new PromoteObjectOutsidePLAB();
    public static final String EVENT_NAME = "jdk.PromoteObjectOutsidePLAB";
    public static final Attribute<PromoteObjectOutsidePLAB, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<PromoteObjectOutsidePLAB, org.moditect.jfrunit.ExpectedThread> EVENT_THREAD = new Attribute("eventThread");
    public static final Attribute<PromoteObjectOutsidePLAB, Integer> GC_ID = new Attribute("gcId");
    public static final Attribute<PromoteObjectOutsidePLAB, org.moditect.jfrunit.ExpectedClass> OBJECT_CLASS = new Attribute("objectClass");
    public static final Attribute<PromoteObjectOutsidePLAB, Long> OBJECT_SIZE = new Attribute("objectSize");
    public static final Attribute<PromoteObjectOutsidePLAB, Integer> TENURING_AGE = new Attribute("tenuringAge");
    public static final Attribute<PromoteObjectOutsidePLAB, Boolean> TENURED = new Attribute("tenured");

    public PromoteObjectOutsidePLAB() {
        super(EVENT_NAME);
    }
}
