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
package dev.morling.jfrunit

import java.util.stream.Collectors

class JfrEventsExtension {

    static JfrEventsList list(JfrEvents self) {
        return list(self, ExpectedEvent.event(null))
    }

    static JfrEventsList list(JfrEvents self, String eventName) {
        return list(self, ExpectedEvent.event(eventName))
    }

    static JfrEventsList list(JfrEvents self, ExpectedEvent event) {
        return new JfrEventsList(self.stream().collect(Collectors.toList()), event)
    }

    static Object asType(JfrEvents self, Class clazz) {
        if (clazz == List) {
            return list(self)
        }
        return self.asType(clazz)
    }

    static Object getAt(JfrEvents self, String eventName) {
        return list(self, eventName)
    }

}
