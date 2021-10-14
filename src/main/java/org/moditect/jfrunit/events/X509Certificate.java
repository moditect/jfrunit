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

/**
 * Details of X.509 Certificate parsed by JDK
 */
public class X509Certificate {
    public static final String EVENT_NAME = "jdk.X509Certificate";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_DURATION_NAME = "duration";
    public static final String ATTRIBUTE_DURATION_TYPE = "long";
    public static final String ATTRIBUTE_EVENTTHREAD_NAME = "eventThread";
    public static final String ATTRIBUTE_EVENTTHREAD_TYPE = "Thread";
    public static final String ATTRIBUTE_STACKTRACE_NAME = "stackTrace";
    public static final String ATTRIBUTE_STACKTRACE_TYPE = "StackTrace";
    public static final String ATTRIBUTE_ALGORITHM_NAME = "algorithm";
    public static final String ATTRIBUTE_ALGORITHM_TYPE = "String";
    public static final String ATTRIBUTE_SERIALNUMBER_NAME = "serialNumber";
    public static final String ATTRIBUTE_SERIALNUMBER_TYPE = "String";
    public static final String ATTRIBUTE_SUBJECT_NAME = "subject";
    public static final String ATTRIBUTE_SUBJECT_TYPE = "String";
    public static final String ATTRIBUTE_ISSUER_NAME = "issuer";
    public static final String ATTRIBUTE_ISSUER_TYPE = "String";
    public static final String ATTRIBUTE_KEYTYPE_NAME = "keyType";
    public static final String ATTRIBUTE_KEYTYPE_TYPE = "String";
    public static final String ATTRIBUTE_KEYLENGTH_NAME = "keyLength";
    public static final String ATTRIBUTE_KEYLENGTH_TYPE = "int";
    public static final String ATTRIBUTE_CERTIFICATEID_NAME = "certificateId";
    public static final String ATTRIBUTE_CERTIFICATEID_TYPE = "long";
    public static final String ATTRIBUTE_VALIDFROM_NAME = "validFrom";
    public static final String ATTRIBUTE_VALIDFROM_TYPE = "long";
    public static final String ATTRIBUTE_VALIDUNTIL_NAME = "validUntil";
    public static final String ATTRIBUTE_VALIDUNTIL_TYPE = "long";
}
