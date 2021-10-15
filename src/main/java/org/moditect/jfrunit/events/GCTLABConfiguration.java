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
 * The configuration of the Thread Local Allocation Buffers (TLABs)
 */
public class GCTLABConfiguration extends JfrEventType {
    public static final GCTLABConfiguration INSTANCE = new GCTLABConfiguration();
    public static final String EVENT_NAME = "jdk.GCTLABConfiguration";
    public static final Attribute<GCTLABConfiguration, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<GCTLABConfiguration, Boolean> USES_T_L_A_BS = new Attribute("usesTLABs");
    public static final Attribute<GCTLABConfiguration, Long> MIN_T_L_A_B_SIZE = new Attribute("minTLABSize");
    public static final Attribute<GCTLABConfiguration, Long> TLAB_REFILL_WASTE_LIMIT = new Attribute("tlabRefillWasteLimit");

    public GCTLABConfiguration() {
        super(EVENT_NAME);
    }
}
