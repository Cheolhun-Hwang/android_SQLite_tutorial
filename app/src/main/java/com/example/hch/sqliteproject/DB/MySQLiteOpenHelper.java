package com.example.hch.sqliteproject.DB;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by hch on 2018-01-31.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper{
    private final String TAG = "MySQLiteOpenHelper";

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate..");
        String sql = "CREATE TABLE IF NOT EXISTS friends("+
                    "f_id integer NOT NULL PRIMARY KEY AUTOINCREMENT, "+
                    "f_name text NOT NULL, "+
                    "f_phone text NOT NULL, "+
                    "f_comment text NULL )";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade.. to Delete");
        String sql = "DROP TABLE IF EXISTS friends";

        db.execSQL(sql);

        onCreate(db);
    }
}
