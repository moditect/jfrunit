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

import groovy.transform.CompileStatic
import spock.lang.Specification

@JfrEventTest
class TLABRelatedSpec extends Specification {

    private static final int BYTE_ARRAY_OVERHEAD = 16
    private static final int OBJECT_SIZE = 102400
    public static byte[] tmp

    JfrEvents jfrEvents = new JfrEvents()

    @EnableEvent('jdk.ObjectAllocationOutsideTLAB')
    @EnableEvent('jdk.ObjectAllocationInNewTLAB')
    def 'test slow allocation'() {
        when:
        System.gc()
        StackTraceElement stackTraceElement = allocate()

        then:
        jfrEvents.list('jdk.ObjectAllocationOutsideTLAB')
        jfrEvents.list('jdk.ObjectAllocationInNewTLAB')

        jfrEvents.list('jdk.ObjectAllocationOutsideTLAB')
                .withAllocationSize((double) OBJECT_SIZE)
                .withObjectClass(new ExpectedClass(byte[].class))
                .withEventThread(new ExpectedThread(Thread.currentThread()))
                .containStackFrame(new ExpectedStackFrame(stackTraceElement))
    }

    @CompileStatic
    private static StackTraceElement allocate() {
        (1..512).each {
            tmp = new byte[OBJECT_SIZE - BYTE_ARRAY_OVERHEAD]
        }
        return new Exception().getStackTrace()[0]
    }
}
