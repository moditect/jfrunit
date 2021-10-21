package org.moditect.jfrunit.generator;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.auto.service.AutoService;

import freemarker.template.TemplateException;

@SupportedOptions({ "jfrDocUrl" })
@SupportedAnnotationTypes({ "org.moditect.jfrunit.generator.Generator" })
@SupportedSourceVersion(SourceVersion.RELEASE_16)
@AutoService(Processor.class)
public class JrfUnitGeneratorProcessor extends AbstractProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(JrfUnitGeneratorProcessor.class);
    static boolean processed = false;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        String jfrDocUrl = (String) System.getProperties().get("jfrDocUrl");
        if (jfrDocUrl == null || processed) {
            return false;
        }

        try {
            JfrUnitConstantsGenerator.generate(jfrDocUrl, processingEnv);
            processed = true;
        }
        catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        catch (TemplateException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            return false;
        }

        return false;
    }
}
