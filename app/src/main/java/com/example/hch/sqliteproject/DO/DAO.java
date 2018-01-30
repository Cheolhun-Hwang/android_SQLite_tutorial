package com.example.hch.sqliteproject.DO;

import android.content.Context;
import android.database.Cursor;

import com.example.hch.sqliteproject.DB.MySQLiteHandler;
import com.example.hch.sqliteproject.DB.MySQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by hch on 2018-01-31.
 */

public class DAO {
    public static ArrayList<Friends> friendsList;
    public static MySQLiteHandler handler;

    public static void init_freindsList(Context mContext){
        friendsList = new ArrayList<Friends>();
        handler = MySQLiteHandler.open(mContext);
    }

    public static void loadData_freindsList(){
        DAO.friendsList.clear();
        Cursor cursor = handler.selectAll();

        if(cursor.moveToFirst()){
            do{
                Friends friends = new Friends();
                friends.setF_id(Integer.parseInt(cursor.getString(0)));
                friends.setF_name(cursor.getString(1));
                friends.setF_phone(cursor.getString(2));
                friends.setF_comment(cursor.getString(3));
                friendsList.add(0, friends);
            }while (cursor.moveToNext());
        }
    }
}
