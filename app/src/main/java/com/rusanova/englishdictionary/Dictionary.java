package com.rusanova.englishdictionary;

import java.util.UUID;

public class Dictionary implements Element {
    private UUID id;
    private String name;
    private String description;

    public Dictionary(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Dictionary(UUID id){
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
