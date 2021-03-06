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

import org.assertj.core.api.AbstractAssert;

public class JfrEventsAssert extends AbstractAssert<JfrEventsAssert, JfrEvents> {

    public JfrEventsAssert(JfrEvents actual) {
        super(actual, JfrEventsAssert.class);
    }

    public static JfrEventsAssert assertThat(JfrEvents actual) {
        return new JfrEventsAssert(actual);
    }

    public JfrEventsAssert contains(ExpectedEvent expectedEvent) {
        isNotNull();

        boolean found = actual.getEvents()
            .anyMatch(re -> ExpectedEvent.matches(expectedEvent, re));

        if (!found) {
            if (expectedEvent.getWithProps().isEmpty() && expectedEvent.getHasProps().isEmpty() && expectedEvent.getHasNotProps().isEmpty()) {
                failWithMessage("No JFR event of type <%s>", expectedEvent.getName());
            }
            else if(!expectedEvent.getHasProps().isEmpty()) {
                failWithMessage("No JFR event of type <%s> with attributes <%s>", expectedEvent.getName(), expectedEvent.getHasProps());
            }
            else if(!expectedEvent.getHasNotProps().isEmpty()) {
                failWithMessage("No JFR event of type <%s> without attributes <%s>", expectedEvent.getName(), expectedEvent.getHasNotProps());
            }
            else {
                failWithMessage("No JFR event of type <%s> with attributes <%s>", expectedEvent.getName(), expectedEvent.getWithProps());
            }
        }

        return this;
    }
}
