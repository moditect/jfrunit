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
 * Basic statistics related to current IHOP calculation
 */
public class G1BasicIHOP {
    public static final String EVENT_NAME = "jdk.G1BasicIHOP";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_GCID_NAME = "gcId";
    public static final String ATTRIBUTE_GCID_TYPE = "int";
    public static final String ATTRIBUTE_THRESHOLD_NAME = "threshold";
    public static final String ATTRIBUTE_THRESHOLD_TYPE = "long";
    public static final String ATTRIBUTE_THRESHOLDPERCENTAGE_NAME = "thresholdPercentage";
    public static final String ATTRIBUTE_THRESHOLDPERCENTAGE_TYPE = "float";
    public static final String ATTRIBUTE_TARGETOCCUPANCY_NAME = "targetOccupancy";
    public static final String ATTRIBUTE_TARGETOCCUPANCY_TYPE = "long";
    public static final String ATTRIBUTE_CURRENTOCCUPANCY_NAME = "currentOccupancy";
    public static final String ATTRIBUTE_CURRENTOCCUPANCY_TYPE = "long";
    public static final String ATTRIBUTE_RECENTMUTATORALLOCATIONSIZE_NAME = "recentMutatorAllocationSize";
    public static final String ATTRIBUTE_RECENTMUTATORALLOCATIONSIZE_TYPE = "long";
    public static final String ATTRIBUTE_RECENTMUTATORDURATION_NAME = "recentMutatorDuration";
    public static final String ATTRIBUTE_RECENTMUTATORDURATION_TYPE = "long";
    public static final String ATTRIBUTE_RECENTALLOCATIONRATE_NAME = "recentAllocationRate";
    public static final String ATTRIBUTE_RECENTALLOCATIONRATE_TYPE = "double";
    public static final String ATTRIBUTE_LASTMARKINGDURATION_NAME = "lastMarkingDuration";
    public static final String ATTRIBUTE_LASTMARKINGDURATION_TYPE = "long";
}
