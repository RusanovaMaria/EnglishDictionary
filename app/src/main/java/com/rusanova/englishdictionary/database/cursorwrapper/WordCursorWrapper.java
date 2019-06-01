package com.rusanova.englishdictionary.database.cursorwrapper;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;

import com.rusanova.englishdictionary.list.DictionaryList;
import com.rusanova.englishdictionary.database.dbschema.WordDbSchema;
import com.rusanova.englishdictionary.element.Dictionary;
import com.rusanova.englishdictionary.element.Word;

import java.util.UUID;

public class WordCursorWrapper extends CursorWrapper {
    private Context mContext;

    public WordCursorWrapper(Cursor cursor) {
        super(cursor);
       // mContext = context;
    }

    public Word getWord() {
        String uuidString = getString(getColumnIndex(WordDbSchema.WordTable.Cols.UUID));
        String name = getString(getColumnIndex(WordDbSchema.WordTable.Cols.NAME));
        String translation = getString(getColumnIndex(WordDbSchema.WordTable.Cols.TRANSLATION));
        String dictionaryStringUUID = getString(getColumnIndex(WordDbSchema.WordTable.Cols.DICTIONARY_UUID));

        Word word = new Word(UUID.fromString(uuidString));
        word.setName(name);
        word.setDescription(translation);

        if (dictionaryStringUUID != null) {
            UUID dictionaryUUID = UUID.fromString(dictionaryStringUUID);
            DictionaryList dictionaryList = DictionaryList.get(mContext);
            Dictionary dictionary = dictionaryList.getDictionary(dictionaryUUID);
            word.setDictionary(dictionary);
        }
        return word;
    }
}
