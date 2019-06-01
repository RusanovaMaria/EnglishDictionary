package com.rusanova.englishdictionary.element;

import java.util.UUID;

public class Dictionary implements Element {
    private UUID mId;
    private String mName;
    private String mDescription;

    public Dictionary(String name, String description) {
        mId = UUID.randomUUID();
        mName = name;
        this.mDescription = description;
    }

    public Dictionary(UUID id) {
        this.mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }
}
