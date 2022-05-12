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

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.moditect.jfrunit.internal.EventConfiguration;

import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.consumer.RecordingFile;

public class JfrFileEvents extends JfrEvents {

    private Path jfrFilePath;

    List<RecordedEvent> events;

    public JfrFileEvents(Path jfrFilePath) {
        this.jfrFilePath = jfrFilePath;
    }

    public void startRecordingEvents(String configurationName, List<EventConfiguration> enabledEvents, Method testMethod, String dumpFileName) {
        try (RecordingFile recordingFile = new RecordingFile(jfrFilePath)) {
            events = new ArrayList<>();
            while (recordingFile.hasMoreEvents()) {
                events.add(recordingFile.readEvent());
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopRecordingEvents() {
        // do nothing
    }

    public void awaitEvents() {
        // do nothing
    }

    public void reset() {
        // do nothing ?
    }

    public Stream<RecordedEvent> stream() {
        return events.stream();
    }

    public Stream<RecordedEvent> events() {
        return stream();
    }
}
