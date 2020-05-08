package com.moca.mechanicallife2;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;

import com.moca.mechanicallife2.dao.EventDao;
import com.moca.mechanicallife2.dao.UserDao;
import com.moca.mechanicallife2.myentity.DateAndTime;
import com.moca.mechanicallife2.myentity.MyEvent;
import com.moca.mechanicallife2.myentity.MyUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


//控制整个程序的全局变量
public class MyApplication extends Application {
    private static  int HAVE_EVENT_PROGRESS = 0;



    private static MyUser thisUser = new MyUser();

    public static void setHaveEventProgress(int haveEventProgress) {
        HAVE_EVENT_PROGRESS = haveEventProgress;
    }

    public static int getHaveEventProgress() {
        return HAVE_EVENT_PROGRESS;
    }
    public static DateAndTime todayEventList;

    EventDao eventDao = new EventDao(this);
    UserDao userDao = new UserDao(this);

    @Override
    public void onCreate() {
        super.onCreate();


        List<MyEvent> myEvents = new ArrayList();
        //获取今天的日期信息
        todayEventList = getTodayEventList();
        //获取今天的所有事件信息
        myEvents = eventDao.queryAllByDate(todayEventList.getMyDateNumber(),todayEventList.strWeekFlag);
        //初始化闹钟
//        for (int i = 0; i < myEvents.size(); i++) {
//            MyEvent myEvent = myEvents.get(i);
//            setAlarmWindow(myEvent.getEventName(),myEvent.getHourStart(),myEvent.getMinuteStart(),i);
//        }





    }

//    public static void init (){
//        if (thisUser.getLastLoginDay() != todayEventList.myDay){
//            userDao.changelastLoginDay(thisUser.getUid(),todayEventList.myDay);
//            userDao.changethisDayCompletedNum(thisUser.getUid(),0);
//        }
//        if (thisUser.getLastLoginMonth() != todayEventList.myMonth){
//            userDao.changelastLoginMonth(thisUser.getUid(),todayEventList.myMonth);
//            userDao.changethisMonthCompletedNum(thisUser.getUid(),0);
//
//        }
//        System.out.println("初始化1"+thisUser.getLastLoginDay()+"   "+todayEventList.myDay+ "   " +thisUser.getUid());
//        System.out.println("初始化2"+thisUser.getLastLoginMonth()+"   "+todayEventList.myMonth+ "   " +thisUser.getUid());
//
//
//        userDao.changeEventprogress(thisUser.getUid(),0);
//    }



//    public void setAlarmWindow(String eventName, int startHour, int startMinute, int num){
//
//        Calendar mCalendar = Calendar.getInstance();
//        mCalendar.setTimeInMillis(System.currentTimeMillis());
//
//        //设置在几点提醒  设置的为0点
//        mCalendar.set(Calendar.HOUR_OF_DAY, startHour);
//        //设置在几分提醒  设置的为0分
//        mCalendar.set(Calendar.MINUTE, startMinute);
//
//        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        intent.putExtra("eventName",eventName);
////        intent.putExtra("endHour",endHour);
////        intent.putExtra("endMinute",endMinute);
////        intent.putExtra("num",num);
//        intent.setComponent(new ComponentName("com.moca.mechanicallife2",
//                "com.moca.mechanicallife2.Utils.AlarmWindowReceiver"));
////        String content = input.getText().toString();
////        intent.putExtra(Constants.KEY_CONTENT,content);
//        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), num, intent, 0);
//
//        //得到AlarmManager实例
//        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
//        am.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pi);
//
//
//    }

    public DateAndTime getTodayEventList (){
        Calendar cal=Calendar.getInstance();
        DateAndTime dateAndTime = new DateAndTime();
//        cal.add(Calendar.DAY_OF_MONTH,1);
        int week=cal.get(Calendar.DAY_OF_WEEK);//获取今天星期几
        switch (week){
            case 2:
                dateAndTime.strWeekFlag = "week1";
                break;
            case 3:
                dateAndTime.strWeekFlag = "week2";
                break;
            case 4:
                dateAndTime.strWeekFlag = "week3";
                break;
            case 5:
                dateAndTime.strWeekFlag = "week4";
                break;
            case 6:
                dateAndTime.strWeekFlag = "week5";
                break;
            case 7:
                dateAndTime.strWeekFlag = "week6";
                break;
            case 1:
                dateAndTime.strWeekFlag = "week7";
                break;
            default:
                break;
        }
        dateAndTime.myYear=cal.get(Calendar.YEAR);       //获取年月日
        dateAndTime.myMonth=cal.get(Calendar.MONTH)+1;   //获取到的月份是从0开始计数
        dateAndTime.myDay=cal.get(Calendar.DAY_OF_MONTH);
        dateAndTime.myHour=cal.get(Calendar.HOUR_OF_DAY);
        dateAndTime.myMinute=cal.get(Calendar.MINUTE);
        System.out.println("Application今日时间："+dateAndTime.myMonth+"---"+dateAndTime.myDay);
        return dateAndTime;
    }

    public static MyUser getThisUser() {
        return thisUser;
    }

    public static void setThisUser(MyUser thisUser) {
        MyApplication.thisUser = thisUser;
    }
}




