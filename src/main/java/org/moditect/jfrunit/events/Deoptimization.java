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
public class Deoptimization extends JfrEventType {
    public static final Deoptimization INSTANCE = new Deoptimization();
    public static final String EVENT_NAME = "jdk.Deoptimization";
    public static final Attribute<Deoptimization, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<Deoptimization, org.moditect.jfrunit.ExpectedThread> EVENT_THREAD = new Attribute("eventThread");
    public static final Attribute<Deoptimization, org.moditect.jfrunit.ExpectedStackTrace> STACK_TRACE = new Attribute("stackTrace");
    public static final Attribute<Deoptimization, Integer> COMPILE_ID = new Attribute("compileId");
    public static final Attribute<Deoptimization, java.lang.String> COMPILER = new Attribute("compiler");
    public static final Attribute<Deoptimization, org.moditect.jfrunit.ExpectedMethod> METHOD = new Attribute("method");
    public static final Attribute<Deoptimization, Integer> LINE_NUMBER = new Attribute("lineNumber");
    public static final Attribute<Deoptimization, Integer> BCI = new Attribute("bci");
    public static final Attribute<Deoptimization, java.lang.String> INSTRUCTION = new Attribute("instruction");
    public static final Attribute<Deoptimization, java.lang.String> REASON = new Attribute("reason");
    public static final Attribute<Deoptimization, java.lang.String> ACTION = new Attribute("action");

    public Deoptimization() {
        super(EVENT_NAME);
    }
}
