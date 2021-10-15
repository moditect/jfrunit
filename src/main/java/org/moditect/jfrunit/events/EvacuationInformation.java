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
public class EvacuationInformation extends JfrEventType {
    public static final EvacuationInformation INSTANCE = new EvacuationInformation();
    public static final String EVENT_NAME = "jdk.EvacuationInformation";
    public static final Attribute<EvacuationInformation, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<EvacuationInformation, Integer> GC_ID = new Attribute("gcId");
    public static final Attribute<EvacuationInformation, Integer> C_SET_REGIONS = new Attribute("cSetRegions");
    public static final Attribute<EvacuationInformation, Long> C_SET_USED_BEFORE = new Attribute("cSetUsedBefore");
    public static final Attribute<EvacuationInformation, Long> C_SET_USED_AFTER = new Attribute("cSetUsedAfter");
    public static final Attribute<EvacuationInformation, Integer> ALLOCATION_REGIONS = new Attribute("allocationRegions");
    public static final Attribute<EvacuationInformation, Long> ALLOCATION_REGIONS_USED_BEFORE = new Attribute("allocationRegionsUsedBefore");
    public static final Attribute<EvacuationInformation, Long> ALLOCATION_REGIONS_USED_AFTER = new Attribute("allocationRegionsUsedAfter");
    public static final Attribute<EvacuationInformation, Long> BYTES_COPIED = new Attribute("bytesCopied");
    public static final Attribute<EvacuationInformation, Integer> REGIONS_FREED = new Attribute("regionsFreed");

    public EvacuationInformation() {
        super(EVENT_NAME);
    }
}
