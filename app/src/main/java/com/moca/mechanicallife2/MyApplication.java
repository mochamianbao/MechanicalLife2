package com.moca.mechanicallife2;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;

import com.moca.mechanicallife2.dao.EventDao;
import com.moca.mechanicallife2.myentity.DateAndTime;
import com.moca.mechanicallife2.myentity.MyEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


//控制整个程序的全局变量
public class MyApplication extends Application {
    private static  int HAVE_EVENT_PROGRESS = 0;

    public static void setHaveEventProgress(int haveEventProgress) {
        HAVE_EVENT_PROGRESS = haveEventProgress;
    }

    public static int getHaveEventProgress() {
        return HAVE_EVENT_PROGRESS;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        //初始化闹钟
        EventDao eventDao = new EventDao(this);
        List<MyEvent> myEvents = new ArrayList();
        DateAndTime todayEventList = getTodayEventList();
        myEvents = eventDao.queryAllByDate(todayEventList.getMyDateNumber(),todayEventList.strWeekFlag);

        for (int i = 0; i < myEvents.size(); i++) {
            MyEvent myEvent = myEvents.get(i);
            setAlarmWindow(myEvent.getEventName(),myEvent.getHourStart(),myEvent.getMinuteStart(),i);
            System.out.println("55555555"+" "+myEvent.getEventName()+" "+myEvent.getHourStart()+" "+myEvent.getMinuteStart());
        }






    }

    public void setAlarmWindow(String eventName, int hour, int minute, int num){

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());

        //设置在几点提醒  设置的为0点
        mCalendar.set(Calendar.HOUR_OF_DAY, hour);
        //设置在几分提醒  设置的为0分
        mCalendar.set(Calendar.MINUTE, minute);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("eventName",eventName);
        intent.setComponent(new ComponentName("com.moca.mechanicallife2",
                "com.moca.mechanicallife2.Utils.AlarmWindowReceiver"));
//        String content = input.getText().toString();
//        intent.putExtra(Constants.KEY_CONTENT,content);
        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), num, intent, 0);

        //得到AlarmManager实例
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pi);






    }


    public DateAndTime getTodayEventList (){
        Calendar cal=Calendar.getInstance();
        DateAndTime dateAndTime = new DateAndTime();
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

        return dateAndTime;
    }
}




