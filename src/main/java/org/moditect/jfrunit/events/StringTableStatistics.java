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
public class StringTableStatistics {
    public static final String EVENT_NAME = "jdk.StringTableStatistics";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_BUCKETCOUNT_NAME = "bucketCount";
    public static final String ATTRIBUTE_BUCKETCOUNT_TYPE = "long";
    public static final String ATTRIBUTE_ENTRYCOUNT_NAME = "entryCount";
    public static final String ATTRIBUTE_ENTRYCOUNT_TYPE = "long";
    public static final String ATTRIBUTE_TOTALFOOTPRINT_NAME = "totalFootprint";
    public static final String ATTRIBUTE_TOTALFOOTPRINT_TYPE = "long";
    public static final String ATTRIBUTE_BUCKETCOUNTMAXIMUM_NAME = "bucketCountMaximum";
    public static final String ATTRIBUTE_BUCKETCOUNTMAXIMUM_TYPE = "long";
    public static final String ATTRIBUTE_BUCKETCOUNTAVERAGE_NAME = "bucketCountAverage";
    public static final String ATTRIBUTE_BUCKETCOUNTAVERAGE_TYPE = "float";
    public static final String ATTRIBUTE_BUCKETCOUNTVARIANCE_NAME = "bucketCountVariance";
    public static final String ATTRIBUTE_BUCKETCOUNTVARIANCE_TYPE = "float";
    public static final String ATTRIBUTE_BUCKETCOUNTSTANDARDDEVIATION_NAME = "bucketCountStandardDeviation";
    public static final String ATTRIBUTE_BUCKETCOUNTSTANDARDDEVIATION_TYPE = "float";
    public static final String ATTRIBUTE_INSERTIONRATE_NAME = "insertionRate";
    public static final String ATTRIBUTE_INSERTIONRATE_TYPE = "float";
    public static final String ATTRIBUTE_REMOVALRATE_NAME = "removalRate";
    public static final String ATTRIBUTE_REMOVALRATE_TYPE = "float";
}
