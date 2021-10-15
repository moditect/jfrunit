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
 * 
 */
public class PSHeapSummary extends JfrEventType {
    public static final PSHeapSummary INSTANCE = new PSHeapSummary();
    public static final String EVENT_NAME = "jdk.PSHeapSummary";
    public static final Attribute<PSHeapSummary, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<PSHeapSummary, Integer> GC_ID = new Attribute("gcId");
    public static final Attribute<PSHeapSummary, java.lang.String> WHEN = new Attribute("when");
    public static final Attribute<PSHeapSummary, VirtualSpace> OLD_SPACE = new Attribute("oldSpace");
    public static final Attribute<PSHeapSummary, ObjectSpace> OLD_OBJECT_SPACE = new Attribute("oldObjectSpace");
    public static final Attribute<PSHeapSummary, VirtualSpace> YOUNG_SPACE = new Attribute("youngSpace");
    public static final Attribute<PSHeapSummary, ObjectSpace> EDEN_SPACE = new Attribute("edenSpace");
    public static final Attribute<PSHeapSummary, ObjectSpace> FROM_SPACE = new Attribute("fromSpace");
    public static final Attribute<PSHeapSummary, ObjectSpace> TO_SPACE = new Attribute("toSpace");

    public PSHeapSummary() {
        super(EVENT_NAME);
    }
}
