package com.example.callerlogs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.callerlogs.tools.Tools;

public class ConectionSQLiteHelper extends SQLiteOpenHelper {

    //final String CREATE_TABLE_SURVEY1 = "CREATE TABLE survey1 (id INTEGER, ans1 TEXT, ans2 TEXT, ans3 TEXT, ans4 TEXT)";
    //final String CREATE_TABLE_SURVEY2 = "CREATE TABLE survey2 (id INTEGER, ans1 TEXT, ans2 TEXT, ans3 TEXT, ans4 TEXT, ans5 TEXT)";
    //final String CREATE_TABLE_SURVEY3 = "CREATE TABLE survey3 (id INTEGER, ans1 TEXT, ans2 TEXT, ans3 TEXT, ans4 TEXT)";

    public ConectionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tools.CREATE_TABLE_SURVEY1);
        db.execSQL(Tools.CREATE_TABLE_SURVEY2);
        db.execSQL(Tools.CREATE_TABLE_SURVEY3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS survey1");
        db.execSQL("DROP TABLE IF EXISTS survey2");
        db.execSQL("DROP TABLE IF EXISTS survey3");
        onCreate(db);

    }
}
