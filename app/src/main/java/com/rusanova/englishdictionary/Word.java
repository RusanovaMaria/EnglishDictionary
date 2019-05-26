package com.rusanova.englishdictionary;

import java.util.UUID;

public class Word {
    private UUID id;
    private String name;
    private String translation;
    private Dictionary mDictionary;

    public Word(String name, String translation) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.translation = translation;
    }

    public Word(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public Dictionary getDictionary() {
        return mDictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        mDictionary = dictionary;
    }
}
