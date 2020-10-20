package com.example.firstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class RateManager {
    private DB dbhelper;
    private String tbname;

    public RateManager(Context context){
        dbhelper = new DB(context);
        tbname = DB.table_name;

    }

    public void add(RateItem item){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("curname",item.getCurname());
        values.put("currate",item.getCurrate());
        db.insert(tbname, null, values);
        db.close();
    }

    public void addAll(List<RateItem> list){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        for(RateItem item : list){
            ContentValues values = new ContentValues();
            values.put("curname",item.getCurname());
            values.put("currate",item.getCurrate());
            db.insert(tbname, null, values);
        }
        db.close();
    }

    public void deleteAll(){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.delete(tbname,null,null);
        db.close();
    }

    public void delete(int id){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.delete(tbname, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void update(RateItem item){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("curname", item.getCurname());
        values.put("currate", item.getCurrate());
        db.update(tbname, values, "ID=?", new String[]{String.valueOf(item.getId())});
        db.close();
    }

    public List<RateItem> listAll(){
        List<RateItem> rateList = null;
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.query(tbname, null, null, null, null, null, null);
        if(cursor!=null){
            rateList = new ArrayList<RateItem>();
            while(cursor.moveToNext()){
                RateItem item = new RateItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setCurname(cursor.getString(cursor.getColumnIndex("CURNAME")));
                item.setCurrate(cursor.getFloat(cursor.getColumnIndex("CURRATE")));

                rateList.add(item);
            }
            cursor.close();
        }
        db.close();
        return rateList;

    }

    public RateItem findById(int id){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query(tbname, null, "ID=?", new String[]{String.valueOf(id)},
                null, null,null);

        RateItem item = null;
        if(cursor!=null && cursor.moveToFirst()){
            item = new RateItem();
            item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            item.setCurname(cursor.getString(cursor.getColumnIndex("CURNAME")));
            item.setCurrate(cursor.getFloat(cursor.getColumnIndex("CURRATE")));
            cursor.close();
        }
        db.close();
        return item;
    }
}
