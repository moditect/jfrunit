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
public class CPUTimeStampCounter extends JfrEventType {
    public static final CPUTimeStampCounter INSTANCE = new CPUTimeStampCounter();
    public static final String EVENT_NAME = "jdk.CPUTimeStampCounter";
    public static final Attribute<CPUTimeStampCounter, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<CPUTimeStampCounter, Boolean> FAST_TIME_ENABLED = new Attribute("fastTimeEnabled");
    public static final Attribute<CPUTimeStampCounter, Boolean> FAST_TIME_AUTO_ENABLED = new Attribute("fastTimeAutoEnabled");
    public static final Attribute<CPUTimeStampCounter, Long> OS_FREQUENCY = new Attribute("osFrequency");
    public static final Attribute<CPUTimeStampCounter, Long> FAST_TIME_FREQUENCY = new Attribute("fastTimeFrequency");

    public CPUTimeStampCounter() {
        super(EVENT_NAME);
    }
}
