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
package org.moditect.jfrunit.generator.events.model;

import java.util.Map;

import org.moditect.jfrunit.generator.JfrUnitConstantsGenerator;

import static java.util.Map.entry;

public class Attribute {
    private static Map<String, String> attributeObjToJfrUnitType = Map.ofEntries(
            entry("long", "Long"),
            entry("int", "Integer"),
            entry("Thread", "org.moditect.jfrunit.ExpectedThread"),
            entry("String", "java.lang.String"),
            entry("Class", "org.moditect.jfrunit.ExpectedClass"),
            entry("ClassLoader", "org.moditect.jfrunit.ExpectedClassLoader"),
            entry("Module", "org.moditect.jfrunit.events.model.Module"),
            entry("Package", "org.moditect.jfrunit.events.model.Package"),
            entry("StackFrame", "org.moditect.jfrunit.ExpectedStackFrame"),
            entry("StackTrace", "org.moditect.jfrunit.ExpectedStackTrace"),
            entry("Method", "org.moditect.jfrunit.ExpectedMethod"),
            entry("boolean", "Boolean"),
            entry("byte", "Byte"),
            entry("char", "Character"),
            entry("double", "Double"),
            entry("float", "Float"),
            entry("short", "Short"),
            entry("Timespan", "java.time.Duration"),
            entry("Timestamp", "java.time.Instant"));

    private String name;
    private String type;
    private String contentType;
    private String description;
    private String objectType;
    private String nameUpperCase;
    private String simpleObjectType;
    private String methodName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjectType() {
        if (attributeObjToJfrUnitType.get(getContentType()) != null) {
            return attributeObjToJfrUnitType.get(getContentType());
        }

        return attributeObjToJfrUnitType.get(getType()) != null ? attributeObjToJfrUnitType.get(getType()) : getType();
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getNameUpperCase() {
        return JfrUnitConstantsGenerator.pascalCaseToSnakeCase(name);
    }

    public void setNameUpperCase(String nameUpperCase) {
        this.nameUpperCase = nameUpperCase;
    }

    public String getSimpleObjectType() {
        return getObjectType().contains(".") ? getObjectType().substring(getObjectType().lastIndexOf(".") + 1) : getObjectType();
    }

    public void setSimpleObjectType(String simpleObjectType) {
        this.simpleObjectType = simpleObjectType;
    }

    public String getMethodName() {
        return "with" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
