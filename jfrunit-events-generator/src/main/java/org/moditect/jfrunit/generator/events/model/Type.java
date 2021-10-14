package org.moditect.jfrunit.generator.events.model;

import java.util.List;

public class Type {
    private String name;
    private List<Attribute> attributes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
