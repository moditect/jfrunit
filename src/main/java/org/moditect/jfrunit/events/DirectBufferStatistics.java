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
 * Statistics of direct buffer
 */
public class DirectBufferStatistics {
    public static final String EVENT_NAME = "jdk.DirectBufferStatistics";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_DURATION_NAME = "duration";
    public static final String ATTRIBUTE_DURATION_TYPE = "long";
    public static final String ATTRIBUTE_EVENTTHREAD_NAME = "eventThread";
    public static final String ATTRIBUTE_EVENTTHREAD_TYPE = "Thread";
    public static final String ATTRIBUTE_STACKTRACE_NAME = "stackTrace";
    public static final String ATTRIBUTE_STACKTRACE_TYPE = "StackTrace";
    public static final String ATTRIBUTE_MAXCAPACITY_NAME = "maxCapacity";
    public static final String ATTRIBUTE_MAXCAPACITY_TYPE = "long";
    public static final String ATTRIBUTE_COUNT_NAME = "count";
    public static final String ATTRIBUTE_COUNT_TYPE = "long";
    public static final String ATTRIBUTE_TOTALCAPACITY_NAME = "totalCapacity";
    public static final String ATTRIBUTE_TOTALCAPACITY_TYPE = "long";
    public static final String ATTRIBUTE_MEMORYUSED_NAME = "memoryUsed";
    public static final String ATTRIBUTE_MEMORYUSED_TYPE = "long";
}
