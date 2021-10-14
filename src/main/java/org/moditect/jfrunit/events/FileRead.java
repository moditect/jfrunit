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
 * Reading data from a file
 */
public class FileRead {
    public static final String EVENT_NAME = "jdk.FileRead";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_DURATION_NAME = "duration";
    public static final String ATTRIBUTE_DURATION_TYPE = "long";
    public static final String ATTRIBUTE_EVENTTHREAD_NAME = "eventThread";
    public static final String ATTRIBUTE_EVENTTHREAD_TYPE = "Thread";
    public static final String ATTRIBUTE_STACKTRACE_NAME = "stackTrace";
    public static final String ATTRIBUTE_STACKTRACE_TYPE = "StackTrace";
    public static final String ATTRIBUTE_PATH_NAME = "path";
    public static final String ATTRIBUTE_PATH_TYPE = "String";
    public static final String ATTRIBUTE_BYTESREAD_NAME = "bytesRead";
    public static final String ATTRIBUTE_BYTESREAD_TYPE = "long";
    public static final String ATTRIBUTE_ENDOFFILE_NAME = "endOfFile";
    public static final String ATTRIBUTE_ENDOFFILE_TYPE = "boolean";
}
