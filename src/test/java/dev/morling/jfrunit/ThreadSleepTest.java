package dev.morling.jfrunit;

import jdk.jfr.consumer.RecordedEvent;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static dev.morling.jfrunit.ExpectedEvent.event;
import static org.assertj.core.api.Assertions.assertThat;

@JfrEventTest
public class ThreadSleepTest {
    public JfrEvents jfrEvents = new JfrEvents();

    @Test
    @EnableEvent(value = "jdk.ThreadSleep", stackTrace = EnableEvent.StacktracePolicy.INCLUDED)
    public void testWithStackTrace() throws Exception {
        Thread.sleep(10);

        jfrEvents.awaitEvents();

        StackTraceElement[] elements = new Exception().getStackTrace();

        List<RecordedEvent> threadSleepEvents = jfrEvents.filter(
                event("jdk.ThreadSleep")
                        .with("eventThread", new ExpectedThread(Thread.currentThread()))
                        .containStackFrame(new ExpectedStackFrame(elements[0]))
        ).collect(Collectors.toList());
        assertThat(threadSleepEvents.size()).isEqualTo(1);
        assertThat(threadSleepEvents.get(0).getStackTrace() != null);
    }

    @Test
    @EnableEvent(value = "jdk.ThreadSleep", stackTrace = EnableEvent.StacktracePolicy.EXCLUDED)
    public void testWithoutStackTrace() throws Exception {
        Thread.sleep(10);

        jfrEvents.awaitEvents();

        List<RecordedEvent> threadSleepEvents = jfrEvents.filter(
                event("jdk.ThreadSleep")
                        .with("eventThread", new ExpectedThread(Thread.currentThread()))
        ).collect(Collectors.toList());
        assertThat(threadSleepEvents.size()).isEqualTo(1);
        assertThat(threadSleepEvents.get(0).getStackTrace() == null);
    }


    @Test
    @EnableEvent(value = "jdk.ThreadSleep", threshold = 500)
    public void testWithThreshold() throws Exception {
        Thread.sleep(10);
        Thread.sleep(1000);

        jfrEvents.awaitEvents();

        StackTraceElement[] elements = new Exception().getStackTrace();

        List<RecordedEvent> threadSleepEvents = jfrEvents.filter(
                event("jdk.ThreadSleep")
                        .with("eventThread", new ExpectedThread(Thread.currentThread()))
                        .containStackFrame(new ExpectedStackFrame(elements[0]))
        ).collect(Collectors.toList());
        assertThat(threadSleepEvents.size()).isEqualTo(1);
        assertThat(threadSleepEvents.get(0).getStackTrace() != null);
    }

}
