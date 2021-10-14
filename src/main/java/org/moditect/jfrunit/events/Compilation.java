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
public class Compilation {
    public static final String EVENT_NAME = "jdk.Compilation";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_DURATION_NAME = "duration";
    public static final String ATTRIBUTE_DURATION_TYPE = "long";
    public static final String ATTRIBUTE_EVENTTHREAD_NAME = "eventThread";
    public static final String ATTRIBUTE_EVENTTHREAD_TYPE = "Thread";
    public static final String ATTRIBUTE_COMPILEID_NAME = "compileId";
    public static final String ATTRIBUTE_COMPILEID_TYPE = "int";
    public static final String ATTRIBUTE_COMPILER_NAME = "compiler";
    public static final String ATTRIBUTE_COMPILER_TYPE = "String";
    public static final String ATTRIBUTE_METHOD_NAME = "method";
    public static final String ATTRIBUTE_METHOD_TYPE = "Method";
    public static final String ATTRIBUTE_COMPILELEVEL_NAME = "compileLevel";
    public static final String ATTRIBUTE_COMPILELEVEL_TYPE = "short";
    public static final String ATTRIBUTE_SUCCEDED_NAME = "succeded";
    public static final String ATTRIBUTE_SUCCEDED_TYPE = "boolean";
    public static final String ATTRIBUTE_ISOSR_NAME = "isOsr";
    public static final String ATTRIBUTE_ISOSR_TYPE = "boolean";
    public static final String ATTRIBUTE_CODESIZE_NAME = "codeSize";
    public static final String ATTRIBUTE_CODESIZE_TYPE = "long";
    public static final String ATTRIBUTE_INLINEDBYTES_NAME = "inlinedBytes";
    public static final String ATTRIBUTE_INLINEDBYTES_TYPE = "long";
}
