package com.moca.mechanicallife2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.moca.mechanicallife2.myentity.DateAndTime;
import com.moca.mechanicallife2.myentity.MyEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 事件详情操作类
 */
public class EventDao {
    //定义DbHelper
    private Dbhelper dbhelper;

    public EventDao(Context context){
        //初始化DeHelper帮助类
        dbhelper = new Dbhelper(context);
    }

    /**
     * 新建事件信息
     */

    public long insert(MyEvent event){
        long id = 0;


        SQLiteDatabase db = null;
        try {
            //创建数据库操作对象
            db =dbhelper.getWritableDatabase();//可写
            //封装数据(Map(key,value))
            ContentValues values = new ContentValues();

            values.put("eventName",event.getEventName());
            values.put("eventType",event.getEventType());
            values.put("eventStateNow",event.getEventStateNow());
            values.put("completedDays",event.getCompletedDays());
            values.put("yearStart",event.getYearStart());
            values.put("monthStart",event.getMonthStart());
            values.put("dayStart",event.getDayStart());
            values.put("yearEnd",event.getYearEnd());
            values.put("monthEnd",event.getMonthEnd());
            values.put("dayEnd",event.getDayEnd());
            values.put("hourStart",event.getHourStart());
            values.put("hourEnd",event.getHourEnd());
            values.put("minuteStart",event.getMinuteStart());
            values.put("minuteEnd",event.getMinuteEnd());
            values.put("week1",event.getWeek1());
            values.put("week2",event.getWeek2());
            values.put("week3",event.getWeek3());
            values.put("week4",event.getWeek4());
            values.put("week5",event.getWeek5());
            values.put("week6",event.getWeek6());
            values.put("week7",event.getWeek7());


            //增加一条事件信息记录
            id = db.insert("myevent", null, values);

            System.out.println("进来了吗？？？？？？？？？？？？？？");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (db!=null){
                db.close();
            }

        }
        return id;


    }

