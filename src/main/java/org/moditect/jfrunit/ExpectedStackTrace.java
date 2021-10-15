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

import java.util.Objects;
import java.util.function.Predicate;

import jdk.jfr.consumer.RecordedFrame;
import jdk.jfr.consumer.RecordedStackTrace;

public class ExpectedStackTrace implements Predicate<RecordedStackTrace> {
    public boolean truncated;
    public ExpectedStackFrame frames;

    public ExpectedStackTrace() {
    }

    public ExpectedStackTrace(StackWalker.StackFrame stackFrame, boolean truncated) {
        this.truncated = truncated;
        this.frames = new ExpectedStackFrame(stackFrame);
    }

    public ExpectedStackTrace(StackTraceElement stackTraceElement, boolean truncated) {
        this.truncated = truncated;
        this.frames = new ExpectedStackFrame(stackTraceElement);
    }

    @Override
    public boolean test(RecordedStackTrace recordedStackTrace) {
        if (!Objects.equals(recordedStackTrace.isTruncated(), truncated)) {
            return false;
        }
        for (RecordedFrame recordedFrame : recordedStackTrace.getFrames()) {
            if (frames != null && frames.test(recordedFrame)) {
                return false;
            }
        }

        return true;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    public ExpectedStackFrame getFrames() {
        return frames;
    }

    public void setFrames(ExpectedStackFrame frames) {
        this.frames = frames;
    }
}
