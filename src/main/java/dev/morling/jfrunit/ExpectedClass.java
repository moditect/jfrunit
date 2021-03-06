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

import jdk.jfr.consumer.RecordedClass;

import java.util.function.Predicate;

public class ExpectedClass implements Predicate<RecordedClass> {
    private Long id;
    private Integer modifiers;
    private ExpectedClassLoader classLoader;
    private String name;

    public ExpectedClass() {
    }

    public ExpectedClass(Class aClass) {
        this.name = aClass.getName();
        ClassLoader classLoader = aClass.getClassLoader();
        if (classLoader != null) {
            this.classLoader = new ExpectedClassLoader(classLoader);
        }
    }

    @Override
    public boolean test(RecordedClass recordedClass) {
        if (recordedClass == null) {
            return false;
        }
        if (this.id != null && this.id != recordedClass.getId()) {
            return false;
        }
        if (this.classLoader != null && !this.classLoader.test(recordedClass.getClassLoader())) {
            return false;
        }
        if (this.name != null && !this.name.equalsIgnoreCase(recordedClass.getName())) {
            return false;
        }
        if (this.modifiers != null && this.modifiers != recordedClass.getModifiers()) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getModifiers() {
        return modifiers;
    }

    public void setModifiers(Integer modifiers) {
        this.modifiers = modifiers;
    }

    public ExpectedClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ExpectedClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
