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
 * Statistics of direct buffer
 */
public class DirectBufferStatistics extends JfrEventType {
    public static final DirectBufferStatistics INSTANCE = new DirectBufferStatistics();
    public static final String EVENT_NAME = "jdk.DirectBufferStatistics";
    public static final Attribute<DirectBufferStatistics, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<DirectBufferStatistics, java.time.Duration> DURATION = new Attribute("duration");
    public static final Attribute<DirectBufferStatistics, org.moditect.jfrunit.ExpectedThread> EVENT_THREAD = new Attribute("eventThread");
    public static final Attribute<DirectBufferStatistics, org.moditect.jfrunit.ExpectedStackTrace> STACK_TRACE = new Attribute("stackTrace");
    public static final Attribute<DirectBufferStatistics, Long> MAX_CAPACITY = new Attribute("maxCapacity");
    public static final Attribute<DirectBufferStatistics, Long> COUNT = new Attribute("count");
    public static final Attribute<DirectBufferStatistics, Long> TOTAL_CAPACITY = new Attribute("totalCapacity");
    public static final Attribute<DirectBufferStatistics, Long> MEMORY_USED = new Attribute("memoryUsed");

    public DirectBufferStatistics() {
        super(EVENT_NAME);
    }
}
