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
 * Waiting on a Java monitor
 */
public class JavaMonitorWait extends JfrEventType {
    public static final JavaMonitorWait INSTANCE = new JavaMonitorWait();
    public static final String EVENT_NAME = "jdk.JavaMonitorWait";
    public static final Attribute<JavaMonitorWait, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<JavaMonitorWait, java.time.Duration> DURATION = new Attribute("duration");
    public static final Attribute<JavaMonitorWait, org.moditect.jfrunit.ExpectedThread> EVENT_THREAD = new Attribute("eventThread");
    public static final Attribute<JavaMonitorWait, org.moditect.jfrunit.ExpectedStackTrace> STACK_TRACE = new Attribute("stackTrace");
    public static final Attribute<JavaMonitorWait, org.moditect.jfrunit.ExpectedClass> MONITOR_CLASS = new Attribute("monitorClass");
    public static final Attribute<JavaMonitorWait, org.moditect.jfrunit.ExpectedThread> NOTIFIER = new Attribute("notifier");
    public static final Attribute<JavaMonitorWait, java.time.Duration> TIMEOUT = new Attribute("timeout");
    public static final Attribute<JavaMonitorWait, Boolean> TIMED_OUT = new Attribute("timedOut");
    public static final Attribute<JavaMonitorWait, Long> ADDRESS = new Attribute("address");

    public JavaMonitorWait() {
        super(EVENT_NAME);
    }
}
