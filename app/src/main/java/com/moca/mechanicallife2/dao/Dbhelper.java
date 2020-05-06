package com.moca.mechanicallife2.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {

    public Dbhelper(@Nullable Context context) {
        super(context, "mysqlite.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql ="create table Myevent(" +
                "id integer primary key autoincrement," +
                "eventName varchar(100) ," +
                "eventType integer ," +
                "eventStateNow integer ," +
                "completedDays integer ," +
                "yearStart integer ," +
                "monthStart integer ," +
                "dayStart integer ," +
                "yearEnd integer ," +
                "monthEnd integer ," +
                "dayEnd integer ," +
                "hourStart integer," +
                "hourEnd integer," +
                "minuteStart integer," +
                "minuteEnd integer," +
                "week1 integer," +
                "week2 integer," +
                "week3 integer," +
                "week4 integer," +
                "week5 integer," +
                "week6 integer," +
                "week7 integer)";



        Log.i("数据表event创建",sql);

        db.execSQL(sql);//创建数据库表



        String sql2 ="create table MyUser(" +
                "id integer primary key autoincrement," +
                "userName varchar(100) ," +
                "passWord varchar(100)," +
                "userage integer ," +
                "sex integer ," +
                "lastLoginYear integer ," +
                "lastLoginMonth integer ," +
                "lastLoginDay integer ," +
                "thisDayCompletedNum integer ," +
                "thisWeekCompletedNum integer ," +
                "thisMonthCompletedNum integer ," +
                "thisUserCompletedNum integer ," +
                "haveEventprogress integer)";
        db.execSQL(sql2);//创建数据库表

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
