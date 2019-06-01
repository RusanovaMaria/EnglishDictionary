package com.rusanova.englishdictionary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rusanova.englishdictionary.database.dbschema.DictionaryDbSchema;
import com.rusanova.englishdictionary.database.dbschema.WordDbSchema;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "dictionaryBase.db";

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

       db.execSQL("create table " + WordDbSchema.WordTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                WordDbSchema.WordTable.Cols.UUID + ", " +
                WordDbSchema.WordTable.Cols.NAME + ", " +
                WordDbSchema.WordTable.Cols.TRANSLATION +
                WordDbSchema.WordTable.Cols.DICTIONARY_UUID + ", " +
                "foreign key(_id)" +
                " references " + DictionaryDbSchema.DictionaryTable.NAME +
               "(_id))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
