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
 * Reading data from a socket
 */
public class SocketRead {
    public static final String EVENT_NAME = "jdk.SocketRead";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_DURATION_NAME = "duration";
    public static final String ATTRIBUTE_DURATION_TYPE = "long";
    public static final String ATTRIBUTE_EVENTTHREAD_NAME = "eventThread";
    public static final String ATTRIBUTE_EVENTTHREAD_TYPE = "Thread";
    public static final String ATTRIBUTE_STACKTRACE_NAME = "stackTrace";
    public static final String ATTRIBUTE_STACKTRACE_TYPE = "StackTrace";
    public static final String ATTRIBUTE_HOST_NAME = "host";
    public static final String ATTRIBUTE_HOST_TYPE = "String";
    public static final String ATTRIBUTE_ADDRESS_NAME = "address";
    public static final String ATTRIBUTE_ADDRESS_TYPE = "String";
    public static final String ATTRIBUTE_PORT_NAME = "port";
    public static final String ATTRIBUTE_PORT_TYPE = "int";
    public static final String ATTRIBUTE_TIMEOUT_NAME = "timeout";
    public static final String ATTRIBUTE_TIMEOUT_TYPE = "long";
    public static final String ATTRIBUTE_BYTESREAD_NAME = "bytesRead";
    public static final String ATTRIBUTE_BYTESREAD_TYPE = "long";
    public static final String ATTRIBUTE_ENDOFSTREAM_NAME = "endOfStream";
    public static final String ATTRIBUTE_ENDOFSTREAM_TYPE = "boolean";
}
