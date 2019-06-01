package com.rusanova.englishdictionary.database.cursorwrapper;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.rusanova.englishdictionary.database.dbschema.DictionaryDbSchema;
import com.rusanova.englishdictionary.element.Dictionary;

import java.util.UUID;

public class DictionaryCursorWrapper extends CursorWrapper {

    public DictionaryCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Dictionary getDictionary() {
        String idString = getString(getColumnIndex("_id"));
        String name = getString(getColumnIndex(DictionaryDbSchema.DictionaryTable.Cols.NAME));
        String description = getString(getColumnIndex(DictionaryDbSchema.DictionaryTable.Cols.DESCRIPTION));

        Dictionary dictionary = new Dictionary(Integer.parseInt(idString));
        dictionary.setName(name);
        dictionary.setDescription(description);

        return dictionary;
    }
}
