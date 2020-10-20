package com.example.firstapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class DB extends SQLiteOpenHelper {

    private static final int version = 1;
    private static final String db_name = "myrate.db";
    public static final String table_name = "db_rates";
    private static final String TAG = "DB";

    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DB(@Nullable Context context) {
        super(context, db_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + table_name + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,CURNAME TEXT,CURRATE REAL)");
        Log.i(TAG,"DB:数据库创建完成");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
