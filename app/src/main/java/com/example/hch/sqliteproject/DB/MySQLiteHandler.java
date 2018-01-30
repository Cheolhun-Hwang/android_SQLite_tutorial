package com.example.hch.sqliteproject.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by hch on 2018-01-31.
 */

public class MySQLiteHandler {
    private MySQLiteOpenHelper helper;
    private SQLiteDatabase db;

    public MySQLiteHandler(Context mContext) {
        this.helper = new MySQLiteOpenHelper(mContext, "sqlite_project.sqlite", null, 1);
    }

    public static MySQLiteHandler open(Context ctx){
        return new MySQLiteHandler(ctx);
    }

    public void close(){
        helper.close();
    }

    public void insert(String f_name, String f_phone, String f_comment){
        db = helper.getWritableDatabase();
        ContentValues vales = new ContentValues();
        vales.put("f_name", f_name);
        vales.put("f_phone", f_phone);
        vales.put("f_comment", f_comment);
        db.insert("friends", null, vales);
    }

    public void updeate(String f_id, String f_name, String f_phone, String f_comment){
        db = helper.getWritableDatabase();
        ContentValues vales = new ContentValues();
        vales.put("f_name", f_name);
        vales.put("f_phone", f_phone);
        vales.put("f_comment", f_comment);
        db.update("friends", vales, "f_id = ?", new String[]{f_id});
    }

    public void delete(String f_id){
        ContentValues vales = new ContentValues();
        db.delete("friends", "f_id = ?", new String[]{f_id});
    }

    public Cursor selectAll(){
        db = helper.getWritableDatabase();
        Cursor c = db.query("friends", null, null, null, null, null, null);
        return c;
    }

    public Cursor selectByID(String f_id){
        db = helper.getWritableDatabase();
        Cursor c = db.query("friends", null, "fid = ?", new String[]{f_id}, null, null, null);
        return c;
    }


}
