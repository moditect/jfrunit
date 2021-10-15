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
 * Description of JVM and the Java application
 */
public class JVMInformation extends JfrEventType {
    public static final JVMInformation INSTANCE = new JVMInformation();
    public static final String EVENT_NAME = "jdk.JVMInformation";
    public static final Attribute<JVMInformation, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<JVMInformation, java.lang.String> JVM_NAME = new Attribute("jvmName");
    public static final Attribute<JVMInformation, java.lang.String> JVM_VERSION = new Attribute("jvmVersion");
    public static final Attribute<JVMInformation, java.lang.String> JVM_ARGUMENTS = new Attribute("jvmArguments");
    public static final Attribute<JVMInformation, java.lang.String> JVM_FLAGS = new Attribute("jvmFlags");
    public static final Attribute<JVMInformation, java.lang.String> JAVA_ARGUMENTS = new Attribute("javaArguments");
    public static final Attribute<JVMInformation, java.time.Instant> JVM_START_TIME = new Attribute("jvmStartTime");
    public static final Attribute<JVMInformation, Long> PID = new Attribute("pid");

    public JVMInformation() {
        super(EVENT_NAME);
    }
}
