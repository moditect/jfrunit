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

import org.moditect.jfrunit.Attribute;
import org.moditect.jfrunit.JfrEventType;
import org.moditect.jfrunit.events.model.*;

/**
 * 
 */
public class ClassLoaderStatistics extends JfrEventType {
    public static final ClassLoaderStatistics INSTANCE = new ClassLoaderStatistics();
    public static final String EVENT_NAME = "jdk.ClassLoaderStatistics";
    public static final Attribute<ClassLoaderStatistics, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<ClassLoaderStatistics, org.moditect.jfrunit.ExpectedClassLoader> CLASS_LOADER = new Attribute("classLoader");
    public static final Attribute<ClassLoaderStatistics, org.moditect.jfrunit.ExpectedClassLoader> PARENT_CLASS_LOADER = new Attribute("parentClassLoader");
    public static final Attribute<ClassLoaderStatistics, Long> CLASS_LOADER_DATA = new Attribute("classLoaderData");
    public static final Attribute<ClassLoaderStatistics, Long> CLASS_COUNT = new Attribute("classCount");
    public static final Attribute<ClassLoaderStatistics, Long> CHUNK_SIZE = new Attribute("chunkSize");
    public static final Attribute<ClassLoaderStatistics, Long> BLOCK_SIZE = new Attribute("blockSize");
    public static final Attribute<ClassLoaderStatistics, Long> HIDDEN_CLASS_COUNT = new Attribute("hiddenClassCount");
    public static final Attribute<ClassLoaderStatistics, Long> HIDDEN_CHUNK_SIZE = new Attribute("hiddenChunkSize");
    public static final Attribute<ClassLoaderStatistics, Long> HIDDEN_BLOCK_SIZE = new Attribute("hiddenBlockSize");

    public ClassLoaderStatistics() {
        super(EVENT_NAME);
    }
}
