package com.rusanova.englishdictionary.list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rusanova.englishdictionary.database.DatabaseHelper;
import com.rusanova.englishdictionary.database.cursorwrapper.WordCursorWrapper;
import com.rusanova.englishdictionary.database.dbschema.WordDbSchema;
import com.rusanova.englishdictionary.element.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WordList {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private static WordList mWordList;

    private WordList(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new DatabaseHelper(mContext).getWritableDatabase();
    }

    public static WordList get(Context context) {
        if (mWordList == null) {
            mWordList = new WordList(context.getApplicationContext());
        }
        return mWordList;
    }

    public void add(Word word) {
        ContentValues values = getContentValues(word);
        mDatabase.insert(WordDbSchema.WordTable.NAME, null, values);
    }

    public void delete(Word word) {
        ContentValues values = getContentValues(word);
        String uuidString = Integer.toString(word.getId());
        mDatabase.delete(WordDbSchema.WordTable.NAME,
                "_id=?",
                new String[]{uuidString});
    }

    public List<Word> getWords() {
        List<Word> words = new ArrayList<>();
        WordCursorWrapper cursor = queryWords(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                words.add(cursor.getWord());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return words;
    }

    public Word getWord(int id) {
        WordCursorWrapper cursor = queryWords
                ("_id=?",
                        new String[]{Integer.toString(id)});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getWord();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(Word word) {
        ContentValues values = new ContentValues();
        values.put(WordDbSchema.WordTable.Cols.NAME, word.getName());
        values.put(WordDbSchema.WordTable.Cols.TRANSLATION, word.getDescription().toString());
        values.put(WordDbSchema.WordTable.Cols.DICTIONARY_ID,
                (word.getDictionary() == null ? null : Integer.toString(word.getDictionary().getId())));
        return values;
    }

    private WordCursorWrapper queryWords(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                WordDbSchema.WordTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        // return null;
        return new WordCursorWrapper(cursor);
    }
}
