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
public class MetaspaceChunkFreeListSummary extends JfrEventType {
    public static final MetaspaceChunkFreeListSummary INSTANCE = new MetaspaceChunkFreeListSummary();
    public static final String EVENT_NAME = "jdk.MetaspaceChunkFreeListSummary";
    public static final Attribute<MetaspaceChunkFreeListSummary, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<MetaspaceChunkFreeListSummary, Integer> GC_ID = new Attribute("gcId");
    public static final Attribute<MetaspaceChunkFreeListSummary, java.lang.String> WHEN = new Attribute("when");
    public static final Attribute<MetaspaceChunkFreeListSummary, java.lang.String> METADATA_TYPE = new Attribute("metadataType");
    public static final Attribute<MetaspaceChunkFreeListSummary, Long> SPECIALIZED_CHUNKS = new Attribute("specializedChunks");
    public static final Attribute<MetaspaceChunkFreeListSummary, Long> SPECIALIZED_CHUNKS_TOTAL_SIZE = new Attribute("specializedChunksTotalSize");
    public static final Attribute<MetaspaceChunkFreeListSummary, Long> SMALL_CHUNKS = new Attribute("smallChunks");
    public static final Attribute<MetaspaceChunkFreeListSummary, Long> SMALL_CHUNKS_TOTAL_SIZE = new Attribute("smallChunksTotalSize");
    public static final Attribute<MetaspaceChunkFreeListSummary, Long> MEDIUM_CHUNKS = new Attribute("mediumChunks");
    public static final Attribute<MetaspaceChunkFreeListSummary, Long> MEDIUM_CHUNKS_TOTAL_SIZE = new Attribute("mediumChunksTotalSize");
    public static final Attribute<MetaspaceChunkFreeListSummary, Long> HUMONGOUS_CHUNKS = new Attribute("humongousChunks");
    public static final Attribute<MetaspaceChunkFreeListSummary, Long> HUMONGOUS_CHUNKS_TOTAL_SIZE = new Attribute("humongousChunksTotalSize");

    public MetaspaceChunkFreeListSummary() {
        super(EVENT_NAME);
    }
}
