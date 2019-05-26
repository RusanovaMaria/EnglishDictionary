package com.rusanova.englishdictionary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class WordList {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private static WordList mWordList;

    private WordList(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new DatabaseHelper(mContext).getWritableDatabase();
    }

    public static WordList get(Context context) {
        if(mWordList == null) {
            mWordList = new WordList(context.getApplicationContext());
        }
        return mWordList;
    }


}
