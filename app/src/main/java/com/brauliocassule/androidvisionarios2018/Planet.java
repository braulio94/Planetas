package com.brauliocassule.androidvisionarios2018;

public class Planet {
    String name;
    String description;
    String path;

    public Planet(String name, String description, String path) {
        this.name = name;
        this.description = description;
        this.path = path;
    }

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
