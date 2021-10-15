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
 * Information about a Shenandoah heap region state change
 */
public class ShenandoahHeapRegionStateChange extends JfrEventType {
    public static final ShenandoahHeapRegionStateChange INSTANCE = new ShenandoahHeapRegionStateChange();
    public static final String EVENT_NAME = "jdk.ShenandoahHeapRegionStateChange";
    public static final Attribute<ShenandoahHeapRegionStateChange, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<ShenandoahHeapRegionStateChange, Integer> INDEX = new Attribute("index");
    public static final Attribute<ShenandoahHeapRegionStateChange, java.lang.String> FROM = new Attribute("from");
    public static final Attribute<ShenandoahHeapRegionStateChange, java.lang.String> TO = new Attribute("to");
    public static final Attribute<ShenandoahHeapRegionStateChange, Long> START = new Attribute("start");
    public static final Attribute<ShenandoahHeapRegionStateChange, Long> USED = new Attribute("used");

    public ShenandoahHeapRegionStateChange() {
        super(EVENT_NAME);
    }
}
