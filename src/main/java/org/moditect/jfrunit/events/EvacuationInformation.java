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
public class EvacuationInformation {
    public static final String EVENT_NAME = "jdk.EvacuationInformation";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_GCID_NAME = "gcId";
    public static final String ATTRIBUTE_GCID_TYPE = "int";
    public static final String ATTRIBUTE_CSETREGIONS_NAME = "cSetRegions";
    public static final String ATTRIBUTE_CSETREGIONS_TYPE = "int";
    public static final String ATTRIBUTE_CSETUSEDBEFORE_NAME = "cSetUsedBefore";
    public static final String ATTRIBUTE_CSETUSEDBEFORE_TYPE = "long";
    public static final String ATTRIBUTE_CSETUSEDAFTER_NAME = "cSetUsedAfter";
    public static final String ATTRIBUTE_CSETUSEDAFTER_TYPE = "long";
    public static final String ATTRIBUTE_ALLOCATIONREGIONS_NAME = "allocationRegions";
    public static final String ATTRIBUTE_ALLOCATIONREGIONS_TYPE = "int";
    public static final String ATTRIBUTE_ALLOCATIONREGIONSUSEDBEFORE_NAME = "allocationRegionsUsedBefore";
    public static final String ATTRIBUTE_ALLOCATIONREGIONSUSEDBEFORE_TYPE = "long";
    public static final String ATTRIBUTE_ALLOCATIONREGIONSUSEDAFTER_NAME = "allocationRegionsUsedAfter";
    public static final String ATTRIBUTE_ALLOCATIONREGIONSUSEDAFTER_TYPE = "long";
    public static final String ATTRIBUTE_BYTESCOPIED_NAME = "bytesCopied";
    public static final String ATTRIBUTE_BYTESCOPIED_TYPE = "long";
    public static final String ATTRIBUTE_REGIONSFREED_NAME = "regionsFreed";
    public static final String ATTRIBUTE_REGIONSFREED_TYPE = "int";
}
