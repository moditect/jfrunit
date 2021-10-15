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
 * The configuration of the garbage collected heap
 */
public class GCHeapConfiguration extends JfrEventType {
    public static final GCHeapConfiguration INSTANCE = new GCHeapConfiguration();
    public static final String EVENT_NAME = "jdk.GCHeapConfiguration";
    public static final Attribute<GCHeapConfiguration, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<GCHeapConfiguration, Long> MIN_SIZE = new Attribute("minSize");
    public static final Attribute<GCHeapConfiguration, Long> MAX_SIZE = new Attribute("maxSize");
    public static final Attribute<GCHeapConfiguration, Long> INITIAL_SIZE = new Attribute("initialSize");
    public static final Attribute<GCHeapConfiguration, Boolean> USES_COMPRESSED_OOPS = new Attribute("usesCompressedOops");
    public static final Attribute<GCHeapConfiguration, java.lang.String> COMPRESSED_OOPS_MODE = new Attribute("compressedOopsMode");
    public static final Attribute<GCHeapConfiguration, Long> OBJECT_ALIGNMENT = new Attribute("objectAlignment");
    public static final Attribute<GCHeapConfiguration, Byte> HEAP_ADDRESS_BITS = new Attribute("heapAddressBits");

    public GCHeapConfiguration() {
        super(EVENT_NAME);
    }
}
