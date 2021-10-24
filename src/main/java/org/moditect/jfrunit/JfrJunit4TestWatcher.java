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

package org.moditect.jfrunit;

import java.lang.reflect.Method;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class JfrJunit4TestWatcher extends TestWatcher {

    private Object testInstance;

    public JfrJunit4TestWatcher(Object testInstance) {
        this.testInstance = testInstance;
    }

    @Override
    protected void starting(Description description) {
        try {
            Method method = description.getTestClass().getMethod(description.getMethodName());
            JfrTestExtensionUtils.beforeEach(method, getTestInstance());
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        super.starting(description);
    }

    @Override
    protected void finished(Description description) {
        try {
            JfrTestExtensionUtils.afterEach(getTestInstance());
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        super.finished(description);
    }

    public Object getTestInstance() {
        return testInstance;
    }
}
