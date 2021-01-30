package dev.morling.jfrunit;

import jdk.jfr.Category;
import jdk.jfr.Event;
import jdk.jfr.Name;
import jdk.jfr.StackTrace;

@Name(StackTraceDisabledSampleEvent.EVENT_NAME)
@Category("JfrUnit")
@StackTrace(false)
public class StackTraceDisabledSampleEvent extends Event {

    public static final String EVENT_NAME = "jfrunit.test.StackTraceDisabledSampleEvent";

}
