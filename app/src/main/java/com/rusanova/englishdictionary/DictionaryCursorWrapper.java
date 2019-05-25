package com.rusanova.englishdictionary;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

public class DictionaryCursorWrapper extends CursorWrapper {

    public DictionaryCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Dictionary getDictionary() {
        String uuidString = getString(getColumnIndex(DictionaryDbSchema.DictionaryTable.Cols.UUID));
        String name = getString(getColumnIndex(DictionaryDbSchema.DictionaryTable.Cols.NAME));
        String description = getString(getColumnIndex(DictionaryDbSchema.DictionaryTable.Cols.DESCRIPTION));

        Dictionary dictionary = new Dictionary(UUID.fromString(uuidString));
        dictionary.setName(name);
        dictionary.setDescription(description);

        return dictionary;
    }
}
