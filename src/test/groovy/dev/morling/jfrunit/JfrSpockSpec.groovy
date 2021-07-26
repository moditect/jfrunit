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
import spock.lang.Specification

import java.time.Duration

class JfrSpockSpec extends Specification {

    @Rule
    TemporaryFolder temporaryFolder = new TemporaryFolder()

    JfrEvents jfrEvents = new JfrEvents()

    @EnableEvent('jdk.GarbageCollection')
    @EnableEvent('jdk.ThreadSleep')
    def 'should Have GC And Sleep Events'() {
        when:
        System.gc()
        sleep(50)

        then:
        jfrEvents.list('jdk.GarbageCollection')
        jfrEvents.list('jdk.ThreadSleep').withTime(Duration.ofMillis(50))
    }

    @EnableConfiguration("profile")
    def 'Should have Gc and Sleep events recorded when enabled with configuration \'profile\''() {
        when:
        System.gc()
        sleep(50)

        then:
        jfrEvents.list('jdk.GarbageCollection')
        jfrEvents.list('jdk.GarbageCollection').withCause('System.gc()').size() == 1
        jfrEvents.list('jdk.ThreadSleep').withTime(Duration.ofMillis(50))
        jfrEvents.list('jdk.GarbageCollection').findAll {it.cause == 'System.gc()' }.size() == 1
        jfrEvents['jdk.GarbageCollection'].withCause('System.gc()').size() == 1
    }

    @EnableEvent('jdk.ThreadSleep')
    def 'Should have StackTrace captured for StackTrace-Enabled Events by default with StackTrace policy Default'() {
        when:
        sleep(50)

        then:
        jfrEvents.list('jdk.ThreadSleep').havingStackTrace()
    }

    @EnableEvent('jfrunit.test.StackTraceDisabledSampleEvent')
    def 'Should not have StackTrace captured for StackTrace-Disabled Events by default with StackTrace policy Default'() {
        given:
        StackTraceDisabledSampleEvent event = new StackTraceDisabledSampleEvent()

        when:
        event.commit()

        then:
        jfrEvents.list('jfrunit.test.StackTraceDisabledSampleEvent').notHavingStackTrace()
        jfrEvents.list().notHavingStackTrace()
    }

    @EnableEvent(value = 'jdk.ThreadSleep', stackTrace = EnableEvent.StacktracePolicy.INCLUDED)
    def 'Should have StackTrace captured irrespective of Event StackTrace Configuration(Enabled) with StackTrace policy Included'() {
        when:
        sleep(50)

        then:
        jfrEvents.list('jdk.ThreadSleep').havingStackTrace()
        jfrEvents.list().havingStackTrace()
    }

    @EnableEvent(value = 'jfrunit.test.StackTraceDisabledSampleEvent', stackTrace = EnableEvent.StacktracePolicy.INCLUDED)
    def 'Should have StackTrace captured irrespective of Event StackTrace Configuration(Disabled) with StackTrace policy Included'() {
        given:
        StackTraceDisabledSampleEvent event = new StackTraceDisabledSampleEvent()

        when:
        event.commit()

        then:
        jfrEvents.list('jfrunit.test.StackTraceDisabledSampleEvent').havingStackTrace()
    }

    @EnableEvent(value = 'jdk.ThreadSleep', stackTrace = EnableEvent.StacktracePolicy.EXCLUDED)
    def 'Should not have StackTrace captured irrespective of Event StackTrace Configuration(Enabled) with StackTrace policy Excluded'() {
        when:
        sleep(50)

        then:
        jfrEvents.list('jdk.ThreadSleep').notHavingStackTrace()
    }

    @EnableEvent(value = 'jfrunit.test.StackTraceDisabledSampleEvent', stackTrace = EnableEvent.StacktracePolicy.EXCLUDED)
    def 'Should not have StackTrace captured irrespective of Event StackTrace Configuration(Disabled) with StackTrace policy Excluded'() {
        given:
        StackTraceDisabledSampleEvent event = new StackTraceDisabledSampleEvent()

        when:
        event.commit();

        then:
        jfrEvents.list('jfrunit.test.StackTraceDisabledSampleEvent').notHavingStackTrace()
    }

    @EnableEvent(value = 'jdk.FileWrite', threshold = 0L)
    def 'should record written bytes on each iteration'(int iteration) {
        given:
        long bytesWritten = iteration * 10
        byte[] array = new byte[bytesWritten]
        new Random().nextBytes(array)
        File file = temporaryFolder.newFile()

        when:
        file << array

        then:
        jfrEvents.list('jdk.FileWrite').size() == 1
        jfrEvents.list('jdk.FileWrite').withBytesWritten(bytesWritten).withPath(file.absolutePath)

        where:
        iteration << [1, 2]
    }

}
