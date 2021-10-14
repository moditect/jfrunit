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
public class Deoptimization {
    public static final String EVENT_NAME = "jdk.Deoptimization";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_EVENTTHREAD_NAME = "eventThread";
    public static final String ATTRIBUTE_EVENTTHREAD_TYPE = "Thread";
    public static final String ATTRIBUTE_STACKTRACE_NAME = "stackTrace";
    public static final String ATTRIBUTE_STACKTRACE_TYPE = "StackTrace";
    public static final String ATTRIBUTE_COMPILEID_NAME = "compileId";
    public static final String ATTRIBUTE_COMPILEID_TYPE = "int";
    public static final String ATTRIBUTE_COMPILER_NAME = "compiler";
    public static final String ATTRIBUTE_COMPILER_TYPE = "String";
    public static final String ATTRIBUTE_METHOD_NAME = "method";
    public static final String ATTRIBUTE_METHOD_TYPE = "Method";
    public static final String ATTRIBUTE_LINENUMBER_NAME = "lineNumber";
    public static final String ATTRIBUTE_LINENUMBER_TYPE = "int";
    public static final String ATTRIBUTE_BCI_NAME = "bci";
    public static final String ATTRIBUTE_BCI_TYPE = "int";
    public static final String ATTRIBUTE_INSTRUCTION_NAME = "instruction";
    public static final String ATTRIBUTE_INSTRUCTION_TYPE = "String";
    public static final String ATTRIBUTE_REASON_NAME = "reason";
    public static final String ATTRIBUTE_REASON_TYPE = "String";
    public static final String ATTRIBUTE_ACTION_NAME = "action";
    public static final String ATTRIBUTE_ACTION_TYPE = "String";
}
