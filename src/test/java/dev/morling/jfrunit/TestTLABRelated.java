package dev.morling.jfrunit;

import jdk.jfr.consumer.RecordedEvent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static dev.morling.jfrunit.ExpectedEvent.event;
import static dev.morling.jfrunit.JfrEventsAssert.assertThat;

@JfrEventTest
public class TestTLABRelated {
    public JfrEvents jfrEvents = new JfrEvents();

    private static final int BYTE_ARRAY_OVERHEAD = 16;
    private static final int OBJECT_SIZE = 102400;
    private static final String BYTE_ARRAY_CLASS_NAME = byte[].class.getName();
    public static byte[] tmp;

    @Test
    @EnableEvent("jdk.ObjectAllocationOutsideTLAB")
    @EnableEvent("jdk.ObjectAllocationInNewTLAB")
    public void testSlowAllocation() throws InterruptedException {
        System.gc();
        Thread.sleep(1000);
        for (int i = 0; i < 512; ++i) {
            tmp = new byte[OBJECT_SIZE - BYTE_ARRAY_OVERHEAD];
        }
        jfrEvents.awaitEvents();

        StackTraceElement[] elements = new Exception().getStackTrace();

        assertThat(jfrEvents).contains(event("jdk.ObjectAllocationOutsideTLAB"));
        assertThat(jfrEvents).contains(event("jdk.ObjectAllocationInNewTLAB"));

        List<RecordedEvent> allocation100KBInNewTLABEvents = jfrEvents.filter(event("jdk.ObjectAllocationInNewTLAB")
                .with("allocationSize", (double) OBJECT_SIZE)
                .with("objectClass", new ExpectedClass(byte[].class))
                .with("eventThread", new ExpectedThread(Thread.currentThread()))
                .containStackFrame(new ExpectedStackFrame(elements[0]))
        ).collect(Collectors.toList());
        List<RecordedEvent> allocation100KBOutsideTLABEvents = jfrEvents.filter(event("jdk.ObjectAllocationOutsideTLAB")
                .with("allocationSize", (double) OBJECT_SIZE)
                .with("objectClass", new ExpectedClass(byte[].class))
                .with("eventThread", new ExpectedThread(Thread.currentThread()))
                .containStackFrame(new ExpectedStackFrame(elements[0]))
        ).collect(Collectors.toList());
        Assertions.assertThat(allocation100KBInNewTLABEvents.size()).isGreaterThan(0);
        Assertions.assertThat(allocation100KBOutsideTLABEvents.size()).isGreaterThan(0);
    }
}
