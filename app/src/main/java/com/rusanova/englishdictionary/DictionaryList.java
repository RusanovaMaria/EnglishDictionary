package com.rusanova.englishdictionary;

import java.util.ArrayList;
import java.util.List;

public class DictionaryList {
    private List<Dictionary> mDictionaries;

    public DictionaryList(){
        initializeData();
    }

    private void initializeData(){
       mDictionaries = new ArrayList<>();
       mDictionaries.add(new Dictionary("Travels", "for travels"));
       mDictionaries.add(new Dictionary("Work", "for work"));
       mDictionaries.add(new Dictionary("Colors", "names of different colors"));
    }

    public List<Dictionary> getDictionaries() {
        return mDictionaries;
    }
}
