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
public class CodeCacheFull extends JfrEventType {
    public static final CodeCacheFull INSTANCE = new CodeCacheFull();
    public static final String EVENT_NAME = "jdk.CodeCacheFull";
    public static final Attribute<CodeCacheFull, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<CodeCacheFull, org.moditect.jfrunit.ExpectedThread> EVENT_THREAD = new Attribute("eventThread");
    public static final Attribute<CodeCacheFull, java.lang.String> CODE_BLOB_TYPE = new Attribute("codeBlobType");
    public static final Attribute<CodeCacheFull, Long> START_ADDRESS = new Attribute("startAddress");
    public static final Attribute<CodeCacheFull, Long> COMMITED_TOP_ADDRESS = new Attribute("commitedTopAddress");
    public static final Attribute<CodeCacheFull, Long> RESERVED_TOP_ADDRESS = new Attribute("reservedTopAddress");
    public static final Attribute<CodeCacheFull, Integer> ENTRY_COUNT = new Attribute("entryCount");
    public static final Attribute<CodeCacheFull, Integer> METHOD_COUNT = new Attribute("methodCount");
    public static final Attribute<CodeCacheFull, Integer> ADAPTOR_COUNT = new Attribute("adaptorCount");
    public static final Attribute<CodeCacheFull, Long> UNALLOCATED_CAPACITY = new Attribute("unallocatedCapacity");
    public static final Attribute<CodeCacheFull, Integer> FULL_COUNT = new Attribute("fullCount");

    public CodeCacheFull() {
        super(EVENT_NAME);
    }
}
