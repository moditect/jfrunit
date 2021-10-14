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
 * The configuration of the garbage collector
 */
public class GCConfiguration {
    public static final String EVENT_NAME = "jdk.GCConfiguration";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_YOUNGCOLLECTOR_NAME = "youngCollector";
    public static final String ATTRIBUTE_YOUNGCOLLECTOR_TYPE = "String";
    public static final String ATTRIBUTE_OLDCOLLECTOR_NAME = "oldCollector";
    public static final String ATTRIBUTE_OLDCOLLECTOR_TYPE = "String";
    public static final String ATTRIBUTE_PARALLELGCTHREADS_NAME = "parallelGCThreads";
    public static final String ATTRIBUTE_PARALLELGCTHREADS_TYPE = "int";
    public static final String ATTRIBUTE_CONCURRENTGCTHREADS_NAME = "concurrentGCThreads";
    public static final String ATTRIBUTE_CONCURRENTGCTHREADS_TYPE = "int";
    public static final String ATTRIBUTE_USESDYNAMICGCTHREADS_NAME = "usesDynamicGCThreads";
    public static final String ATTRIBUTE_USESDYNAMICGCTHREADS_TYPE = "boolean";
    public static final String ATTRIBUTE_ISEXPLICITGCCONCURRENT_NAME = "isExplicitGCConcurrent";
    public static final String ATTRIBUTE_ISEXPLICITGCCONCURRENT_TYPE = "boolean";
    public static final String ATTRIBUTE_ISEXPLICITGCDISABLED_NAME = "isExplicitGCDisabled";
    public static final String ATTRIBUTE_ISEXPLICITGCDISABLED_TYPE = "boolean";
    public static final String ATTRIBUTE_PAUSETARGET_NAME = "pauseTarget";
    public static final String ATTRIBUTE_PAUSETARGET_TYPE = "long";
    public static final String ATTRIBUTE_GCTIMERATIO_NAME = "gcTimeRatio";
    public static final String ATTRIBUTE_GCTIMERATIO_TYPE = "int";
}
