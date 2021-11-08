package com.hellofudan.chapter3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    // if you change the database schema, you must increment the database version
    public static final String DATABASE_NAME = "DataBase.db";

    private static final String CREATE_TABLE = "create table Info(" +
            "id integer primary key autoincrement, " +
            "content text, " +
            "time long, " +
            "status Boolean)";

    private static final String DELETE_TABLE = "DROP TABLE IF EXISTS " +
            DataBaseContract.InfoEntry.TABLE_NAME;

    public MyDatabaseHelper(Context context, int version){
        super(context, DATABASE_NAME, null, version);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }



}
