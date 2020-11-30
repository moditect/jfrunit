# JfrUnit - A JUnit extension for asserting JDK Flight Recorder events

JfrUnit allows to assert the [JDK Flight Recorder](https://openjdk.java.net/jeps/328) (JFR) events emitted by an application.

## Usage

This project requires OpenJDK 14 or later at runtime.

JfrUnit is not yet available from Maven Central yet;
in the meantime you can obtain snapshot builds from [JitPack](https://jitpack.io).
To so, add the following dependency to your project's _pom.xml_:

```xml
...
<dependency>
  <groupId>com.github.gunnarmorling</groupId>
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

Note that when you're writing a test for a Quarkus application using the `@QuarkusTest` annotation, you don't need (and even should not) add the `@JfrEventTest` annotation.
Instead, the Quarkus test framework will automatically pick up the required callbacks for managing the JFR recording.

## Build

This project requires OpenJDK 14 or later for its build.
Apache Maven is used for the build.
Run the following to build the project:

```shell
mvn clean install
```

## License

This code base is available under the Apache License, version 2.
