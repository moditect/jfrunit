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
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.assertj.core.api.AbstractAssert;
import org.openjdk.jmc.common.unit.IQuantity;
import org.openjdk.jmc.flightrecorder.rules.IResult;
import org.openjdk.jmc.flightrecorder.rules.IRule;
import org.openjdk.jmc.flightrecorder.rules.Severity;
import org.openjdk.jmc.flightrecorder.rules.TypedResult;

public class JmcAutomaticAnalysisAssert extends AbstractAssert<JmcAutomaticAnalysisAssert, List<IResult>> {

    private IResult foundResult;
    private static final String LS = System.getProperty("line.separator");

    public JmcAutomaticAnalysisAssert(List<IResult> results) {
        super(results, JmcAutomaticAnalysisAssert.class);
    }

    public static JmcAutomaticAnalysisAssert assertThat(List<IResult> results) {
        return new JmcAutomaticAnalysisAssert(results);
    }

    public JmcAutomaticAnalysisAssert doesNotContain(Class<? extends IRule> expectedRule) {
        return findRule(expectedRule, true, "JMC Analysis result contains rule of type <%s>");
    }

    public JmcAutomaticAnalysisAssert contains(Class<? extends IRule> expectedRule) {
        return findRule(expectedRule);
    }

    private JmcAutomaticAnalysisAssert findRule(Class<? extends IRule> expectedRule) {
        return findRule(expectedRule, false, "No JMC Analysis result rule of type <%s>");
    }

    private JmcAutomaticAnalysisAssert findRule(Class<? extends IRule> expectedRule, boolean negate, String failureMsg) {
        isNotNull();

        Optional<IResult> optionalIResult = actual.stream()
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

    public JmcAutomaticAnalysisAssert hasSeverity(Class<? extends IRule> expectedRule, Severity expectedSeverity) {
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

    public JmcAutomaticAnalysisAssert scoresLessThan(Class<? extends IRule> expectedRule, double expectedScore) {

        findRule(expectedRule);

        return scoresLessThan(expectedScore);
    }

    public JmcAutomaticAnalysisAssert scoresLessThan(double expectedScore) {
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

    public JmcAutomaticAnalysisAssert removeRuleFromResults(Class<? extends IRule> rule) {
        actual
                .stream()
                .filter(result -> result.getRule().equals(rule)).forEach(filtered -> actual.remove(filtered));
        return this;
    }

    public JmcAutomaticAnalysisAssert haveSeverityGreaterThan(Severity expectedSeverity) {
        List<IResult> filterResults = filterBySeverity(expectedSeverity, (expected, actualSeverity) -> actualSeverity.compareTo(expectedSeverity) < 0);
        if (filterResults.size() == 0) {
            failWithMessage("Expected to contain severity greater than: " + expectedSeverity.getLocalizedName());
        }
        return this;
    }

    public JmcAutomaticAnalysisAssert haveSeverityLessThan(Severity expectedSeverity) {
        List<IResult> filterResults = filterBySeverity(expectedSeverity, (expected, actualSeverity) -> actualSeverity.compareTo(expectedSeverity) >= 0);

        if (filterResults.size() > 0) {
            StringBuilder reportBuilder = new StringBuilder();
            reportBuilder.append("Analysis result score equals or exceeds threshold: ")
                    .append(expectedSeverity.getLocalizedName())
                    .append(LS).append(LS);

            filterResults.forEach(result -> {
                reportBuilder.append(result.getSummary())
                        .append(LS)
                        .append(result.getExplanation())
                        .append(LS).append(LS);
            });
            failWithMessage(reportBuilder.toString());
        }
        return this;

    }

    private List<IResult> filterBySeverity(Severity expectedSeverity, BiFunction<Severity, Severity, Boolean> severityComparator) {
        return actual
                .stream()
                .filter(result -> severityComparator.apply(expectedSeverity, result.getSeverity()))
                .collect(Collectors.toList());
    }

    private Optional<IResult> findResult(Class<? extends IRule> expectedRule) {
        return actual.stream()
                .filter(re -> re.getRule().getClass().equals(expectedRule))
                .findFirst();

    }
}
