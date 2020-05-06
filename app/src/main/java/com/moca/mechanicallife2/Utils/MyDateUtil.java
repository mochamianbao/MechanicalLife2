package com.moca.mechanicallife2.Utils;

import com.moca.mechanicallife2.myentity.DateAndTime;

import java.util.Calendar;

public class MyDateUtil {

    DateAndTime dateAndTime = new DateAndTime();

    public DateAndTime getMonthFrist(){
        DateAndTime dateAndTime = new DateAndTime();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH,1);
        dateAndTime.myYear = cal.get(Calendar.YEAR);
        dateAndTime.myMonth = cal.get(Calendar.MONTH)+1;
        dateAndTime.myDay = cal.get(Calendar.DAY_OF_MONTH);
        int i =cal.get(Calendar.DAY_OF_WEEK);
        if (i == 1){
            dateAndTime.myWeek = 7;
        }else {
            dateAndTime.myWeek = i-1;
        }
        dateAndTime.myDateNumber = dateAndTime.getMyDateNumber();
        dateAndTime.myCalendar = cal;

        System.out.println("当前月第一天"+dateAndTime.myYear+":"+dateAndTime.myMonth+":"+dateAndTime.myDay+":"+dateAndTime.myWeek);
        return dateAndTime;
    }
    public DateAndTime getMonthLast(){
        DateAndTime dateAndTime = new DateAndTime();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

        dateAndTime.myYear = cal.get(Calendar.YEAR);
        dateAndTime.myMonth = cal.get(Calendar.MONTH)+1;
        dateAndTime.myDay = cal.get(Calendar.DAY_OF_MONTH);
        int i =cal.get(Calendar.DAY_OF_WEEK);
        if (i == 1){
            dateAndTime.myWeek = 7;
        }else {
            dateAndTime.myWeek = i-1;
        }
        dateAndTime.myDateNumber = dateAndTime.getMyDateNumber();
        System.out.println("当前月最后一天"+dateAndTime.myYear+":"+dateAndTime.myMonth+":"+dateAndTime.myDay+":"+dateAndTime.myWeek);
        dateAndTime.myCalendar = cal;
        return dateAndTime;
    }
    public DateAndTime getWeekFrist(){
        DateAndTime dateAndTime = new DateAndTime();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, 1);
        cal.add(Calendar.DATE,1);
        dateAndTime.myYear = cal.get(Calendar.YEAR);
        dateAndTime.myMonth = cal.get(Calendar.MONTH)+1;
        dateAndTime.myDay = cal.get(Calendar.DAY_OF_MONTH);
        int i =cal.get(Calendar.DAY_OF_WEEK);
        if (i == 1){
            dateAndTime.myWeek = 7;
        }else {
            dateAndTime.myWeek = i-1;
        }
        dateAndTime.myDateNumber = dateAndTime.getMyDateNumber();
        dateAndTime.myCalendar = cal;
        System.out.println("当前周第一天"+dateAndTime.myYear+":"+dateAndTime.myMonth+":"+dateAndTime.myDay+":"+dateAndTime.myWeek);
        return dateAndTime;
    }
    public DateAndTime getWeekLast(){
        DateAndTime dateAndTime = new DateAndTime();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
        cal.add(Calendar.DATE,1);
        dateAndTime.myYear = cal.get(Calendar.YEAR);
        dateAndTime.myMonth = cal.get(Calendar.MONTH)+1;
        dateAndTime.myDay = cal.get(Calendar.DAY_OF_MONTH);
        int i =cal.get(Calendar.DAY_OF_WEEK);
        if (i == 1){
            dateAndTime.myWeek = 7;
        }else {
            dateAndTime.myWeek = i-1;
        }
        dateAndTime.myDateNumber = dateAndTime.getMyDateNumber();
        System.out.println("当前周最后一天"+dateAndTime.myYear+":"+dateAndTime.myMonth+":"+dateAndTime.myDay+":"+dateAndTime.myWeek);
        dateAndTime.myCalendar = cal;
        return dateAndTime;
    }








}
