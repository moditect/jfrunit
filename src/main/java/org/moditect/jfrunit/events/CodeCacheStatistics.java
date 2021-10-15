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
public class CodeCacheStatistics extends JfrEventType {
    public static final CodeCacheStatistics INSTANCE = new CodeCacheStatistics();
    public static final String EVENT_NAME = "jdk.CodeCacheStatistics";
    public static final Attribute<CodeCacheStatistics, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<CodeCacheStatistics, java.lang.String> CODE_BLOB_TYPE = new Attribute("codeBlobType");
    public static final Attribute<CodeCacheStatistics, Long> START_ADDRESS = new Attribute("startAddress");
    public static final Attribute<CodeCacheStatistics, Long> RESERVED_TOP_ADDRESS = new Attribute("reservedTopAddress");
    public static final Attribute<CodeCacheStatistics, Integer> ENTRY_COUNT = new Attribute("entryCount");
    public static final Attribute<CodeCacheStatistics, Integer> METHOD_COUNT = new Attribute("methodCount");
    public static final Attribute<CodeCacheStatistics, Integer> ADAPTOR_COUNT = new Attribute("adaptorCount");
    public static final Attribute<CodeCacheStatistics, Long> UNALLOCATED_CAPACITY = new Attribute("unallocatedCapacity");
    public static final Attribute<CodeCacheStatistics, Integer> FULL_COUNT = new Attribute("fullCount");

    public CodeCacheStatistics() {
        super(EVENT_NAME);
    }
}
