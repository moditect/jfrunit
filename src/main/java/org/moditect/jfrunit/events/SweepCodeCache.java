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
public class SweepCodeCache {
    public static final String EVENT_NAME = "jdk.SweepCodeCache";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_DURATION_NAME = "duration";
    public static final String ATTRIBUTE_DURATION_TYPE = "long";
    public static final String ATTRIBUTE_EVENTTHREAD_NAME = "eventThread";
    public static final String ATTRIBUTE_EVENTTHREAD_TYPE = "Thread";
    public static final String ATTRIBUTE_SWEEPID_NAME = "sweepId";
    public static final String ATTRIBUTE_SWEEPID_TYPE = "int";
    public static final String ATTRIBUTE_SWEPTCOUNT_NAME = "sweptCount";
    public static final String ATTRIBUTE_SWEPTCOUNT_TYPE = "int";
    public static final String ATTRIBUTE_FLUSHEDCOUNT_NAME = "flushedCount";
    public static final String ATTRIBUTE_FLUSHEDCOUNT_TYPE = "int";
    public static final String ATTRIBUTE_ZOMBIFIEDCOUNT_NAME = "zombifiedCount";
    public static final String ATTRIBUTE_ZOMBIFIEDCOUNT_TYPE = "int";
}
