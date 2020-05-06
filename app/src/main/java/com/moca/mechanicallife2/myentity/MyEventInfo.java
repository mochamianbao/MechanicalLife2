package com.moca.mechanicallife2.myentity;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.moca.mechanicallife2.R;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MyEventInfo {

    private int id;
    private String eventName;
    private int eventType;
    private int eventStateNow;
    private int completedDays;
    private int yearStart;
    private int monthStart;
    private int dayStart;
    private int yearEnd;
    private int monthEnd;
    private int dayEnd;
    private int hourStart;
    private int hourEnd;
    private int minuteStart;
    private int minuteEnd;

    private String numberOfPlanDay;
    private String detailStartTime;
    private String detailEndTime;





    public MyEventInfo(MyEvent myEvent){
        id=myEvent.getId();
        eventName = myEvent.getEventName();
        eventType = myEvent.getEventType();
        eventStateNow = myEvent.getEventStateNow();
        completedDays = myEvent.getCompletedDays();
        yearStart = myEvent.getYearStart();
        monthStart = myEvent.getMonthStart();
        dayStart = myEvent.getDayStart();
        yearEnd = myEvent.getYearEnd();
        monthEnd = myEvent.getMonthEnd();
        dayEnd = myEvent.getDayEnd();
        hourStart = myEvent.getHourStart();
        hourEnd = myEvent.getHourEnd();
        minuteStart = myEvent.getMinuteStart();
        minuteEnd = myEvent.getMinuteEnd();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getNumberOfPlanDay() {
        LocalDate start = LocalDate.of(yearStart,monthStart,dayStart);
        LocalDate end = LocalDate.of(yearEnd,monthEnd,dayEnd);
        int num = (int) ChronoUnit.DAYS.between(start, end);
        numberOfPlanDay = String.valueOf(num);
        return numberOfPlanDay;
    }

    public String getDetailStartTime() {
        if (minuteStart < 10){
            detailStartTime=hourStart+":"+"0"+minuteStart;
        }else {
            detailStartTime=hourStart+":"+minuteStart;
        }
        return detailStartTime;
    }

    public String getDetailEndTime() {
        if (minuteEnd < 10){
            detailEndTime=hourEnd+":"+"0"+minuteEnd;
        }else {
            detailEndTime=hourEnd+":"+minuteEnd;
        }
        return detailEndTime;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventType() {

        String str = new String();
        switch (eventType){
            case 0:
                str = "学习";
                break;
            case 1:
                str = "娱乐";
                break;
            case 2:
                str = "运动";
                break;
            case 3:
                str = "阅读";
                break;
            default:
                break;
        }
        return str;
    }

    public String getEventStateNow() {
        String str = new String();
        switch (eventStateNow){
            case 0:
                str = "待办";
                break;
            case 1:
                str = "进行中";
                break;
            case 2:
                str = "已完成";
                break;
            case 3:
                str = "未完成";
                break;
            default:
                break;
        }
        return str;
    }

    public String getCompletedDays() {
        return String.valueOf(completedDays);
    }
}
