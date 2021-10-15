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
 * Basic statistics related to current IHOP calculation
 */
public class G1BasicIHOP extends JfrEventType {
    public static final G1BasicIHOP INSTANCE = new G1BasicIHOP();
    public static final String EVENT_NAME = "jdk.G1BasicIHOP";
    public static final Attribute<G1BasicIHOP, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<G1BasicIHOP, Integer> GC_ID = new Attribute("gcId");
    public static final Attribute<G1BasicIHOP, Long> THRESHOLD = new Attribute("threshold");
    public static final Attribute<G1BasicIHOP, Float> THRESHOLD_PERCENTAGE = new Attribute("thresholdPercentage");
    public static final Attribute<G1BasicIHOP, Long> TARGET_OCCUPANCY = new Attribute("targetOccupancy");
    public static final Attribute<G1BasicIHOP, Long> CURRENT_OCCUPANCY = new Attribute("currentOccupancy");
    public static final Attribute<G1BasicIHOP, Long> RECENT_MUTATOR_ALLOCATION_SIZE = new Attribute("recentMutatorAllocationSize");
    public static final Attribute<G1BasicIHOP, java.time.Duration> RECENT_MUTATOR_DURATION = new Attribute("recentMutatorDuration");
    public static final Attribute<G1BasicIHOP, Double> RECENT_ALLOCATION_RATE = new Attribute("recentAllocationRate");
    public static final Attribute<G1BasicIHOP, java.time.Duration> LAST_MARKING_DURATION = new Attribute("lastMarkingDuration");

    public G1BasicIHOP() {
        super(EVENT_NAME);
    }
}
