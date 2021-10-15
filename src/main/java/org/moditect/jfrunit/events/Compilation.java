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
public class Compilation extends JfrEventType {
    public static final Compilation INSTANCE = new Compilation();
    public static final String EVENT_NAME = "jdk.Compilation";
    public static final Attribute<Compilation, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<Compilation, java.time.Duration> DURATION = new Attribute("duration");
    public static final Attribute<Compilation, org.moditect.jfrunit.ExpectedThread> EVENT_THREAD = new Attribute("eventThread");
    public static final Attribute<Compilation, Integer> COMPILE_ID = new Attribute("compileId");
    public static final Attribute<Compilation, java.lang.String> COMPILER = new Attribute("compiler");
    public static final Attribute<Compilation, org.moditect.jfrunit.ExpectedMethod> METHOD = new Attribute("method");
    public static final Attribute<Compilation, Short> COMPILE_LEVEL = new Attribute("compileLevel");
    public static final Attribute<Compilation, Boolean> SUCCEDED = new Attribute("succeded");
    public static final Attribute<Compilation, Boolean> IS_OSR = new Attribute("isOsr");
    public static final Attribute<Compilation, Long> CODE_SIZE = new Attribute("codeSize");
    public static final Attribute<Compilation, Long> INLINED_BYTES = new Attribute("inlinedBytes");

    public Compilation() {
        super(EVENT_NAME);
    }
}
