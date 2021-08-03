/**
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
package org.moditect.jfrunit

import groovy.transform.PackageScope
import jdk.jfr.consumer.RecordedEvent

import java.util.stream.Collectors

@PackageScope
class JfrEventsList implements List<RecordedEvent> {

    private static final String WITH_PREFIX = 'with'

    private static final String HAVING_PREFIX = 'having'

    private static final String NOT_HAVING_PREFIX = 'notHaving'

    @Delegate
    private final List<RecordedEvent> events

    private final ExpectedEvent expectedEvent

    JfrEventsList(List<RecordedEvent> events, ExpectedEvent expectedEvent) {
        this.events = Collections.unmodifiableList(events.stream().filter(expectedEvent).collect(Collectors.toList()))
        this.expectedEvent = expectedEvent
    }

    def methodMissing(String name, def args) {
        if (name.startsWith(WITH_PREFIX)) {
            String what = name.substring(WITH_PREFIX.length()).uncapitalize()
            if (what && args.length == 1) {
                return new JfrEventsList(events, expectedEvent.with(what, wrapWithArgument(what, args[0])))
            } else if (args.length == 2) {
                return new JfrEventsList(events, expectedEvent.with(args[0], args[1]))
            }
        } else if (name.startsWith(HAVING_PREFIX)) {
            String what = name.substring(HAVING_PREFIX.length()).uncapitalize()
            if (what && args.length == 0) {
                return new JfrEventsList(events, expectedEvent.has(what))
            } else if (args.length == 1) {
                return new JfrEventsList(events, expectedEvent.has(args[0]))
            }
        } else if (name.startsWith(NOT_HAVING_PREFIX)) {
            String what = name.substring(NOT_HAVING_PREFIX.length()).uncapitalize()
            if (what && args.length == 0) {
                return new JfrEventsList(events, expectedEvent.hasNot(what))
            } else if (args.length == 1) {
                return new JfrEventsList(events, expectedEvent.hasNot(args[0]))
            }
        } else if ('containStackFrame' == name && args.length == 1 && args[0] instanceof StackTraceElement) {
            return new JfrEventsList(events, expectedEvent.containStackFrame(new ExpectedStackFrame(args[0])))
        }
        return new JfrEventsList(events, expectedEvent.invokeMethod(name, args))
    }

    private Object wrapWithArgument(String what, Object withArgument) {
        if ('objectClass' == what && withArgument instanceof Class) {
            return new ExpectedClass(withArgument)
        }
        if ('eventThread' == what && withArgument instanceof Thread) {
            return new ExpectedThread(withArgument)
        }
        return withArgument
    }

}
