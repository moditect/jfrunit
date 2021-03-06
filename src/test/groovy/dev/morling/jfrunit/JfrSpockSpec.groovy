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

import java.time.Duration

import static dev.morling.jfrunit.ExpectedEvent.event
import static dev.morling.jfrunit.JfrEventsAssert.assertThat

@JfrEventTest
class JfrSpockSpec extends Specification {

    JfrEvents jfrEvents = new JfrEvents()

    @EnableEvent('jdk.GarbageCollection')
    @EnableEvent('jdk.ThreadSleep')
    def 'should Have GC And Sleep Events'() {
        when:
        System.gc()
        sleep(1000)

        then:
        assertThat(jfrEvents).contains(event('jdk.GarbageCollection'))
        assertThat(jfrEvents).contains(
                event('jdk.ThreadSleep').with('time', Duration.ofSeconds(1)))
    }

    @EnableEvent('jdk.ThreadSleep')
    def 'should Sleep Events when data driven'(int iteration) {
        given:
        def sleepTime = iteration * 200

        when:
        sleep(sleepTime)

        then:
        jfrEvents.events.filter({it.eventType.name == 'jdk.ThreadSleep' }).count() == 1
        assertThat(jfrEvents).contains(
                event('jdk.ThreadSleep').with('time', Duration.ofMillis(sleepTime)))

        where:
        iteration << [1, 2]
    }
}
