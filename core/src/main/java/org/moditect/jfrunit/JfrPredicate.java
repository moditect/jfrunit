package org.moditect.jfrunit;

import java.util.function.Predicate;

import jdk.jfr.consumer.RecordedEvent;

public class JfrPredicate {
    private Predicate<RecordedEvent> predicate;
    private String attributeName;
    private String value;

    public JfrPredicate(Predicate<RecordedEvent> predicate, String attributeName, String value) {
        this.predicate = predicate;
        this.attributeName = attributeName;
        this.value = value;
    }

    public Predicate<RecordedEvent> getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate<RecordedEvent> predicate) {
        this.predicate = predicate;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
