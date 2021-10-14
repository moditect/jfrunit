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

/**
 * 
 */
public class CodeSweeperStatistics {
    public static final String EVENT_NAME = "jdk.CodeSweeperStatistics";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_SWEEPCOUNT_NAME = "sweepCount";
    public static final String ATTRIBUTE_SWEEPCOUNT_TYPE = "int";
    public static final String ATTRIBUTE_METHODRECLAIMEDCOUNT_NAME = "methodReclaimedCount";
    public static final String ATTRIBUTE_METHODRECLAIMEDCOUNT_TYPE = "int";
    public static final String ATTRIBUTE_TOTALSWEEPTIME_NAME = "totalSweepTime";
    public static final String ATTRIBUTE_TOTALSWEEPTIME_TYPE = "long";
    public static final String ATTRIBUTE_PEAKFRACTIONTIME_NAME = "peakFractionTime";
    public static final String ATTRIBUTE_PEAKFRACTIONTIME_TYPE = "long";
    public static final String ATTRIBUTE_PEAKSWEEPTIME_NAME = "peakSweepTime";
    public static final String ATTRIBUTE_PEAKSWEEPTIME_TYPE = "long";
}
