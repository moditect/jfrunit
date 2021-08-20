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
package org.moditect.jfrunit.internal;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Future;

import org.openjdk.jmc.common.item.IItemCollection;
import org.openjdk.jmc.flightrecorder.CouldNotLoadRecordingException;
import org.openjdk.jmc.flightrecorder.JfrLoaderToolkit;
import org.openjdk.jmc.flightrecorder.rules.IResult;
import org.openjdk.jmc.flightrecorder.rules.IRule;
import org.openjdk.jmc.flightrecorder.rules.RuleRegistry;
import org.openjdk.jmc.flightrecorder.rules.Severity;
import org.openjdk.jmc.flightrecorder.rules.util.RulesToolkit;

public class JmcAutomaticAnalysis {

    private static final System.Logger LOGGER = System.getLogger(JmcAutomaticAnalysis.class.getName());

    public static List<IResult> analysisRecording(String fileName, Severity minSeverity) {
        try {
            File file = new File(fileName);

            IItemCollection events;
            try {
                events = JfrLoaderToolkit.loadEvents(file);
            }
            catch (IOException | CouldNotLoadRecordingException e) {
                LOGGER.log(System.Logger.Level.ERROR, "Unable to analyse jfr recording: " + e.getLocalizedMessage());
                return null;
            }
            return analyseEvents(events, minSeverity);

        }
        catch (Throwable t) {
            System.err.println("Got exception when creating report for " + fileName); //$NON-NLS-1$
            throw t;
        }
    }

    public static List<IResult> analyseEvents(IItemCollection events, Severity minSeverity) {
        // TODO: Provide configuration
        Map<IRule, Future<IResult>> resultFutures = RulesToolkit.evaluateParallel(RuleRegistry.getRules(), events,
                null, 0);
        List<Map.Entry<IRule, Future<IResult>>> resultFutureList = new ArrayList<>(resultFutures.entrySet());
        Collections.sort(resultFutureList, Comparator.comparing(o -> o.getKey().getId()));

        List<IResult> analysisResults = new ArrayList();

        for (Map.Entry<IRule, Future<IResult>> resultEntry : resultFutureList) {
            IResult result;
            try {
                result = resultEntry.getValue().get();
            }
            catch (Throwable t) {
                LOGGER.log(System.Logger.Level.WARNING, "Unable to analyse analysis result: " + t.getLocalizedMessage());
                continue;
            }

            if (result != null && result.getSeverity().compareTo(minSeverity) >= 0) {
                analysisResults.add(result);
            }
        }
        return analysisResults;

    }

}
