# JfrUnit - A JUnit extension for asserting JDK Flight Recorder events

_Unit testing, for performance_

JfrUnit allows to assert the [JDK Flight Recorder](https://openjdk.java.net/jeps/328) (JFR) events emitted by an application.

## Why JfrUnit?

While unit testing of functional requirements is a standard practice, identifying performance regressions (e.g. increased latencies, reduced throughput) through automated tests is much harder: e.g. assertions on specific request runtimes are prone to failures in virtualized/containerized CI environments due to concurrent load of other jobs.

JfrUnit offers a fresh angle to this topic by supporting assertions not on metrics like latency/throughput themselves, but on indirect metrics which may impact those.
Based on JDK Flight Recorder events, JfrUnit allows you execute assertions e.g. against memory allocation, database IO, and number of executed SQL statements, for a defined workload.
Starting off from a defined base line, future failures of such assertions are indicators for potential performance regressions in an application, as a code change may have introduced higher GC pressure, the retrieval of unneccessary data from the database, or common SQL problems like N+1 SELECT statements.

JfrUnit provide means of identifying and analysizing such issues in a reliable, environment independent way in standard JUnit tests,
before they manifest as performance regressions in production.

## Usage

This project requires OpenJDK 15 or later at runtime.
Support for JDK 11 is on the roadmap, JfrUnit couldn't rely on JFR event stream in this case though, but would have to read JFR events from a recording persisted to disk.
A PR contributing this change would be very welcomed.

JfrUnit is not yet available from Maven Central yet;
in the meantime you can obtain snapshot builds from [JitPack](https://jitpack.io).
To do so, add the following dependency to your project's _pom.xml_:

```xml
...
<dependency>
  <groupId>com.github.moditect</groupId>
  <artifactId>jfrunit</artifactId>
  <version>main-SNAPSHOT</version>
</dependency>
...
```

Alternatively, build JfrUnit from source (see below) yourself and add the following dependency to your project's _pom.xml_:

```xml
...
<dependency>
  <groupId>dev.morling.jfrunit</groupId>
  <artifactId>jfrunit</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
...
```

Then you can implement tests expecting specific JFR events like so:

```java
import dev.morling.jfrunit.*;

import static dev.morling.jfrunit.JfrEventsAssert.*;
import static dev.morling.jfrunit.ExpectedEvent.*;

@JfrEventTest
public class JfrTest {

    public JfrEvents jfrEvents = new JfrEvents();

    @Test
    @EnableEvent("jdk.GarbageCollection")
    @EnableEvent("jdk.ThreadSleep")
    public void shouldHaveGcAndSleepEvents() throws Exception {
        System.gc();
        Thread.sleep(1000);

        jfrEvents.awaitEvents();

        assertThat(jfrEvents).contains(event("jdk.GarbageCollection"));
        assertThat(jfrEvents).contains(
                event("jdk.ThreadSleep").with("time", Duration.ofSeconds(1)));
    }
}
```

Or with Spock Framework like this:
```groovy
import dev.morling.jfrunit.*
import spock.lang.Specification

import java.time.Duration

import static dev.morling.jfrunit.JfrEventsAssert.*
import static dev.morling.jfrunit.ExpectedEvent.*

@JfrEventTest
class JfrSpec extends Specification {

    JfrEvents jfrEvents = new JfrEvents()

    @EnableEvent('jdk.GarbageCollection')
    @EnableEvent('jdk.ThreadSleep')
    def 'should Have GC And Sleep Events'() {
        when:
        System.gc()
        sleep(1000)

        then:
        assertThat(jfrEvents).contains(event('jdk.GarbageCollection'))
        assertThat(jfrEvents).contains(
                event('jdk.ThreadSleep').with('time', Duration.ofSeconds(1)))
    }
}
```

Note that when you're writing a test for a Quarkus application using the `@QuarkusTest` annotation, you don't need (and even should not) add the `@JfrEventTest` annotation.
Instead, the Quarkus test framework will automatically pick up the required callbacks for managing the JFR recording.

The `@EnableEvent` annotation is used to enable one or more JFR event types which should be captured.
The "*" character can be used as a wildcard character to match multiple types:

```java
@Test
@EnableEvent("jdk.GC*")
@EnableEvent("jdk.G1*")
public void someTest() throws Exception { ... }
```

This would capture events like `jdk.GCHeapSummary`, `jdk.GCPhasePause`, `jdk.G1GarbageCollection` etc.
A complete list of all built-in JFR event types can be found [here](https://bestsolution-at.github.io/jfr-doc/).

Alternatively, you can specify the name of a JFR configuration file, e.g. "default" or "profile", using the `@EnableConfiguration` annotation:

```java
@Test
@EnableConfiguration("default")
public void someTest() throws Exception { ... }
```

JFR configuration files are located in the _$JAVA_HOME/bin/jfr_ directory.

## Build

This project requires OpenJDK 14 or later for its build.
Apache Maven is used for the build.
Run the following to build the project:

```shell
mvn clean install
```

## License

This code base is available under the Apache License, version 2.
