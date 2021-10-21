package org.moditect.jfrunit.generator.events.model;

import java.util.HashMap;
import java.util.Map;

public class Attribute {
    static Map<String, String> attributeObjToJfrUnitType = new HashMap<>();

    static {
        attributeObjToJfrUnitType.put("long", "Long");
        attributeObjToJfrUnitType.put("int", "Integer");
        attributeObjToJfrUnitType.put("Thread", "org.moditect.jfrunit.ExpectedThread");
        attributeObjToJfrUnitType.put("String", "java.lang.String");
        attributeObjToJfrUnitType.put("Class", "org.moditect.jfrunit.ExpectedClass");
        attributeObjToJfrUnitType.put("ClassLoader", "org.moditect.jfrunit.ExpectedClassLoader");
        attributeObjToJfrUnitType.put("Module", "org.moditect.jfrunit.events.model.Module");
        attributeObjToJfrUnitType.put("Package", "org.moditect.jfrunit.events.model.Package");
        attributeObjToJfrUnitType.put("StackFrame", "org.moditect.jfrunit.ExpectedStackFrame");
        attributeObjToJfrUnitType.put("StackTrace", "org.moditect.jfrunit.ExpectedStackTrace");
        attributeObjToJfrUnitType.put("Method", "org.moditect.jfrunit.ExpectedMethod");
        attributeObjToJfrUnitType.put("boolean", "Boolean");
        attributeObjToJfrUnitType.put("byte", "Byte");
        attributeObjToJfrUnitType.put("char", "Character");
        attributeObjToJfrUnitType.put("double", "Double");
        attributeObjToJfrUnitType.put("float", "Float");
        attributeObjToJfrUnitType.put("short", "Short");

        attributeObjToJfrUnitType.put("Timespan", "java.time.Duration");
        attributeObjToJfrUnitType.put("Timestamp", "java.time.Instant");
    }

    private String name;
    private String type;
    private String contentType;
    private String description;
    private String objectType;
    private String nameUpperCase;
    private String simpleObjectType;
    private String methodName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjectType() {
        if (attributeObjToJfrUnitType.get(getContentType()) != null) {
            return attributeObjToJfrUnitType.get(getContentType());
        }

        return attributeObjToJfrUnitType.get(getType()) != null ? attributeObjToJfrUnitType.get(getType()) : getType();
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getNameUpperCase() {
        return name.replaceAll("()([A-Z])", "$1_$2").toUpperCase();
    }

    public void setNameUpperCase(String nameUpperCase) {
        this.nameUpperCase = nameUpperCase;
    }

    public String getSimpleObjectType() {
        return getObjectType().contains(".") ? getObjectType().substring(getObjectType().lastIndexOf(".") + 1) : getObjectType();
    }

    public void setSimpleObjectType(String simpleObjectType) {
        this.simpleObjectType = simpleObjectType;
    }

    public String getMethodName() {
        return "with" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
