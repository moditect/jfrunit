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

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.moditect.jfrunit.events.model.CalleeMethod;
import org.moditect.jfrunit.events.model.CopyFailed;
import org.moditect.jfrunit.events.model.G1EvacuationStatistics;
import org.moditect.jfrunit.events.model.MetaspaceSizes;
import org.moditect.jfrunit.events.model.Module;
import org.moditect.jfrunit.events.model.ObjectSpace;
import org.moditect.jfrunit.events.model.OldObject;
import org.moditect.jfrunit.events.model.OldObjectGcRoot;
import org.moditect.jfrunit.events.model.Package;
import org.moditect.jfrunit.events.model.VirtualSpace;

import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.consumer.RecordedFrame;
import jdk.jfr.consumer.RecordedStackTrace;

public abstract class JfrEventType {
    private String name;
    private List<JfrPredicate> predicates = new ArrayList();

    protected JfrEventType(String name) {
        this.name = name;
    }

    protected static boolean matches(Instant expected, String name, RecordedEvent recordedEvent) {
        return Objects.equals(recordedEvent.getInstant(name), expected);
    }

    protected static boolean matches(Duration expected, String name, RecordedEvent recordedEvent) {
        return Objects.equals(recordedEvent.getDuration(name), expected);
    }

    protected static boolean matches(Long expected, String name, RecordedEvent recordedEvent) {
        return Objects.equals(recordedEvent.getLong(name), expected);
    }

    protected static boolean matches(Integer expected, String name, RecordedEvent recordedEvent) {
        return Objects.equals(recordedEvent.getInt(name), expected);
    }

    protected static boolean matches(Boolean expected, String name, RecordedEvent recordedEvent) {
        return Objects.equals(recordedEvent.getBoolean(name), expected);
    }

    protected static boolean matches(Byte expected, String name, RecordedEvent recordedEvent) {
        return Objects.equals(recordedEvent.getByte(name), expected);
    }

    protected static boolean matches(Character expected, String name, RecordedEvent recordedEvent) {
        return Objects.equals(recordedEvent.getChar(name), expected);
    }

    protected static boolean matches(Double expected, String name, RecordedEvent recordedEvent) {
        return Objects.equals(recordedEvent.getDouble(name), expected);
    }

    protected static boolean matches(Float expected, String name, RecordedEvent recordedEvent) {
        return Objects.equals(recordedEvent.getFloat(name), expected);
    }

    protected static boolean matches(Short expected, String name, RecordedEvent recordedEvent) {
        return Objects.equals(recordedEvent.getShort(name), expected);
    }

    protected static boolean matches(String expected, String name, RecordedEvent recordedEvent) {
        return Objects.equals(recordedEvent.getString(name), expected);
    }

    protected static boolean matches(ExpectedThread expected, String name, RecordedEvent recordedEvent) {
        return expected.test(recordedEvent.getThread(name));
    }

    protected static boolean matches(ExpectedClass expected, String name, RecordedEvent recordedEvent) {
        return expected.test(recordedEvent.getClass(name));
    }

    protected static boolean matches(ExpectedStackFrame expected, String name, RecordedEvent recordedEvent) {
        RecordedStackTrace stackTrace = recordedEvent.getStackTrace();
        if (stackTrace == null) {
            return false;
        }
        List<RecordedFrame> recordedFrames = stackTrace.getFrames();
        if (recordedFrames.stream().noneMatch(expected::test)) {
            return false;
        }

        return true;
    }

    protected static boolean matches(ExpectedClassLoader expected, String name, RecordedEvent recordedEvent) {
        throw new UnsupportedOperationException("matches ExpectedClassLoader not implemented");
    }

    protected static boolean matches(Module expected, String name, RecordedEvent recordedEvent) {
        throw new UnsupportedOperationException("matches Module not implemented");
    }

    protected static boolean matches(Package expected, String name, RecordedEvent recordedEvent) {
        throw new UnsupportedOperationException("matches Package not implemented");
    }

    protected static boolean matches(ExpectedStackTrace expected, String name, RecordedEvent recordedEvent) {
        RecordedStackTrace stackTrace = recordedEvent.getStackTrace();
        if (stackTrace == null) {
            return false;
        }

        return expected.test(stackTrace);
    }

    protected static boolean matches(ExpectedMethod expected, String name, RecordedEvent recordedEvent) {
        throw new UnsupportedOperationException("matches ExpectedMethod not implemented");
    }

    protected static boolean matches(CalleeMethod expected, String name, RecordedEvent recordedEvent) {
        throw new UnsupportedOperationException("matches CalleeMethod not implemented");
    }

    protected static boolean matches(CopyFailed expected, String name, RecordedEvent recordedEvent) {
        throw new UnsupportedOperationException("matches CopyFailed not implemented");
    }

    protected static boolean matches(G1EvacuationStatistics expected, String name, RecordedEvent recordedEvent) {
        throw new UnsupportedOperationException("matches G1EvacuationStatistics not implemented");
    }

    protected static boolean matches(MetaspaceSizes expected, String name, RecordedEvent recordedEvent) {
        throw new UnsupportedOperationException("matches OldObject not implemented");
    }

    protected static boolean matches(OldObject expected, String name, RecordedEvent recordedEvent) {
        throw new UnsupportedOperationException("matches OldObject not implemented");
    }

    protected static boolean matches(OldObjectGcRoot expected, String name, RecordedEvent recordedEvent) {
        throw new UnsupportedOperationException("matches OldObjectGcRoot not implemented");
    }

    protected static boolean matches(VirtualSpace expected, String name, RecordedEvent recordedEvent) {
        throw new UnsupportedOperationException("matches VirtualSpace not implemented");
    }

    protected static boolean matches(ObjectSpace expected, String name, RecordedEvent recordedEvent) {
        throw new UnsupportedOperationException("matches ObjectSpace not implemented");
    }

    public final String getName() {
        return name;
    }

    public List<JfrPredicate> getPredicates() {
        return predicates;
    }
}
