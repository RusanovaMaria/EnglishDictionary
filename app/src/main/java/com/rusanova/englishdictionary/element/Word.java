package com.rusanova.englishdictionary.element;

import java.util.UUID;

public class Word {
    private int mId;
    private String mName;
    private String mDescription;
    private Dictionary mDictionary;

    public Word(String name, String translation) {
        mName = name;
        mDescription = translation;
    }

    public Word(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Dictionary getDictionary() {
        return mDictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        mDictionary = dictionary;
    }
}
