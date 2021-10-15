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
public class CodeCacheConfiguration extends JfrEventType {
    public static final CodeCacheConfiguration INSTANCE = new CodeCacheConfiguration();
    public static final String EVENT_NAME = "jdk.CodeCacheConfiguration";
    public static final Attribute<CodeCacheConfiguration, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<CodeCacheConfiguration, Long> INITIAL_SIZE = new Attribute("initialSize");
    public static final Attribute<CodeCacheConfiguration, Long> RESERVED_SIZE = new Attribute("reservedSize");
    public static final Attribute<CodeCacheConfiguration, Long> NON_N_METHOD_SIZE = new Attribute("nonNMethodSize");
    public static final Attribute<CodeCacheConfiguration, Long> PROFILED_SIZE = new Attribute("profiledSize");
    public static final Attribute<CodeCacheConfiguration, Long> NON_PROFILED_SIZE = new Attribute("nonProfiledSize");
    public static final Attribute<CodeCacheConfiguration, Long> EXPANSION_SIZE = new Attribute("expansionSize");
    public static final Attribute<CodeCacheConfiguration, Long> MIN_BLOCK_LENGTH = new Attribute("minBlockLength");
    public static final Attribute<CodeCacheConfiguration, Long> START_ADDRESS = new Attribute("startAddress");
    public static final Attribute<CodeCacheConfiguration, Long> RESERVED_TOP_ADDRESS = new Attribute("reservedTopAddress");

    public CodeCacheConfiguration() {
        super(EVENT_NAME);
    }
}
