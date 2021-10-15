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
 * Results of deserialization and ObjectInputFilter checks
 */
public class Deserialization extends JfrEventType {
    public static final Deserialization INSTANCE = new Deserialization();
    public static final String EVENT_NAME = "jdk.Deserialization";
    public static final Attribute<Deserialization, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<Deserialization, java.time.Duration> DURATION = new Attribute("duration");
    public static final Attribute<Deserialization, org.moditect.jfrunit.ExpectedThread> EVENT_THREAD = new Attribute("eventThread");
    public static final Attribute<Deserialization, org.moditect.jfrunit.ExpectedStackTrace> STACK_TRACE = new Attribute("stackTrace");
    public static final Attribute<Deserialization, Boolean> FILTER_CONFIGURED = new Attribute("filterConfigured");
    public static final Attribute<Deserialization, java.lang.String> FILTER_STATUS = new Attribute("filterStatus");
    public static final Attribute<Deserialization, org.moditect.jfrunit.ExpectedClass> TYPE = new Attribute("type");
    public static final Attribute<Deserialization, Integer> ARRAY_LENGTH = new Attribute("arrayLength");
    public static final Attribute<Deserialization, Long> OBJECT_REFERENCES = new Attribute("objectReferences");
    public static final Attribute<Deserialization, Long> DEPTH = new Attribute("depth");
    public static final Attribute<Deserialization, Long> BYTES_READ = new Attribute("bytesRead");
    public static final Attribute<Deserialization, org.moditect.jfrunit.ExpectedClass> EXCEPTION_TYPE = new Attribute("exceptionType");
    public static final Attribute<Deserialization, java.lang.String> EXCEPTION_MESSAGE = new Attribute("exceptionMessage");

    public Deserialization() {
        super(EVENT_NAME);
    }
}
