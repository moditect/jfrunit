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

import java.util.Optional;

import org.assertj.core.api.AbstractAssert;
import org.openjdk.jmc.common.unit.IQuantity;
import org.openjdk.jmc.flightrecorder.rules.IResult;
import org.openjdk.jmc.flightrecorder.rules.IRule;
import org.openjdk.jmc.flightrecorder.rules.Severity;
import org.openjdk.jmc.flightrecorder.rules.TypedResult;

public class JfrAnalysisAssert extends AbstractAssert<JfrAnalysisAssert, JfrAnalysisResults> {

    private IResult foundResult;

    public JfrAnalysisAssert(JfrAnalysisResults results) {
        super(results, JfrAnalysisAssert.class);
    }

    public static JfrAnalysisAssert assertThat(JfrAnalysisResults results) {
        return new JfrAnalysisAssert(results);
    }

    public JfrAnalysisAssert doesNotContain(Class<? extends IRule> expectedRule) {
        return findRule(expectedRule, true, "JMC Analysis result contains rule of type <%s>");
    }

    public JfrAnalysisAssert contains(Class<? extends IRule> expectedRule) {
        return findRule(expectedRule);
    }

    private JfrAnalysisAssert findRule(Class<? extends IRule> expectedRule) {
        return findRule(expectedRule, false, "No JMC Analysis result rule of type <%s>");
    }

    private JfrAnalysisAssert findRule(Class<? extends IRule> expectedRule, boolean negate, String failureMsg) {
        isNotNull();

        Optional<IResult> optionalIResult = actual.getResults().stream()
                .filter(re -> re.getRule().getClass().equals(expectedRule))
                .findAny();

        boolean found = optionalIResult
                .isPresent();

        if (negate ? found : !found) {
            failWithMessage(failureMsg, expectedRule.getName());
        }
        else {
            if (!negate) {
                this.foundResult = optionalIResult.get();
            }
        }

        return this;

    }

    public JfrAnalysisAssert hasSeverity(Class<? extends IRule> expectedRule, Severity expectedSeverity) {
        Optional<IResult> resultOptional = findResult(expectedRule);

        if (!resultOptional.isPresent()) {
            failWithMessage("No analysis type for <%s>", expectedRule.getName());
        }
        else {
            IResult result = resultOptional.get();

            if (result.getSeverity().getLimit() < expectedSeverity.getLimit()) {
                failWithMessage("Analysis result not required severity <%s>", expectedSeverity);

            }
        }
        return this;
    }

    public JfrAnalysisAssert scoresLessThan(Class<? extends IRule> expectedRule, double expectedScore) {

        findRule(expectedRule);

        return scoresLessThan(expectedScore);
    }

    public JfrAnalysisAssert scoresLessThan(double expectedScore) {
        IQuantity resultScore = this.foundResult.getResult(TypedResult.SCORE);
        double score = 0;
        if (resultScore != null) {
            score = resultScore.doubleValue();
        }
        else if (this.foundResult.getSeverity().getLimit() != 0.0d) {
            score = this.foundResult.getSeverity().getLimit();
        }

        if (score > expectedScore) {
            failWithMessage("Analysis result score exceeds threshold: actual <%.1f>, threshold <%.1f>", score, expectedScore);
        }
        return this;

    }

    private Optional<IResult> findResult(Class<? extends IRule> expectedRule) {
        return actual.getResults().stream()
                .filter(re -> re.getRule().getClass().equals(expectedRule))
                .findFirst();

    }
}
