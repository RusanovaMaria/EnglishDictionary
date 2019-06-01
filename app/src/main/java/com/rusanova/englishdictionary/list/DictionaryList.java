package com.rusanova.englishdictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rusanova.englishdictionary.database.DatabaseHelper;
import com.rusanova.englishdictionary.database.cursorwrapper.DictionaryCursorWrapper;
import com.rusanova.englishdictionary.database.dbschema.DictionaryDbSchema;
import com.rusanova.englishdictionary.element.Dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DictionaryList {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private static DictionaryList mDictionaryList;

    private DictionaryList(Context context) {

        mContext = context.getApplicationContext();
        mDatabase = new DatabaseHelper(mContext).getWritableDatabase();
    }

    public static DictionaryList get(Context context) {
        if (mDictionaryList == null) {
            mDictionaryList = new DictionaryList(context);
        }
        return mDictionaryList;
    }

    public void add(Dictionary dictionary) {
        ContentValues values = getContentValues(dictionary);
        mDatabase.insert(DictionaryDbSchema.DictionaryTable.NAME, null, values);
    }

    public void delete(Dictionary dictionary) {
        ContentValues values = getContentValues(dictionary);
        String uuidString = dictionary.getId().toString();
        mDatabase.delete(DictionaryDbSchema.DictionaryTable.NAME,
                DictionaryDbSchema.DictionaryTable.Cols.UUID + "=?",
                new String[]{uuidString});
    }

    public List<Dictionary> getDictionaries() {
        List<Dictionary> dictionaries = new ArrayList<>();
        DictionaryCursorWrapper cursor = queryDictionaries(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                dictionaries.add(cursor.getDictionary());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return dictionaries;
    }

    public Dictionary getDictionary(UUID id) {
        DictionaryCursorWrapper cursor = queryDictionaries(
                DictionaryDbSchema.DictionaryTable.Cols.UUID + "?",
                new String[]{id.toString()});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getDictionary();
        } finally {
            cursor.close();
        }
    }

    public void updateDictionary(Dictionary dictionary) {
        String uuidString = dictionary.getId().toString();
        ContentValues values = getContentValues(dictionary);
        mDatabase.update(DictionaryDbSchema.DictionaryTable.NAME, values,
                DictionaryDbSchema.DictionaryTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private static ContentValues getContentValues(Dictionary dictionary) {
        ContentValues values = new ContentValues();
        values.put(DictionaryDbSchema.DictionaryTable.Cols.UUID, dictionary.getId().toString());
        values.put(DictionaryDbSchema.DictionaryTable.Cols.NAME, dictionary.getName());
        values.put(DictionaryDbSchema.DictionaryTable.Cols.DESCRIPTION, dictionary.getDescription());
        return values;
    }

    private DictionaryCursorWrapper queryDictionaries(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                DictionaryDbSchema.DictionaryTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                DictionaryDbSchema.DictionaryTable.Cols.NAME
        );
        return new DictionaryCursorWrapper(cursor);
    }
}
