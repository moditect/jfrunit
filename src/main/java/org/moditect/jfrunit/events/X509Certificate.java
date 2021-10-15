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
 * Details of X.509 Certificate parsed by JDK
 */
public class X509Certificate extends JfrEventType {
    public static final X509Certificate INSTANCE = new X509Certificate();
    public static final String EVENT_NAME = "jdk.X509Certificate";
    public static final Attribute<X509Certificate, java.time.Instant> START_TIME = new Attribute("startTime");
    public static final Attribute<X509Certificate, java.time.Duration> DURATION = new Attribute("duration");
    public static final Attribute<X509Certificate, org.moditect.jfrunit.ExpectedThread> EVENT_THREAD = new Attribute("eventThread");
    public static final Attribute<X509Certificate, org.moditect.jfrunit.ExpectedStackTrace> STACK_TRACE = new Attribute("stackTrace");
    public static final Attribute<X509Certificate, java.lang.String> ALGORITHM = new Attribute("algorithm");
    public static final Attribute<X509Certificate, java.lang.String> SERIAL_NUMBER = new Attribute("serialNumber");
    public static final Attribute<X509Certificate, java.lang.String> SUBJECT = new Attribute("subject");
    public static final Attribute<X509Certificate, java.lang.String> ISSUER = new Attribute("issuer");
    public static final Attribute<X509Certificate, java.lang.String> KEY_TYPE = new Attribute("keyType");
    public static final Attribute<X509Certificate, Integer> KEY_LENGTH = new Attribute("keyLength");
    public static final Attribute<X509Certificate, Long> CERTIFICATE_ID = new Attribute("certificateId");
    public static final Attribute<X509Certificate, java.time.Instant> VALID_FROM = new Attribute("validFrom");
    public static final Attribute<X509Certificate, java.time.Instant> VALID_UNTIL = new Attribute("validUntil");

    public X509Certificate() {
        super(EVENT_NAME);
    }
}
