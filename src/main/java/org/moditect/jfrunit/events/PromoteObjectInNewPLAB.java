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
 * Object survived scavenge and was copied to a new Promotion Local Allocation Buffer (PLAB). Supported GCs are Parallel Scavange, G1 and CMS with Parallel New. Due to promotion being done in parallel an object might be reported multiple times as the GC threads race to copy all objects.
 */
public class PromoteObjectInNewPLAB {
    public static final String EVENT_NAME = "jdk.PromoteObjectInNewPLAB";
    public static final String ATTRIBUTE_STARTTIME_NAME = "startTime";
    public static final String ATTRIBUTE_STARTTIME_TYPE = "long";
    public static final String ATTRIBUTE_EVENTTHREAD_NAME = "eventThread";
    public static final String ATTRIBUTE_EVENTTHREAD_TYPE = "Thread";
    public static final String ATTRIBUTE_GCID_NAME = "gcId";
    public static final String ATTRIBUTE_GCID_TYPE = "int";
    public static final String ATTRIBUTE_OBJECTCLASS_NAME = "objectClass";
    public static final String ATTRIBUTE_OBJECTCLASS_TYPE = "Class";
    public static final String ATTRIBUTE_OBJECTSIZE_NAME = "objectSize";
    public static final String ATTRIBUTE_OBJECTSIZE_TYPE = "long";
    public static final String ATTRIBUTE_TENURINGAGE_NAME = "tenuringAge";
    public static final String ATTRIBUTE_TENURINGAGE_TYPE = "int";
    public static final String ATTRIBUTE_TENURED_NAME = "tenured";
    public static final String ATTRIBUTE_TENURED_TYPE = "boolean";
    public static final String ATTRIBUTE_PLABSIZE_NAME = "plabSize";
    public static final String ATTRIBUTE_PLABSIZE_TYPE = "long";
}
