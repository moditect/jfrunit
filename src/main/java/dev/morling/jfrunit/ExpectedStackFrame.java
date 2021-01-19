package dev.morling.jfrunit;

import jdk.jfr.consumer.RecordedFrame;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.function.Predicate;

public class ExpectedStackFrame implements Predicate<RecordedFrame> {
    private Boolean isJavaFrame;
    private Integer bytecodeIndex;
    private Integer lineNumber;
    private String type;
    private ExpectedMethod method;

    public ExpectedStackFrame() {
    }

    public ExpectedStackFrame(StackWalker.StackFrame stackFrame) {
        this.isJavaFrame = !stackFrame.isNativeMethod();
        this.method = new ExpectedMethod();
        this.method.setName(stackFrame.getMethodName());
    }


    public ExpectedStackFrame(StackTraceElement stackTraceElement) {
        this.isJavaFrame = !stackTraceElement.isNativeMethod();
        this.method = new ExpectedMethod();
        this.method.setName(stackTraceElement.getMethodName());
    }

    @Override
    public boolean test(RecordedFrame recordedFrame) {
        if (isJavaFrame != null && !Objects.equals(recordedFrame.isJavaFrame(), isJavaFrame)) {
            return false;
        }
        if (bytecodeIndex != null && !Objects.equals(recordedFrame.getBytecodeIndex(), bytecodeIndex)) {
            return false;
        }
        if (lineNumber != null && !Objects.equals(recordedFrame.getLineNumber(), lineNumber)) {
            return false;
        }
        if (type != null && !Objects.equals(recordedFrame.getType(), type)) {
            return false;
        }
        if (method != null && !method.test(recordedFrame.getMethod())) {
            return false;
        }
        return true;
    }

    public Boolean getJavaFrame() {
        return isJavaFrame;
    }

    public void setJavaFrame(Boolean javaFrame) {
        isJavaFrame = javaFrame;
    }

    public Integer getBytecodeIndex() {
        return bytecodeIndex;
    }

    public void setBytecodeIndex(Integer bytecodeIndex) {
        this.bytecodeIndex = bytecodeIndex;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ExpectedMethod getMethod() {
        return method;
    }

    public void setMethod(ExpectedMethod method) {
        this.method = method;
    }
}
