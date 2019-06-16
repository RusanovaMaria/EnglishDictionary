package com.rusanova.englishdictionary.pagefragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.rusanova.englishdictionary.element.Element;
import com.rusanova.englishdictionary.list.DictionaryList;
import com.rusanova.englishdictionary.list.WordList;

import java.util.List;

public class PageFragmentFactory {
    private static String ERROR_TEXT = "Указана несуществующая страница активности";

    public static Fragment form(int pageNumber, List<Element> elements) {
        switch (pageNumber) {
            case 0:
                return DictionaryPageFragment.newInstance(elements);
            case 1:
                return WordPageFragment.newInstance(elements);
        }
        throw  new IllegalArgumentException(ERROR_TEXT);
    }

    public static String getTitle(int pageNumber) {
        switch (pageNumber) {
            case 0:
                return DictionaryPageFragment.getTitle();
            case 1:
                return WordPageFragment.getTitle();
        }
        throw  new IllegalArgumentException(ERROR_TEXT);
    }

    public static List<Element> getElements(int pageNumber, Context context) {
        switch (pageNumber) {
            case 0:
                return DictionaryList.get(context).getDictionaries();
            case 1:
                return WordList.get(context).getWords();
        }

        throw new IllegalArgumentException(ERROR_TEXT);
    }
}
