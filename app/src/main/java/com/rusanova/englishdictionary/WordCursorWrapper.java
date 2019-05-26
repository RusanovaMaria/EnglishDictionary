package com.rusanova.englishdictionary;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

public class WordCursorWrapper extends CursorWrapper {
    private Context mContext;

    public WordCursorWrapper(Cursor cursor, Context context) {
        super(cursor);
        mContext = context;
    }

    public Word getWord() {
        String uuidString = getString(getColumnIndex(WordDbSchema.WordTable.Cols.UUID));
        String name = getString(getColumnIndex(WordDbSchema.WordTable.Cols.NAME));
        String translation = getString(getColumnIndex(WordDbSchema.WordTable.Cols.TRANSLATION));
        String dictionaryStringUUID = getString(getColumnIndex(WordDbSchema.WordTable.Cols.DICTIONARY_UUID));

        Word word = new Word(UUID.fromString(uuidString));
        word.setName(name);
        word.setTranslation(translation);

        if (dictionaryStringUUID != null) {
            UUID dictionaryUUID = UUID.fromString(dictionaryStringUUID);
            DictionaryList dictionaryList = DictionaryList.get(mContext);
            Dictionary dictionary = dictionaryList.getDictionary(dictionaryUUID);
            word.setDictionary(dictionary);
        }
        return word;
    }
}
