package com.rusanova.englishdictionary.element;

public class Dictionary implements Element {
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

    @Override
    public int getId() {
        return mId;
    }

    @Override
    public void setId(int id) {
        this.mId = id;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void setName(String name) {
        this.mName = name;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }

    @Override
    public void setDescription(String description) {
        this.mDescription = description;
    }
}
