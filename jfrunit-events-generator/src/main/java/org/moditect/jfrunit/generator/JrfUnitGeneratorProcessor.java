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
package org.moditect.jfrunit.generator;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.TemplateException;

/**
 * Annotation processor that generates JFR event types from its json representation
 * ex. https://bestsolution-at.github.io/jfr-doc/openjdk-17.json
 */
@SupportedOptions({ "jfrDocUrl" })
@SupportedAnnotationTypes({ "*" })
@SupportedSourceVersion(SourceVersion.RELEASE_16)
public class JrfUnitGeneratorProcessor extends AbstractProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(JrfUnitGeneratorProcessor.class);
    private static boolean processed = false;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        String jfrDocUrl = processingEnv.getOptions().get("jfrDocUrl");
        if (jfrDocUrl == null || processed) {
            return false;
        }

        try {
            JfrUnitConstantsGenerator.generate(jfrDocUrl, processingEnv);
            processed = true;
        }
        catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        catch (TemplateException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }

        return false;
    }
}
