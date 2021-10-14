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
 * Results of deserialization and ObjectInputFilter checks
 */
public class Deserialization {
    public static final String EVENT_NAME = "jdk.Deserialization";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_DURATION_NAME = "duration";
    public static final String ATTRIBUTE_DURATION_TYPE = "long";
    public static final String ATTRIBUTE_EVENTTHREAD_NAME = "eventThread";
    public static final String ATTRIBUTE_EVENTTHREAD_TYPE = "Thread";
    public static final String ATTRIBUTE_STACKTRACE_NAME = "stackTrace";
    public static final String ATTRIBUTE_STACKTRACE_TYPE = "StackTrace";
    public static final String ATTRIBUTE_FILTERCONFIGURED_NAME = "filterConfigured";
    public static final String ATTRIBUTE_FILTERCONFIGURED_TYPE = "boolean";
    public static final String ATTRIBUTE_FILTERSTATUS_NAME = "filterStatus";
    public static final String ATTRIBUTE_FILTERSTATUS_TYPE = "String";
    public static final String ATTRIBUTE_TYPE_NAME = "type";
    public static final String ATTRIBUTE_TYPE_TYPE = "Class";
    public static final String ATTRIBUTE_ARRAYLENGTH_NAME = "arrayLength";
    public static final String ATTRIBUTE_ARRAYLENGTH_TYPE = "int";
    public static final String ATTRIBUTE_OBJECTREFERENCES_NAME = "objectReferences";
    public static final String ATTRIBUTE_OBJECTREFERENCES_TYPE = "long";
    public static final String ATTRIBUTE_DEPTH_NAME = "depth";
    public static final String ATTRIBUTE_DEPTH_TYPE = "long";
    public static final String ATTRIBUTE_BYTESREAD_NAME = "bytesRead";
    public static final String ATTRIBUTE_BYTESREAD_TYPE = "long";
    public static final String ATTRIBUTE_EXCEPTIONTYPE_NAME = "exceptionType";
    public static final String ATTRIBUTE_EXCEPTIONTYPE_TYPE = "Class";
    public static final String ATTRIBUTE_EXCEPTIONMESSAGE_NAME = "exceptionMessage";
    public static final String ATTRIBUTE_EXCEPTIONMESSAGE_TYPE = "String";
}
