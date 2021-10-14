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
public class CompilerStatistics {
    public static final String EVENT_NAME = "jdk.CompilerStatistics";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_COMPILECOUNT_NAME = "compileCount";
    public static final String ATTRIBUTE_COMPILECOUNT_TYPE = "int";
    public static final String ATTRIBUTE_BAILOUTCOUNT_NAME = "bailoutCount";
    public static final String ATTRIBUTE_BAILOUTCOUNT_TYPE = "int";
    public static final String ATTRIBUTE_INVALIDATEDCOUNT_NAME = "invalidatedCount";
    public static final String ATTRIBUTE_INVALIDATEDCOUNT_TYPE = "int";
    public static final String ATTRIBUTE_OSRCOMPILECOUNT_NAME = "osrCompileCount";
    public static final String ATTRIBUTE_OSRCOMPILECOUNT_TYPE = "int";
    public static final String ATTRIBUTE_STANDARDCOMPILECOUNT_NAME = "standardCompileCount";
    public static final String ATTRIBUTE_STANDARDCOMPILECOUNT_TYPE = "int";
    public static final String ATTRIBUTE_OSRBYTESCOMPILED_NAME = "osrBytesCompiled";
    public static final String ATTRIBUTE_OSRBYTESCOMPILED_TYPE = "long";
    public static final String ATTRIBUTE_STANDARDBYTESCOMPILED_NAME = "standardBytesCompiled";
    public static final String ATTRIBUTE_STANDARDBYTESCOMPILED_TYPE = "long";
    public static final String ATTRIBUTE_NMETHODSSIZE_NAME = "nmethodsSize";
    public static final String ATTRIBUTE_NMETHODSSIZE_TYPE = "long";
    public static final String ATTRIBUTE_NMETHODCODESIZE_NAME = "nmethodCodeSize";
    public static final String ATTRIBUTE_NMETHODCODESIZE_TYPE = "long";
    public static final String ATTRIBUTE_PEAKTIMESPENT_NAME = "peakTimeSpent";
    public static final String ATTRIBUTE_PEAKTIMESPENT_TYPE = "long";
    public static final String ATTRIBUTE_TOTALTIMESPENT_NAME = "totalTimeSpent";
    public static final String ATTRIBUTE_TOTALTIMESPENT_TYPE = "long";
}
