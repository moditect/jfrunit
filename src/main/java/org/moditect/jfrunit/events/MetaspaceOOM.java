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
public class MetaspaceOOM extends JfrEventType {
    public static final MetaspaceOOM INSTANCE = new MetaspaceOOM();
    public static final String EVENT_NAME = "jdk.MetaspaceOOM";
    public static final Attribute<MetaspaceOOM, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<MetaspaceOOM, org.moditect.jfrunit.ExpectedStackTrace> STACK_TRACE = new Attribute("stackTrace");
    public static final Attribute<MetaspaceOOM, org.moditect.jfrunit.ExpectedClassLoader> CLASS_LOADER = new Attribute("classLoader");
    public static final Attribute<MetaspaceOOM, Boolean> HIDDEN_CLASS_LOADER = new Attribute("hiddenClassLoader");
    public static final Attribute<MetaspaceOOM, Long> SIZE = new Attribute("size");
    public static final Attribute<MetaspaceOOM, java.lang.String> METADATA_TYPE = new Attribute("metadataType");
    public static final Attribute<MetaspaceOOM, java.lang.String> METASPACE_OBJECT_TYPE = new Attribute("metaspaceObjectType");

    public MetaspaceOOM() {
        super(EVENT_NAME);
    }
}
