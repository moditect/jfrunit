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
 * Description of JVM and the Java application
 */
public class JVMInformation {
    public static final String EVENT_NAME = "jdk.JVMInformation";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_JVMNAME_NAME = "jvmName";
    public static final String ATTRIBUTE_JVMNAME_TYPE = "String";
    public static final String ATTRIBUTE_JVMVERSION_NAME = "jvmVersion";
    public static final String ATTRIBUTE_JVMVERSION_TYPE = "String";
    public static final String ATTRIBUTE_JVMARGUMENTS_NAME = "jvmArguments";
    public static final String ATTRIBUTE_JVMARGUMENTS_TYPE = "String";
    public static final String ATTRIBUTE_JVMFLAGS_NAME = "jvmFlags";
    public static final String ATTRIBUTE_JVMFLAGS_TYPE = "String";
    public static final String ATTRIBUTE_JAVAARGUMENTS_NAME = "javaArguments";
    public static final String ATTRIBUTE_JAVAARGUMENTS_TYPE = "String";
    public static final String ATTRIBUTE_JVMSTARTTIME_NAME = "jvmStartTime";
    public static final String ATTRIBUTE_JVMSTARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_PID_NAME = "pid";
    public static final String ATTRIBUTE_PID_TYPE = "long";
}
