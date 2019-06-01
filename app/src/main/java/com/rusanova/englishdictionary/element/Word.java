package com.rusanova.englishdictionary.element;

import java.util.UUID;

public class Word implements Element {
    private UUID mId;
    private String mName;
    private String mDescription;
    private Dictionary mDictionary;

    public Word(String name, String translation) {
        mId = UUID.randomUUID();
        mName = name;
        mDescription = translation;
    }

    public Word(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
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
