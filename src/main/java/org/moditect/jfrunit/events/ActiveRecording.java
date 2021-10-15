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
public class ActiveRecording extends JfrEventType {
    public static final ActiveRecording INSTANCE = new ActiveRecording();
    public static final String EVENT_NAME = "jdk.ActiveRecording";
    public static final Attribute<ActiveRecording, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<ActiveRecording, java.time.Duration> DURATION = new Attribute("duration");
    public static final Attribute<ActiveRecording, org.moditect.jfrunit.ExpectedThread> EVENT_THREAD = new Attribute("eventThread");
    public static final Attribute<ActiveRecording, org.moditect.jfrunit.ExpectedStackTrace> STACK_TRACE = new Attribute("stackTrace");
    public static final Attribute<ActiveRecording, Long> ID = new Attribute("id");
    public static final Attribute<ActiveRecording, java.lang.String> NAME = new Attribute("name");
    public static final Attribute<ActiveRecording, java.lang.String> DESTINATION = new Attribute("destination");
    public static final Attribute<ActiveRecording, java.time.Duration> MAX_AGE = new Attribute("maxAge");
    public static final Attribute<ActiveRecording, java.time.Duration> FLUSH_INTERVAL = new Attribute("flushInterval");
    public static final Attribute<ActiveRecording, Long> MAX_SIZE = new Attribute("maxSize");
    public static final Attribute<ActiveRecording, java.time.Instant> RECORDING_START = new Attribute("recordingStart");
    public static final Attribute<ActiveRecording, java.time.Duration> RECORDING_DURATION = new Attribute("recordingDuration");

    public ActiveRecording() {
        super(EVENT_NAME);
    }
}
