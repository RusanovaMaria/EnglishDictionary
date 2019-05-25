package com.rusanova.englishdictionary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DictionaryDbSchema.DictionaryTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                DictionaryDbSchema.DictionaryTable.Cols.UUID + ", " +
                DictionaryDbSchema.DictionaryTable.Cols.NAME + ", " +
                DictionaryDbSchema.DictionaryTable.Cols.DESCRIPTION +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
