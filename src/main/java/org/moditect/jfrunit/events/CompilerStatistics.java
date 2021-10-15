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
public class CompilerStatistics extends JfrEventType {
    public static final CompilerStatistics INSTANCE = new CompilerStatistics();
    public static final String EVENT_NAME = "jdk.CompilerStatistics";
    public static final Attribute<CompilerStatistics, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<CompilerStatistics, Integer> COMPILE_COUNT = new Attribute("compileCount");
    public static final Attribute<CompilerStatistics, Integer> BAILOUT_COUNT = new Attribute("bailoutCount");
    public static final Attribute<CompilerStatistics, Integer> INVALIDATED_COUNT = new Attribute("invalidatedCount");
    public static final Attribute<CompilerStatistics, Integer> OSR_COMPILE_COUNT = new Attribute("osrCompileCount");
    public static final Attribute<CompilerStatistics, Integer> STANDARD_COMPILE_COUNT = new Attribute("standardCompileCount");
    public static final Attribute<CompilerStatistics, Long> OSR_BYTES_COMPILED = new Attribute("osrBytesCompiled");
    public static final Attribute<CompilerStatistics, Long> STANDARD_BYTES_COMPILED = new Attribute("standardBytesCompiled");
    public static final Attribute<CompilerStatistics, Long> NMETHODS_SIZE = new Attribute("nmethodsSize");
    public static final Attribute<CompilerStatistics, Long> NMETHOD_CODE_SIZE = new Attribute("nmethodCodeSize");
    public static final Attribute<CompilerStatistics, java.time.Duration> PEAK_TIME_SPENT = new Attribute("peakTimeSpent");
    public static final Attribute<CompilerStatistics, java.time.Duration> TOTAL_TIME_SPENT = new Attribute("totalTimeSpent");

    public CompilerStatistics() {
        super(EVENT_NAME);
    }
}
