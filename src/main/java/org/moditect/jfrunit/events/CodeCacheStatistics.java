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
public class CodeCacheStatistics {
    public static final String EVENT_NAME = "jdk.CodeCacheStatistics";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_CODEBLOBTYPE_NAME = "codeBlobType";
    public static final String ATTRIBUTE_CODEBLOBTYPE_TYPE = "String";
    public static final String ATTRIBUTE_STARTADDRESS_NAME = "startAddress";
    public static final String ATTRIBUTE_STARTADDRESS_TYPE = "long";
    public static final String ATTRIBUTE_RESERVEDTOPADDRESS_NAME = "reservedTopAddress";
    public static final String ATTRIBUTE_RESERVEDTOPADDRESS_TYPE = "long";
    public static final String ATTRIBUTE_ENTRYCOUNT_NAME = "entryCount";
    public static final String ATTRIBUTE_ENTRYCOUNT_TYPE = "int";
    public static final String ATTRIBUTE_METHODCOUNT_NAME = "methodCount";
    public static final String ATTRIBUTE_METHODCOUNT_TYPE = "int";
    public static final String ATTRIBUTE_ADAPTORCOUNT_NAME = "adaptorCount";
    public static final String ATTRIBUTE_ADAPTORCOUNT_TYPE = "int";
    public static final String ATTRIBUTE_UNALLOCATEDCAPACITY_NAME = "unallocatedCapacity";
    public static final String ATTRIBUTE_UNALLOCATEDCAPACITY_TYPE = "long";
    public static final String ATTRIBUTE_FULLCOUNT_NAME = "fullCount";
    public static final String ATTRIBUTE_FULLCOUNT_TYPE = "int";
}
