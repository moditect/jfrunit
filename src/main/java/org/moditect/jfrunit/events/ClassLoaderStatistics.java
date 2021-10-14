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
public class ClassLoaderStatistics {
    public static final String EVENT_NAME = "jdk.ClassLoaderStatistics";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_CLASSLOADER_NAME = "classLoader";
    public static final String ATTRIBUTE_CLASSLOADER_TYPE = "ClassLoader";
    public static final String ATTRIBUTE_PARENTCLASSLOADER_NAME = "parentClassLoader";
    public static final String ATTRIBUTE_PARENTCLASSLOADER_TYPE = "ClassLoader";
    public static final String ATTRIBUTE_CLASSLOADERDATA_NAME = "classLoaderData";
    public static final String ATTRIBUTE_CLASSLOADERDATA_TYPE = "long";
    public static final String ATTRIBUTE_CLASSCOUNT_NAME = "classCount";
    public static final String ATTRIBUTE_CLASSCOUNT_TYPE = "long";
    public static final String ATTRIBUTE_CHUNKSIZE_NAME = "chunkSize";
    public static final String ATTRIBUTE_CHUNKSIZE_TYPE = "long";
    public static final String ATTRIBUTE_BLOCKSIZE_NAME = "blockSize";
    public static final String ATTRIBUTE_BLOCKSIZE_TYPE = "long";
    public static final String ATTRIBUTE_HIDDENCLASSCOUNT_NAME = "hiddenClassCount";
    public static final String ATTRIBUTE_HIDDENCLASSCOUNT_TYPE = "long";
    public static final String ATTRIBUTE_HIDDENCHUNKSIZE_NAME = "hiddenChunkSize";
    public static final String ATTRIBUTE_HIDDENCHUNKSIZE_TYPE = "long";
    public static final String ATTRIBUTE_HIDDENBLOCKSIZE_NAME = "hiddenBlockSize";
    public static final String ATTRIBUTE_HIDDENBLOCKSIZE_TYPE = "long";
}
