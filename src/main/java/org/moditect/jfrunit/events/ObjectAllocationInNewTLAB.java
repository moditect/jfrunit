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
 * Allocation in new Thread Local Allocation Buffer
 */
public class ObjectAllocationInNewTLAB extends JfrEventType {
    public static final ObjectAllocationInNewTLAB INSTANCE = new ObjectAllocationInNewTLAB();
    public static final String EVENT_NAME = "jdk.ObjectAllocationInNewTLAB";
    public static final Attribute<ObjectAllocationInNewTLAB, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<ObjectAllocationInNewTLAB, org.moditect.jfrunit.ExpectedThread> EVENT_THREAD = new Attribute("eventThread");
    public static final Attribute<ObjectAllocationInNewTLAB, org.moditect.jfrunit.ExpectedStackTrace> STACK_TRACE = new Attribute("stackTrace");
    public static final Attribute<ObjectAllocationInNewTLAB, org.moditect.jfrunit.ExpectedClass> OBJECT_CLASS = new Attribute("objectClass");
    public static final Attribute<ObjectAllocationInNewTLAB, Long> ALLOCATION_SIZE = new Attribute("allocationSize");
    public static final Attribute<ObjectAllocationInNewTLAB, Long> TLAB_SIZE = new Attribute("tlabSize");

    public ObjectAllocationInNewTLAB() {
        super(EVENT_NAME);
    }
}
