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
 * Statistics related to current adaptive IHOP calculation
 */
public class G1AdaptiveIHOP extends JfrEventType {
    public static final G1AdaptiveIHOP INSTANCE = new G1AdaptiveIHOP();
    public static final String EVENT_NAME = "jdk.G1AdaptiveIHOP";
    public static final Attribute<G1AdaptiveIHOP, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<G1AdaptiveIHOP, Integer> GC_ID = new Attribute("gcId");
    public static final Attribute<G1AdaptiveIHOP, Long> THRESHOLD = new Attribute("threshold");
    public static final Attribute<G1AdaptiveIHOP, Float> THRESHOLD_PERCENTAGE = new Attribute("thresholdPercentage");
    public static final Attribute<G1AdaptiveIHOP, Long> IHOP_TARGET_OCCUPANCY = new Attribute("ihopTargetOccupancy");
    public static final Attribute<G1AdaptiveIHOP, Long> CURRENT_OCCUPANCY = new Attribute("currentOccupancy");
    public static final Attribute<G1AdaptiveIHOP, Long> ADDITIONAL_BUFFER_SIZE = new Attribute("additionalBufferSize");
    public static final Attribute<G1AdaptiveIHOP, Double> PREDICTED_ALLOCATION_RATE = new Attribute("predictedAllocationRate");
    public static final Attribute<G1AdaptiveIHOP, java.time.Duration> PREDICTED_MARKING_DURATION = new Attribute("predictedMarkingDuration");
    public static final Attribute<G1AdaptiveIHOP, Boolean> PREDICTION_ACTIVE = new Attribute("predictionActive");

    public G1AdaptiveIHOP() {
        super(EVENT_NAME);
    }
}
