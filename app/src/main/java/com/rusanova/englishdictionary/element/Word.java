package com.rusanova.englishdictionary.element;

public class Word implements Element {
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

    @Override
    public int getId() {
        return mId;
    }

    @Override
    public void setId(int id) {
        mId = id;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void setName(String name) {
        mName = name;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }

    @Override
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
