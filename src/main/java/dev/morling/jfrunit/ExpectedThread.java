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

import jdk.jfr.consumer.RecordedThread;

import java.util.function.Predicate;

public class ExpectedThread implements Predicate<RecordedThread> {
    private Long id;
    private Long javaThreadId;
    private String javaName;
    private Long osThreadId;
    private String osName;
    private ExpectedThreadGroup expectedThreadGroup;

    public ExpectedThread() {
    }

    public ExpectedThread(Thread thread) {
        this.javaThreadId = thread.getId();
        this.javaName = thread.getName();
        ThreadGroup threadGroup = thread.getThreadGroup();
        if (threadGroup != null) {
            this.expectedThreadGroup = new ExpectedThreadGroup(threadGroup);
        }
    }

    @Override
    public boolean test(RecordedThread recordedThread) {
        if (recordedThread == null) {
            return false;
        }
        if (this.id != null && this.id != recordedThread.getId()) {
            return false;
        }
        if (this.javaThreadId != null && this.javaThreadId != recordedThread.getJavaThreadId()) {
            return false;
        }
        if (this.javaName != null && !this.javaName.equalsIgnoreCase(recordedThread.getJavaName())) {
            return false;
        }
        if (this.osThreadId != null && this.osThreadId != recordedThread.getOSThreadId()) {
            return false;
        }
        if (this.osName != null && !this.osName.equalsIgnoreCase(recordedThread.getOSName())) {
            return false;
        }
        if (this.expectedThreadGroup != null && !this.expectedThreadGroup.test(recordedThread.getThreadGroup())) {
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

    public Long getJavaThreadId() {
        return javaThreadId;
    }

    public void setJavaThreadId(Long javaThreadId) {
        this.javaThreadId = javaThreadId;
    }

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public Long getOsThreadId() {
        return osThreadId;
    }

    public void setOsThreadId(Long osThreadId) {
        this.osThreadId = osThreadId;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public ExpectedThreadGroup getExpectedThreadGroup() {
        return expectedThreadGroup;
    }

    public void setExpectedThreadGroup(ExpectedThreadGroup expectedThreadGroup) {
        this.expectedThreadGroup = expectedThreadGroup;
    }
}
