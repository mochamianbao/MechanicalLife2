package com.moca.mechanicallife2.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.moca.mechanicallife2.myentity.MyUser;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人信息操作类
 */
public class UserDao {
    private Dbhelper dbhelper;

    public UserDao(Context context){
        //初始化DeHelper帮助类
        dbhelper = new Dbhelper(context);
    }

    /**
     * 新建个人信息
     */
    public void insert(MyUser myUser){
        SQLiteDatabase db = null;

        try {
            db =dbhelper.getWritableDatabase();//可写

            ContentValues values = new ContentValues();
            values.put("userName",myUser.getUserName());
            values.put("passWord",myUser.getUserPassword());
            values.put("userAge",myUser.getUserAge());
            values.put("sex",myUser.getUserSex());
//            values.put("lastLoginYear",myUser.getUserName());
//            values.put("lastLoginMonth",myUser.getUserName());
//            values.put("lastLoginDay",myUser.getUserName());
//            values.put("thisDayCompletedNum",myUser.getUserName());
//            values.put("thisWeekCompletedNum",myUser.getUserName());
//            values.put("thisMonthCompletedNum",myUser.getUserName());
//            values.put("thisUserCompletedNum",myUser.getUserName());
//            values.put("haveEventprogress",myUser.getUserName());
            //增加一条事件信息记录
            db.insert("MyUser", null, values);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db!=null){
                db.close();
            }
        }

    }


    /**
     * 根据用户名查询信息（登录验证）
     */
    public List checkUser(String userName){
        SQLiteDatabase db = null;
        db = dbhelper.getReadableDatabase();
        List ulist = new ArrayList<>();
        MyUser myUser = new MyUser();
        String sql = "select * from MyUser";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            myUser.setUid(cursor.getInt(cursor.getColumnIndex("id")));
            myUser.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
            myUser.setUserPassword(cursor.getString(cursor.getColumnIndex("passWord")));
            myUser.setUserAge(cursor.getInt(cursor.getColumnIndex("userage")));
            myUser.setUserSex(cursor.getInt(cursor.getColumnIndex("sex")));
            myUser.setLastLoginYear(cursor.getInt(cursor.getColumnIndex("lastLoginYear")));
            myUser.setLastLoginMonth(cursor.getInt(cursor.getColumnIndex("lastLoginMonth")));
            myUser.setLastLoginDay(cursor.getInt(cursor.getColumnIndex("lastLoginDay")));
            myUser.setThisDayCompletedNum(cursor.getInt(cursor.getColumnIndex("thisDayCompletedNum")));
            myUser.setThisWeekCompletedNum(cursor.getInt(cursor.getColumnIndex("thisWeekCompletedNum")));
            myUser.setThisMonthCompletedNum(cursor.getInt(cursor.getColumnIndex("thisMonthCompletedNum")));
            myUser.setThisUserCompletedNum(cursor.getInt(cursor.getColumnIndex("thisUserCompletedNum")));
            myUser.setHaveEventprogress(cursor.getInt(cursor.getColumnIndex("haveEventprogress")));
            ulist.add(myUser);
        }
        return ulist;
    }


    /**
     * 根据用户ID修改个人信息
     */
    public void changeUserInfo(String userName,int sex,int age,int id){
        SQLiteDatabase db = null;
        db = dbhelper.getWritableDatabase();
        String sql = "update MyUser set userName = ? , sex = ? , userage = ? where id = ?";
        db.execSQL(sql,new Object[]{userName,sex,age,id});
        System.out.println("数据库完成修改工作");
    }


}