    /**
     * 查询事情信息列表
     */
    public List queryAllById(){
        //返回值
        List elist = new ArrayList();
        //创建数据库操作对象
        SQLiteDatabase db = null;

        db = dbhelper.getReadableDatabase();


        try {
            //查询
            Cursor cursor = db.query("myevent",null,null,null,null,null,null);
            //循环取出数据
            while (cursor.moveToNext()){
                //取数据
                int idt = cursor.getInt(cursor.getColumnIndex("id"));
                String eventName = cursor.getString(cursor.getColumnIndex("eventName"));
                int eventType = cursor.getInt(cursor.getColumnIndex("eventType"));
                int eventStateNow = cursor.getInt(cursor.getColumnIndex("eventStateNow"));
                int completedDays = cursor.getInt(cursor.getColumnIndex("completedDays"));
                int yearStart = cursor.getInt(cursor.getColumnIndex("yearStart"));
                int monthStart = cursor.getInt(cursor.getColumnIndex("monthStart"));
                int dayStart = cursor.getInt(cursor.getColumnIndex("dayStart"));
                int yearEnd = cursor.getInt(cursor.getColumnIndex("yearEnd"));
                int monthEnd = cursor.getInt(cursor.getColumnIndex("monthEnd"));
                int dayEnd = cursor.getInt(cursor.getColumnIndex("dayEnd"));
                int hourStart = cursor.getInt(cursor.getColumnIndex("hourStart"));
                int hourEnd = cursor.getInt(cursor.getColumnIndex("hourEnd"));
                int minuteStart = cursor.getInt(cursor.getColumnIndex("minuteStart"));
                int minuteEnd = cursor.getInt(cursor.getColumnIndex("minuteEnd"));
                int week1 = cursor.getInt(cursor.getColumnIndex("week1"));
                int week2 = cursor.getInt(cursor.getColumnIndex("week2"));
                int week3 = cursor.getInt(cursor.getColumnIndex("week3"));
                int week4 = cursor.getInt(cursor.getColumnIndex("week4"));
                int week5 = cursor.getInt(cursor.getColumnIndex("week5"));
                int week6 = cursor.getInt(cursor.getColumnIndex("week6"));
                int week7 = cursor.getInt(cursor.getColumnIndex("week7"));


                //封装到个人对象
                MyEvent event =new MyEvent(idt,eventName,eventType,eventStateNow,completedDays,yearStart,monthStart,dayStart,yearEnd,monthEnd,dayEnd,hourStart,hourEnd,minuteStart,minuteEnd,week1,week2,week3,week4,week5,week6,week7);
                //将事件对象加入集合中
                elist.add(event);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {


            if (db!=null){
                db.close();
            }
        }

        return elist;

    }

    /**
     * 查询一天事情信息列表
     */
    public List queryAllByDate(int dateNumber,String strWeekFlag){
        //返回值
        List elist = new ArrayList();
        //创建数据库操作对象
        SQLiteDatabase db = null;
        db = dbhelper.getReadableDatabase();
        try {
            //查询
            String sql = "select *from myevent where yearEnd*10000+monthEnd*100+dayEnd > " + dateNumber +" and yearStart*10000+monthStart*100+dayStart < " + dateNumber+" and "+strWeekFlag+" = 1 ";
//            Log.i("数据库","**************"+sql);
            Cursor cursor = db.rawQuery(sql,null);
            //循环取出数据
            while (cursor.moveToNext()){
                //取数据
                int idt = cursor.getInt(cursor.getColumnIndex("id"));
                String eventName = cursor.getString(cursor.getColumnIndex("eventName"));
                int eventType = cursor.getInt(cursor.getColumnIndex("eventType"));
                int eventStateNow = cursor.getInt(cursor.getColumnIndex("eventStateNow"));
                int completedDays = cursor.getInt(cursor.getColumnIndex("completedDays"));
                int yearStart = cursor.getInt(cursor.getColumnIndex("yearStart"));
                int monthStart = cursor.getInt(cursor.getColumnIndex("monthStart"));
                int dayStart = cursor.getInt(cursor.getColumnIndex("dayStart"));
                int yearEnd = cursor.getInt(cursor.getColumnIndex("yearEnd"));
                int monthEnd = cursor.getInt(cursor.getColumnIndex("monthEnd"));
                int dayEnd = cursor.getInt(cursor.getColumnIndex("dayEnd"));
                int hourStart = cursor.getInt(cursor.getColumnIndex("hourStart"));
                int hourEnd = cursor.getInt(cursor.getColumnIndex("hourEnd"));
                int minuteStart = cursor.getInt(cursor.getColumnIndex("minuteStart"));
                int minuteEnd = cursor.getInt(cursor.getColumnIndex("minuteEnd"));
                int week1 = cursor.getInt(cursor.getColumnIndex("week1"));
                int week2 = cursor.getInt(cursor.getColumnIndex("week2"));
                int week3 = cursor.getInt(cursor.getColumnIndex("week3"));
                int week4 = cursor.getInt(cursor.getColumnIndex("week4"));
                int week5 = cursor.getInt(cursor.getColumnIndex("week5"));
                int week6 = cursor.getInt(cursor.getColumnIndex("week6"));
                int week7 = cursor.getInt(cursor.getColumnIndex("week7"));


                //封装到个人对象
                MyEvent event =new MyEvent(idt,eventName,eventType,eventStateNow,completedDays,yearStart,monthStart,dayStart,yearEnd,monthEnd,dayEnd,hourStart,hourEnd,minuteStart,minuteEnd,week1,week2,week3,week4,week5,week6,week7);
                //将事件对象加入集合中
                elist.add(event);
//                Log.i("增加事件信息", "开始日期:" + event.getYearStart() + "/" + event.getMonthStart() + "/" + event.getDayStart()
//                        + "结束日期:" + event.getYearEnd() + "/" + event.getMonthEnd() + "/" + event.getDayEnd()
//                        + "开始时间:" + event.getHourStart() + ":" + event.getMinuteStart()
//                        + "结束时间:" + event.getHourEnd() + ":" + event.getMinuteEnd() +
//                        "事件类型" + event.getEventType() + "事件名字" + event.getEventName() +
//                        "-----week1" + event.getWeek1() + "-----week2" + event.getWeek2() + "-----week2" + event.getWeek3() +
//                        "-----week4" + event.getWeek4() + "-----week5" + event.getWeek5() + "-----week6" + event.getWeek6() +
//                        "-----week7" + event.getWeek7());

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db!=null){
                db.close();
            }
        }
        return elist;

    }


    /**
     * 查询一天事情信息列表
     */
    public int queryIdByDate(int dateNumber,String strWeekFlag){
        //返回值
        List elist = new ArrayList();
        //创建数据库操作对象
        SQLiteDatabase db = null;
        int i = 0;
        db = dbhelper.getReadableDatabase();
        try {
            //查询
            String sql = "select *from myevent where yearEnd*10000+monthEnd*100+dayEnd > " + dateNumber +" and yearStart*10000+monthStart*100+dayStart < " + dateNumber+" and "+strWeekFlag+" = 1 ";
//            Log.i("数据库","**************"+sql);
            Cursor cursor = db.rawQuery(sql,null);
            //循环取出数据
            i = cursor.getCount();
//            Log.i("数据库queryIdByDate方法","********"+i+"***"+dateNumber);
//            while (cursor.moveToNext()){
//                //取数据
//
//                int week1 = cursor.getInt(cursor.getColumnIndex("week1"));
//                int week2 = cursor.getInt(cursor.getColumnIndex("week2"));
//                int week3 = cursor.getInt(cursor.getColumnIndex("week3"));
//                int week4 = cursor.getInt(cursor.getColumnIndex("week4"));
//                int week5 = cursor.getInt(cursor.getColumnIndex("week5"));
//                int week6 = cursor.getInt(cursor.getColumnIndex("week6"));
//                int week7 = cursor.getInt(cursor.getColumnIndex("week7"));


                //封装到个人对象
//                MyEvent event =new MyEvent(idt,eventName,eventType,eventStateNow,completedDays,yearStart,monthStart,dayStart,yearEnd,monthEnd,dayEnd,hourStart,hourEnd,minuteStart,minuteEnd,week1,week2,week3,week4,week5,week6,week7);
                //将事件对象加入集合中
//                elist.add(event);
//                Log.i("增加事件信息", "开始日期:" + event.getYearStart() + "/" + event.getMonthStart() + "/" + event.getDayStart()
//                        + "结束日期:" + event.getYearEnd() + "/" + event.getMonthEnd() + "/" + event.getDayEnd()
//                        + "开始时间:" + event.getHourStart() + ":" + event.getMinuteStart()
//                        + "结束时间:" + event.getHourEnd() + ":" + event.getMinuteEnd() +
//                        "事件类型" + event.getEventType() + "事件名字" + event.getEventName() +
//                        "-----week1" + event.getWeek1() + "-----week2" + event.getWeek2() + "-----week2" + event.getWeek3() +
//                        "-----week4" + event.getWeek4() + "-----week5" + event.getWeek5() + "-----week6" + event.getWeek6() +
//                        "-----week7" + event.getWeek7());

//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db!=null){
                db.close();
            }
        }
        return i;

    }

    /**
     * 根据Id查询事情信息
     */
    public MyEvent queryById(int idput){
        //返回值
        MyEvent event1 = new MyEvent();
        //创建数据库操作对象
        SQLiteDatabase db = null;

        db = dbhelper.getReadableDatabase();


        try {
            //查询
            String sql = "select * from myevent where id = ?";

            Cursor cursor = db.rawQuery(sql,new String[]{String.valueOf(idput)});
            //循环取出数据

            //必须加入这条语句，设置游标
            cursor.moveToFirst();
            //取数据
            int idt = cursor.getInt(cursor.getColumnIndex("id"));
            String eventName = cursor.getString(cursor.getColumnIndex("eventName"));
            int eventType = cursor.getInt(cursor.getColumnIndex("eventType"));
            int eventStateNow = cursor.getInt(cursor.getColumnIndex("eventStateNow"));
            int completedDays = cursor.getInt(cursor.getColumnIndex("completedDays"));
            int yearStart = cursor.getInt(cursor.getColumnIndex("yearStart"));
            int monthStart = cursor.getInt(cursor.getColumnIndex("monthStart"));
            int dayStart = cursor.getInt(cursor.getColumnIndex("dayStart"));
            int yearEnd = cursor.getInt(cursor.getColumnIndex("yearEnd"));
            int monthEnd = cursor.getInt(cursor.getColumnIndex("monthEnd"));
            int dayEnd = cursor.getInt(cursor.getColumnIndex("dayEnd"));
            int hourStart = cursor.getInt(cursor.getColumnIndex("hourStart"));
            int hourEnd = cursor.getInt(cursor.getColumnIndex("hourEnd"));
            int minuteStart = cursor.getInt(cursor.getColumnIndex("minuteStart"));
            int minuteEnd = cursor.getInt(cursor.getColumnIndex("minuteEnd"));
            int week1 = cursor.getInt(cursor.getColumnIndex("week1"));
            int week2 = cursor.getInt(cursor.getColumnIndex("week2"));
            int week3 = cursor.getInt(cursor.getColumnIndex("week3"));
            int week4 = cursor.getInt(cursor.getColumnIndex("week4"));
            int week5 = cursor.getInt(cursor.getColumnIndex("week5"));
            int week6 = cursor.getInt(cursor.getColumnIndex("week6"));
            int week7 = cursor.getInt(cursor.getColumnIndex("week7"));
            //封装到个人对象
            event1 =new MyEvent(idt,eventName,eventType,eventStateNow,completedDays,yearStart,monthStart,dayStart,yearEnd,monthEnd,dayEnd,hourStart,hourEnd,minuteStart,minuteEnd,week1,week2,week3,week4,week5,week6,week7);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {


            if (db!=null){
                db.close();
            }
        }

        return event1;

    }

    /**
     * 查询设置时间是否与已定计划重合,true为有重合，false为没有重合
     */
    public boolean queryForCheckData (DateAndTime startDate,DateAndTime endDate,int[] weekAll){
        //创建数据库操作对象
        SQLiteDatabase db = null;
        List elist = new ArrayList();
        db = dbhelper.getReadableDatabase();
        boolean result = false;

        String str = "";

        for (int i = 0; i <7 ; i++) {

            if (weekAll[i] == 1){
                if (str == ""){
                    str = str+" and ( "+"week"+(i+1)+"=1";
                }
                else {
                    str = str + " or " + "week" + (i+1) + "=1";
                }
            }

        }
        if (str != null){
            str = str+" )";
        }

        int thisStartDate = startDate.myYear*10000+startDate.myMonth*100+startDate.myDay;
        int thisstartTime = startDate.myHour*60+startDate.myMinute;
        int thisendDate = endDate.myYear*10000+endDate.myMonth*100+endDate.myDay;
        int thisendTime = endDate.myHour*60+endDate.myMinute;

        Log.i("数据库获取设置开始时间信息","---------------------"+startDate.myHour+":"+startDate.myMinute);
        Log.i("数据库获取设置结束时间信息","---------------------"+endDate.myHour+":"+endDate.myMinute);



        try {
            //查询
//            String sql = "SELECT * FROM myevent2 WHERE (yearEnd*10000+monthEnd*100+dayEnd)>"+thisStartDate+" and (yearStart*10000+monthStart*100+dayStart)<"+thisendDate+" and (hourStart*60+minuteStart) > "+thisstartTime+"and (hourStart*60+minuteStart) < "+thisendTime;
            String sql = "SELECT * FROM myevent WHERE (yearEnd*10000+monthEnd*100+dayEnd) > "+thisStartDate +" and (yearStart*10000+monthStart*100+dayStart) < "+thisendDate+" and (hourEnd*60+minuteEnd) > "+ thisstartTime + "  and (hourStart*60+minuteStart) < " + thisendTime +" "+str;

            Cursor cursor = db.rawQuery(sql,null);
            //循环取出数据

            if (cursor.getCount()>0){
                result = true;
                System.out.println(cursor.getCount());
            }
            System.out.println("                     "+sql);
            //必须加入这条语句，设置游标
//            cursor.moveToFirst();




            //取数据
            while (cursor.moveToNext()){
                //取数据
                int idt = cursor.getInt(cursor.getColumnIndex("id"));
                String eventName = cursor.getString(cursor.getColumnIndex("eventName"));
                int eventType = cursor.getInt(cursor.getColumnIndex("eventType"));
                int eventStateNow = cursor.getInt(cursor.getColumnIndex("eventStateNow"));
                int completedDays = cursor.getInt(cursor.getColumnIndex("completedDays"));
                int yearStart = cursor.getInt(cursor.getColumnIndex("yearStart"));
                int monthStart = cursor.getInt(cursor.getColumnIndex("monthStart"));
                int dayStart = cursor.getInt(cursor.getColumnIndex("dayStart"));
                int yearEnd = cursor.getInt(cursor.getColumnIndex("yearEnd"));
                int monthEnd = cursor.getInt(cursor.getColumnIndex("monthEnd"));
                int dayEnd = cursor.getInt(cursor.getColumnIndex("dayEnd"));
                int hourStart = cursor.getInt(cursor.getColumnIndex("hourStart"));
                int hourEnd = cursor.getInt(cursor.getColumnIndex("hourEnd"));
                int minuteStart = cursor.getInt(cursor.getColumnIndex("minuteStart"));
                int minuteEnd = cursor.getInt(cursor.getColumnIndex("minuteEnd"));
                int week1 = cursor.getInt(cursor.getColumnIndex("week1"));
                int week2 = cursor.getInt(cursor.getColumnIndex("week2"));
                int week3 = cursor.getInt(cursor.getColumnIndex("week3"));
                int week4 = cursor.getInt(cursor.getColumnIndex("week4"));
                int week5 = cursor.getInt(cursor.getColumnIndex("week5"));
                int week6 = cursor.getInt(cursor.getColumnIndex("week6"));
                int week7 = cursor.getInt(cursor.getColumnIndex("week7"));


                //封装到个人对象
                MyEvent event =new MyEvent(idt,eventName,eventType,eventStateNow,completedDays,yearStart,monthStart,dayStart,yearEnd,monthEnd,dayEnd,hourStart,hourEnd,minuteStart,minuteEnd,week1,week2,week3,week4,week5,week6,week7);
                //将事件对象加入集合中
                elist.add(event);
                Log.i("增加事件信息", "开始日期:" + event.getYearStart() + "/" + event.getMonthStart() + "/" + event.getDayStart()
                        + "结束日期:" + event.getYearEnd() + "/" + event.getMonthEnd() + "/" + event.getDayEnd()
                        + "开始时间:" + event.getHourStart() + ":" + event.getMinuteStart()
                        + "结束时间:" + event.getHourEnd() + ":" + event.getMinuteEnd() +
                        "事件类型" + event.getEventType() + "事件名字" + event.getEventName() +
                        "-----week1" + event.getWeek1() + "-----week2" + event.getWeek2() + "-----week2" + event.getWeek3() +
                        "-----week4" + event.getWeek4() + "-----week5" + event.getWeek5() + "-----week6" + event.getWeek6() +
                        "-----week7" + event.getWeek7());

            }



            //封装到个人对象
//            event1 =new MyEvent(idt,eventName,eventType,eventStateNow,completedDays,yearStart,monthStart,dayStart,yearEnd,monthEnd,dayEnd,hourStart,hourEnd,minuteStart,minuteEnd,week1,week2,week3,week4,week5,week6,week7);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {


            if (db!=null){
                db.close();
            }
        }

        return result;
    }


    /**
     * 查询设置时间是否与已定计划重合,true为有重合，false为没有重合,除去自身数据
     */
    public boolean queryForCheckDataElseOne (DateAndTime startDate,DateAndTime endDate,int[] weekAll,int id){
        //创建数据库操作对象
        SQLiteDatabase db = null;
        List elist = new ArrayList();
        db = dbhelper.getReadableDatabase();
        boolean result = false;

        String str = "";

        for (int i = 0; i <7 ; i++) {

            if (weekAll[i] == 1){
                if (str == ""){
                    str = str+" and ( "+"week"+(i+1)+"=1";
                }
                else {
                    str = str + " or " + "week" + (i+1) + "=1";
                }
            }

        }
        if (str != null){
            str = str+" )";
        }

        int thisStartDate = startDate.myYear*10000+startDate.myMonth*100+startDate.myDay;
        int thisstartTime = startDate.myHour*60+startDate.myMinute;
        int thisendDate = endDate.myYear*10000+endDate.myMonth*100+endDate.myDay;
        int thisendTime = endDate.myHour*60+endDate.myMinute;

        Log.i("数据库获取设置开始时间信息","---------------------"+startDate.myHour+":"+startDate.myMinute);
        Log.i("数据库获取设置结束时间信息","---------------------"+endDate.myHour+":"+endDate.myMinute);
        Log.i("数据库获取设置结束时间信息，年","---------------------"+startDate.myYear);
        Log.i("数据库获取设置结束时间信息，月","---------------------"+startDate.myMonth);
        Log.i("数据库获取设置结束时间信息，日","---------------------"+startDate.myDay);
        Log.i("数据库获取设置结束时间信息，年","---------------------"+endDate.myYear);
        Log.i("数据库获取设置结束时间信息，月","---------------------"+endDate.myMonth);
        Log.i("数据库获取设置结束时间信息，日","---------------------"+endDate.myDay);



        try {
            //查询
//            String sql = "SELECT * FROM myevent2 WHERE (yearEnd*10000+monthEnd*100+dayEnd)>"+thisStartDate+" and (yearStart*10000+monthStart*100+dayStart)<"+thisendDate+" and (hourStart*60+minuteStart) > "+thisstartTime+"and (hourStart*60+minuteStart) < "+thisendTime;
            String sql = "SELECT * FROM myevent WHERE (yearEnd*10000+monthEnd*100+dayEnd) > "+thisStartDate +" and (yearStart*10000+monthStart*100+dayStart) < "+thisendDate+" and (hourEnd*60+minuteEnd) > "+ thisstartTime + "  and (hourStart*60+minuteStart) < " + thisendTime +" "+str;

            Cursor cursor = db.rawQuery(sql,null);
            //循环取出数据


            System.out.println("       重新判断时间  "+sql);


            //取数据
            while (cursor.moveToNext()){
                //取数据
                int idt = cursor.getInt(cursor.getColumnIndex("id"));
                if (idt != id ){
                    return true;
                }

                Log.i("数据库","queryForCheckDataElseOne+++++++++++");


            }



            //封装到个人对象
//            event1 =new MyEvent(idt,eventName,eventType,eventStateNow,completedDays,yearStart,monthStart,dayStart,yearEnd,monthEnd,dayEnd,hourStart,hourEnd,minuteStart,minuteEnd,week1,week2,week3,week4,week5,week6,week7);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {


            if (db!=null){
                db.close();
            }
        }

        return result;
    }

    /**
     *
     * 查询一段时间内存在的所有事件（本月，本周）
     */
    public  List  querySpanByDate(int dataNumber1,int dataNumber){
        //返回值
        List elist = new ArrayList();
        //创建数据库操作对象
        SQLiteDatabase db = null;
        db = dbhelper.getReadableDatabase();
        try {
            //查询
            String sql = "select *from myevent where yearEnd*10000+monthEnd*100+dayEnd > " + dataNumber1 +" and yearStart*10000+monthStart*100+dayStart < " + dataNumber;
//            Log.i("数据库","**************"+sql);
            Cursor cursor = db.rawQuery(sql,null);
            //循环取出数据
            while (cursor.moveToNext()){
                //取数据
                int idt = cursor.getInt(cursor.getColumnIndex("id"));
                String eventName = cursor.getString(cursor.getColumnIndex("eventName"));
                int eventType = cursor.getInt(cursor.getColumnIndex("eventType"));
                int eventStateNow = cursor.getInt(cursor.getColumnIndex("eventStateNow"));
                int completedDays = cursor.getInt(cursor.getColumnIndex("completedDays"));
                int yearStart = cursor.getInt(cursor.getColumnIndex("yearStart"));
                int monthStart = cursor.getInt(cursor.getColumnIndex("monthStart"));
                int dayStart = cursor.getInt(cursor.getColumnIndex("dayStart"));
                int yearEnd = cursor.getInt(cursor.getColumnIndex("yearEnd"));
                int monthEnd = cursor.getInt(cursor.getColumnIndex("monthEnd"));
                int dayEnd = cursor.getInt(cursor.getColumnIndex("dayEnd"));
                int hourStart = cursor.getInt(cursor.getColumnIndex("hourStart"));
                int hourEnd = cursor.getInt(cursor.getColumnIndex("hourEnd"));
                int minuteStart = cursor.getInt(cursor.getColumnIndex("minuteStart"));
                int minuteEnd = cursor.getInt(cursor.getColumnIndex("minuteEnd"));
                int week1 = cursor.getInt(cursor.getColumnIndex("week1"));
                int week2 = cursor.getInt(cursor.getColumnIndex("week2"));
                int week3 = cursor.getInt(cursor.getColumnIndex("week3"));
                int week4 = cursor.getInt(cursor.getColumnIndex("week4"));
                int week5 = cursor.getInt(cursor.getColumnIndex("week5"));
                int week6 = cursor.getInt(cursor.getColumnIndex("week6"));
                int week7 = cursor.getInt(cursor.getColumnIndex("week7"));


                //封装到个人对象
                MyEvent event =new MyEvent(idt,eventName,eventType,eventStateNow,completedDays,yearStart,monthStart,dayStart,yearEnd,monthEnd,dayEnd,hourStart,hourEnd,minuteStart,minuteEnd,week1,week2,week3,week4,week5,week6,week7);
                //将事件对象加入集合中
                elist.add(event);
                Log.i("增加事件信息", "开始日期:" + event.getYearStart() + "/" + event.getMonthStart() + "/" + event.getDayStart()
                        + "结束日期:" + event.getYearEnd() + "/" + event.getMonthEnd() + "/" + event.getDayEnd()
                        + "开始时间:" + event.getHourStart() + ":" + event.getMinuteStart()
                        + "结束时间:" + event.getHourEnd() + ":" + event.getMinuteEnd() +
                        "事件类型" + event.getEventType() + "事件名字" + event.getEventName() +
                        "-----week1" + event.getWeek1() + "-----week2" + event.getWeek2() + "-----week2" + event.getWeek3() +
                        "-----week4" + event.getWeek4() + "-----week5" + event.getWeek5() + "-----week6" + event.getWeek6() +
                        "-----week7" + event.getWeek7());

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db!=null){
                db.close();
            }
        }
        return elist;

    }


    /**
     * 修改事件执行状态
     */
    public void updateEventStateNowById(int id,int state){
        SQLiteDatabase db = null;
        db = dbhelper.getReadableDatabase();
        String sql = "update myevent set eventStateNow = ? where id = ?";
        db.execSQL(sql,new Object[]{state,id});

    }

    /**
     * 修改事件的时间
     */
    public void updateEventTime(DateAndTime startDate,DateAndTime endDate,int id){
        SQLiteDatabase db = null;
        db = dbhelper.getWritableDatabase();
        String sql = "update myevent set hourStart = ? , minuteStart = ?, hourEnd = ? ,minuteEnd = ?  where id = ?";
        db.execSQL(sql,new Object[]{startDate.myHour,startDate.myMinute,endDate.myHour,endDate.myMinute,id});
        Log.i("数据库updateEventTime",startDate.myHour+"  "+startDate.myMinute+ "  "+endDate.myHour+"  "+endDate.myMinute+" "+id);
    }


}
