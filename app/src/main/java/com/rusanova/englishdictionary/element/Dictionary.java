package com.rusanova.englishdictionary.element;

import java.util.UUID;

public class Dictionary {
    private int mId;
    private String mName;
    private String mDescription;

    public Dictionary(String name, String description) {
        mName = name;
        this.mDescription = description;
    }

    public Dictionary(int id) {
        this.mId = id;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
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
