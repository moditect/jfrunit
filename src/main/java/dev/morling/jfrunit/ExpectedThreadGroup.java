package dev.morling.jfrunit;

import jdk.jfr.consumer.RecordedThreadGroup;

import java.util.function.Predicate;

public class ExpectedThreadGroup implements Predicate<RecordedThreadGroup> {
    private String name;
    private ExpectedThreadGroup parent;

    public ExpectedThreadGroup() {
    }

    public ExpectedThreadGroup(ThreadGroup threadGroup) {
        this.name = threadGroup.getName();
        ThreadGroup parent = threadGroup.getParent();
        if (parent != null) {
            this.parent = new ExpectedThreadGroup(parent);
        }
    }

    @Override
    public boolean test(RecordedThreadGroup recordedThreadGroup) {
        if (recordedThreadGroup == null) {
            return false;
        }
        if (this.name != null && !this.name.equalsIgnoreCase(recordedThreadGroup.getName())) {
            return false;
        }
        if (this.parent != null && !this.parent.test(recordedThreadGroup.getParent())) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExpectedThreadGroup getParent() {
        return parent;
    }

    public void setParent(ExpectedThreadGroup parent) {
        this.parent = parent;
    }
}
