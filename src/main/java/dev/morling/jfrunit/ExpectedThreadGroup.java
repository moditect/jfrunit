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

import jdk.jfr.consumer.RecordedThreadGroup;

import java.util.function.Predicate;

public class ExpectedThreadGroup implements Predicate<RecordedThreadGroup> {
    private String name;
    private ExpectedThreadGroup parent;

    public ExpectedThreadGroup() {
    }

    public ExpectedThreadGroup(ThreadGroup threadGroup) {
        this.name = threadGroup.getName();
        ThreadGroup parent = threadGroup.getParent();
        if (parent != null) {
            this.parent = new ExpectedThreadGroup(parent);
        }
    }

    @Override
    public boolean test(RecordedThreadGroup recordedThreadGroup) {
        if (recordedThreadGroup == null) {
            return false;
        }
        if (this.name != null && !this.name.equalsIgnoreCase(recordedThreadGroup.getName())) {
            return false;
        }
        if (this.parent != null && !this.parent.test(recordedThreadGroup.getParent())) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExpectedThreadGroup getParent() {
        return parent;
    }

    public void setParent(ExpectedThreadGroup parent) {
        this.parent = parent;
    }
}
