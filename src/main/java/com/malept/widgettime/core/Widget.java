package com.malept.widgettime.core;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Widget {
    private long id;
    @NotNull
    @Pattern(regexp="^[^ ]{2,}$")
    private String name;
    @NotNull
    @Min(0)
    private long dateCreated;

    public Widget() {
        // Empty parameterless constructor needed for JSON deserialization
    }

    public Widget(long id, String name, long dateCreated) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }
}
