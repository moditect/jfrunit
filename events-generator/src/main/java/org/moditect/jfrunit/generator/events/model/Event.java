package org.moditect.jfrunit.generator.events.model;

import java.util.List;

public class Event {
    private String name;
    private String description;
    private String label;
    private List<String> categories;
    private List<Attribute> attributes;
    private String typeUpperCase;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getTypeUpperCase() {
        return name.replaceAll("(.)([A-Z])", "$1_$2").toUpperCase();
    }

    public void setTypeUpperCase(String typeUpperCase) {
        this.typeUpperCase = typeUpperCase;
    }
}
