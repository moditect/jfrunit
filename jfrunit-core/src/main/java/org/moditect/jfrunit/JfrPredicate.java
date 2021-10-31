package org.moditect.jfrunit;

import java.util.function.Predicate;

import jdk.jfr.consumer.RecordedEvent;

public class JfrPredicate {

    private final Predicate<RecordedEvent> predicate;
    private final String attributeName;
    private final String value;

    public JfrPredicate(final Predicate<RecordedEvent> predicate, final String attributeName, final String value) {
        this.predicate = predicate;
        this.attributeName = attributeName;
        this.value = value;
    }

    public Predicate<RecordedEvent> getPredicate() {
        return predicate;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getValue() {
        return value;
    }
}
