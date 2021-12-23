package org.moditect.jfrunit;

import java.time.Duration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.moditect.jfrunit.events.GarbageCollection;
import org.moditect.jfrunit.events.JfrEventTypes;
import org.moditect.jfrunit.events.ThreadSleep;

import static org.moditect.jfrunit.JfrEventsAssert.assertThat;

@JfrEventTest
public class JfrUnitErrorMessagesTest {

    public JfrEvents jfrEvents = new JfrEvents();

    @Test
    @EnableEvent(GarbageCollection.EVENT_NAME)
    @EnableEvent(ThreadSleep.EVENT_NAME)
    @DisplayName("Should have Gc and Sleep events recorded when explicitly enabled individually with @EnableEvent")
    public void shouldHaveGcAndSleepEvents() throws Exception {
        System.gc();
        Thread.sleep(50);

        jfrEvents.awaitEvents();

        String wrongCause = "System.gc1()";
        Long wrongMillis = 555L;
        Assertions.assertThatThrownBy(() -> assertThat(jfrEvents).contains(JfrEventTypes.GARBAGE_COLLECTION.withCause(wrongCause)))
                .hasMessage("No JFR event of type <jdk.GarbageCollection>, expected <" + wrongCause + "> on field <cause>");

        Assertions.assertThatThrownBy(() -> assertThat(jfrEvents).contains(JfrEventTypes.THREAD_SLEEP.withTime(Duration.ofMillis(wrongMillis))))
                .hasMessage("No JFR event of type <jdk.ThreadSleep>, expected <PT0." + wrongMillis + "S> on field <time>");

        Assertions.assertThatThrownBy(() -> assertThat(jfrEvents).contains(JfrEventTypes.OBJECT_ALLOCATION_IN_NEW_TLAB))
                .hasMessage("No JFR event of type <jdk.ObjectAllocationInNewTLAB>");
    }
}
