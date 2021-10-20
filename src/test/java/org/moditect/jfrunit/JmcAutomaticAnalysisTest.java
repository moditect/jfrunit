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

import java.util.List;

import javax.el.MethodNotFoundException;

import org.junit.jupiter.api.Test;
import org.openjdk.jmc.flightrecorder.rules.IResult;
import org.openjdk.jmc.flightrecorder.rules.Severity;
import org.openjdk.jmc.flightrecorder.rules.jdk.general.StackDepthSettingRule;
import org.openjdk.jmc.flightrecorder.rules.jdk.memory.FullGcRule;
import org.openjdk.jmc.flightrecorder.rules.jdk.memory.HeapDumpRule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@JfrEventTest
public class JmcAutomaticAnalysisTest {

    public JfrEvents jfrEvents = new JfrEvents();

    @Test
    @EnableConfiguration("profile")
    public void automatedAnalysis() throws Exception {

        System.gc();
        Thread.sleep(1000);

        jfrEvents.awaitEvents();

        List<IResult> analysisResults = jfrEvents.automaticAnalysis();

        assertNotNull(analysisResults);

        assertThat(analysisResults.size()).isGreaterThan(0);

        // Inspect rules that fired
        JmcAutomaticAnalysisAssert.assertThat(analysisResults).contains(FullGcRule.class);
        JmcAutomaticAnalysisAssert.assertThat(analysisResults).doesNotContain(HeapDumpRule.class);

        // Inspect severity of rule
        JmcAutomaticAnalysisAssert.assertThat(analysisResults).hasSeverity(FullGcRule.class, Severity.WARNING);

        // Inspect score of rule
        JmcAutomaticAnalysisAssert.assertThat(analysisResults)
                .contains(FullGcRule.class)
                .scoresLessThan(80);

    }

    @Test
    @EnableConfiguration("profile")
    public void automatedExceptionAnalysis() throws Exception {

        for (int i = 0; i < 20_000; i++) {
            try {
                throw new MethodNotFoundException();
            }
            catch (MethodNotFoundException methodNotFoundException) {
                // silently swallow exception
            }
        }

        List<IResult> analysisResults = jfrEvents.automaticAnalysis();

        assertThat(analysisResults.size()).isGreaterThan(0);

        JmcAutomaticAnalysisAssert.assertThat(analysisResults)
                .removeRuleFromResults(StackDepthSettingRule.class)
                .haveSeverityGreaterThan(Severity.WARNING);

    }

}
