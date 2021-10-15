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
public class MetaspaceSummary extends JfrEventType {
    public static final MetaspaceSummary INSTANCE = new MetaspaceSummary();
    public static final String EVENT_NAME = "jdk.MetaspaceSummary";
    public static final Attribute<MetaspaceSummary, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<MetaspaceSummary, Integer> GC_ID = new Attribute("gcId");
    public static final Attribute<MetaspaceSummary, java.lang.String> WHEN = new Attribute("when");
    public static final Attribute<MetaspaceSummary, Long> GC_THRESHOLD = new Attribute("gcThreshold");
    public static final Attribute<MetaspaceSummary, MetaspaceSizes> METASPACE = new Attribute("metaspace");
    public static final Attribute<MetaspaceSummary, MetaspaceSizes> DATA_SPACE = new Attribute("dataSpace");
    public static final Attribute<MetaspaceSummary, MetaspaceSizes> CLASS_SPACE = new Attribute("classSpace");

    public MetaspaceSummary() {
        super(EVENT_NAME);
    }
}
