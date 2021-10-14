package org.moditect.jfrunit;

import java.time.Duration;
import java.util.function.Predicate;

import jdk.jfr.consumer.RecordedEvent;

/**
 * This class is used by typed assertion builder, stores information about the actual predicate to assert on,
 * the recorded jfr attribute name, the value used for contextual information when the predicate evaluation
 * return false.
 * @see org.moditect.jfrunit.events.ThreadSleep#withTime(Duration) 
 */
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
