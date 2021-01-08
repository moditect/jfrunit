package dev.morling.jfrunit;

import jdk.jfr.consumer.RecordedClassLoader;

import java.util.function.Predicate;

public class ExpectedClassLoader implements Predicate<RecordedClassLoader> {
    private Long id;
    private String name;
    private ExpectedClass type;

    public ExpectedClassLoader() {
    }

    public ExpectedClassLoader(ClassLoader classLoader) {
        this.name = classLoader.getName();
        this.type = new ExpectedClass(classLoader.getClass());
    }

    @Override
    public boolean test(RecordedClassLoader recordedClassLoader) {
        if (recordedClassLoader == null) {
            return false;
        }
        if (this.id != null && this.id != recordedClassLoader.getId()) {
            return false;
        }
        if (this.type != null && !this.type.test(recordedClassLoader.getType())) {
            return false;
        }
        if (this.name != null && !this.name.equalsIgnoreCase(recordedClassLoader.getName())) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
