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
public class CodeCacheConfiguration {
    public static final String EVENT_NAME = "jdk.CodeCacheConfiguration";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_INITIALSIZE_NAME = "initialSize";
    public static final String ATTRIBUTE_INITIALSIZE_TYPE = "long";
    public static final String ATTRIBUTE_RESERVEDSIZE_NAME = "reservedSize";
    public static final String ATTRIBUTE_RESERVEDSIZE_TYPE = "long";
    public static final String ATTRIBUTE_NONNMETHODSIZE_NAME = "nonNMethodSize";
    public static final String ATTRIBUTE_NONNMETHODSIZE_TYPE = "long";
    public static final String ATTRIBUTE_PROFILEDSIZE_NAME = "profiledSize";
    public static final String ATTRIBUTE_PROFILEDSIZE_TYPE = "long";
    public static final String ATTRIBUTE_NONPROFILEDSIZE_NAME = "nonProfiledSize";
    public static final String ATTRIBUTE_NONPROFILEDSIZE_TYPE = "long";
    public static final String ATTRIBUTE_EXPANSIONSIZE_NAME = "expansionSize";
    public static final String ATTRIBUTE_EXPANSIONSIZE_TYPE = "long";
    public static final String ATTRIBUTE_MINBLOCKLENGTH_NAME = "minBlockLength";
    public static final String ATTRIBUTE_MINBLOCKLENGTH_TYPE = "long";
    public static final String ATTRIBUTE_STARTADDRESS_NAME = "startAddress";
    public static final String ATTRIBUTE_STARTADDRESS_TYPE = "long";
    public static final String ATTRIBUTE_RESERVEDTOPADDRESS_NAME = "reservedTopAddress";
    public static final String ATTRIBUTE_RESERVEDTOPADDRESS_TYPE = "long";
}
