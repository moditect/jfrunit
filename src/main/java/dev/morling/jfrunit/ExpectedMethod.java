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
package dev.morling.jfrunit;

import jdk.jfr.consumer.RecordedMethod;

import java.lang.reflect.Method;
import java.util.function.Predicate;

public class ExpectedMethod implements Predicate<RecordedMethod> {
    private ExpectedClass type;
    private String name;
    private String descriptor;
    private Integer modifiers;
    private Boolean isHidden;

    public ExpectedMethod() {
    }

    public ExpectedMethod(Method method) {
        this.name = method.getName();
    }

    @Override
    public boolean test(RecordedMethod recordedMethod) {
        if (recordedMethod == null) {
            return false;
        }
        if (this.type != null && !this.type.test(recordedMethod.getType())) {
            return false;
        }
        if (this.name != null && !this.name.equalsIgnoreCase(recordedMethod.getName())) {
            return false;
        }
        if (this.descriptor != null && !this.descriptor.equalsIgnoreCase(recordedMethod.getDescriptor())) {
            return false;
        }
        if (this.modifiers != null && this.modifiers != recordedMethod.getModifiers()) {
            return false;
        }
        if (this.isHidden != null && this.isHidden != recordedMethod.isHidden()) {
            return false;
        }
        return true;
    }

    public ExpectedClass getType() {
        return type;
    }

    public void setType(ExpectedClass type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public Integer getModifiers() {
        return modifiers;
    }

    public void setModifiers(Integer modifiers) {
        this.modifiers = modifiers;
    }

    public Boolean getHidden() {
        return isHidden;
    }

    public void setHidden(Boolean hidden) {
        isHidden = hidden;
    }
}
