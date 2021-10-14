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
 * A potential memory leak
 */
public class OldObjectSample {
    public static final String EVENT_NAME = "jdk.OldObjectSample";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_DURATION_NAME = "duration";
    public static final String ATTRIBUTE_DURATION_TYPE = "long";
    public static final String ATTRIBUTE_EVENTTHREAD_NAME = "eventThread";
    public static final String ATTRIBUTE_EVENTTHREAD_TYPE = "Thread";
    public static final String ATTRIBUTE_STACKTRACE_NAME = "stackTrace";
    public static final String ATTRIBUTE_STACKTRACE_TYPE = "StackTrace";
    public static final String ATTRIBUTE_ALLOCATIONTIME_NAME = "allocationTime";
    public static final String ATTRIBUTE_ALLOCATIONTIME_TYPE = "long";
    public static final String ATTRIBUTE_OBJECTAGE_NAME = "objectAge";
    public static final String ATTRIBUTE_OBJECTAGE_TYPE = "long";
    public static final String ATTRIBUTE_LASTKNOWNHEAPUSAGE_NAME = "lastKnownHeapUsage";
    public static final String ATTRIBUTE_LASTKNOWNHEAPUSAGE_TYPE = "long";
    public static final String ATTRIBUTE_OBJECT_NAME = "object";
    public static final String ATTRIBUTE_OBJECT_TYPE = "OldObject";
    public static final String ATTRIBUTE_ARRAYELEMENTS_NAME = "arrayElements";
    public static final String ATTRIBUTE_ARRAYELEMENTS_TYPE = "int";
    public static final String ATTRIBUTE_ROOT_NAME = "root";
    public static final String ATTRIBUTE_ROOT_TYPE = "OldObjectGcRoot";
}
