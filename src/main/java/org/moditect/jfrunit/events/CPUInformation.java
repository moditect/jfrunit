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
public class CPUInformation {
    public static final String EVENT_NAME = "jdk.CPUInformation";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_CPU_NAME = "cpu";
    public static final String ATTRIBUTE_CPU_TYPE = "String";
    public static final String ATTRIBUTE_DESCRIPTION_NAME = "description";
    public static final String ATTRIBUTE_DESCRIPTION_TYPE = "String";
    public static final String ATTRIBUTE_SOCKETS_NAME = "sockets";
    public static final String ATTRIBUTE_SOCKETS_TYPE = "int";
    public static final String ATTRIBUTE_CORES_NAME = "cores";
    public static final String ATTRIBUTE_CORES_TYPE = "int";
    public static final String ATTRIBUTE_HWTHREADS_NAME = "hwThreads";
    public static final String ATTRIBUTE_HWTHREADS_TYPE = "int";
}
