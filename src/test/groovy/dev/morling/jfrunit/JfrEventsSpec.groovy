/**
 *  Copyright 2020 - 2021 The JfrUnit authors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package dev.morling.jfrunit

import spock.lang.Specification

import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class JfrEventsSpec extends Specification {

    def 'does not block on stream() when capturing is not running'() {
        given:
        JfrEvents jfrEvents = new JfrEvents()
        CountDownLatch latch = new CountDownLatch(1)

        when:
        Executors.newFixedThreadPool(1).submit({
            jfrEvents.stream()
            latch.countDown()
        })

        then:
        latch.await(1, TimeUnit.SECONDS)
    }

}
