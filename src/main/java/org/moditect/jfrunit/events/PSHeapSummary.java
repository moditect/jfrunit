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
public class PSHeapSummary {
    public static final String EVENT_NAME = "jdk.PSHeapSummary";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_GCID_NAME = "gcId";
    public static final String ATTRIBUTE_GCID_TYPE = "int";
    public static final String ATTRIBUTE_WHEN_NAME = "when";
    public static final String ATTRIBUTE_WHEN_TYPE = "String";
    public static final String ATTRIBUTE_OLDSPACE_NAME = "oldSpace";
    public static final String ATTRIBUTE_OLDSPACE_TYPE = "VirtualSpace";
    public static final String ATTRIBUTE_OLDOBJECTSPACE_NAME = "oldObjectSpace";
    public static final String ATTRIBUTE_OLDOBJECTSPACE_TYPE = "ObjectSpace";
    public static final String ATTRIBUTE_YOUNGSPACE_NAME = "youngSpace";
    public static final String ATTRIBUTE_YOUNGSPACE_TYPE = "VirtualSpace";
    public static final String ATTRIBUTE_EDENSPACE_NAME = "edenSpace";
    public static final String ATTRIBUTE_EDENSPACE_TYPE = "ObjectSpace";
    public static final String ATTRIBUTE_FROMSPACE_NAME = "fromSpace";
    public static final String ATTRIBUTE_FROMSPACE_TYPE = "ObjectSpace";
    public static final String ATTRIBUTE_TOSPACE_NAME = "toSpace";
    public static final String ATTRIBUTE_TOSPACE_TYPE = "ObjectSpace";
}
