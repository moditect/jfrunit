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

import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Shared
import spock.lang.Specification

import java.time.Duration

import static dev.morling.jfrunit.ExpectedEvent.event
import static dev.morling.jfrunit.JfrEventsAssert.assertThat

@JfrEventTest
class JfrSpockSharedSpec extends Specification {

    @Rule
    TemporaryFolder temporaryFolder = new TemporaryFolder()

    @Shared
    JfrEvents jfrEvents = new JfrEvents()

    @EnableEvent(value = 'jdk.FileWrite', threshold = 0L)
    def 'records written bytes on each iteration'(int iteration) {
        given:
        long bytesWritten = iteration * 10
        byte[] array = new byte[bytesWritten]
        new Random().nextBytes(array)
        File file = temporaryFolder.newFile()

        when:
        file << array

        then:
        jfrEvents.events.filter{ it.eventType.name == 'jdk.FileWrite' }.count() == iteration
        JfrEventsAssert.assertThat(jfrEvents).contains(
                ExpectedEvent.event('jdk.FileWrite')
                        .with('bytesWritten', bytesWritten)
                        .with('path', file.absolutePath))

        where:
        iteration << [1, 2]
    }

}
