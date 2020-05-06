package com.moca.mechanicallife2.myentity;

/**
 * 事件信息实体类，用于储存事件的详细信息
 */
public class MyEvent {


    private int id;
    private String eventName;
    private int eventType;

    /**
     * 事件今天进行的状态，
     * 待办0：未到达事件开始时间
     * 进行中1：用户在规定时间内开始事件，并处于处理事件的时间范围内。
     * 已完成2：用户在规定时间内完成待办事件。
     * 未完成3：用户未能在规定时间内开始事件。
     */
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
    private int week1;
    private int week2;
    private int week3;
    private int week4;
    private int week5;
    private int week6;
    private int week7;




    public MyEvent(){

    }

    public MyEvent(String eventName, int eventType, int eventStateNow, int completedDays, int yearStart, int monthStart, int dayStart, int yearEnd, int monthEnd, int dayEnd, int hourStart, int hourEnd, int minuteStart, int minuteEnd) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventStateNow = eventStateNow;
        this.completedDays = completedDays;
        this.yearStart = yearStart;
        this.monthStart = monthStart;
        this.dayStart = dayStart;
        this.yearEnd = yearEnd;
        this.monthEnd = monthEnd;
        this.dayEnd = dayEnd;
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
        this.minuteStart = minuteStart;
        this.minuteEnd = minuteEnd;
    }

    public MyEvent(int id, String eventName, int eventType, int eventStateNow, int completedDays, int yearStart, int monthStart, int dayStart, int yearEnd, int monthEnd, int dayEnd, int hourStart, int hourEnd, int minuteStart, int minuteEnd) {
        this.id = id;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventStateNow = eventStateNow;
        this.completedDays = completedDays;
        this.yearStart = yearStart;
        this.monthStart = monthStart;
        this.dayStart = dayStart;
        this.yearEnd = yearEnd;
        this.monthEnd = monthEnd;
        this.dayEnd = dayEnd;
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
        this.minuteStart = minuteStart;
        this.minuteEnd = minuteEnd;
    }


    public MyEvent(int id, String eventName, int eventType, int eventStateNow, int completedDays, int yearStart, int monthStart, int dayStart, int yearEnd, int monthEnd, int dayEnd, int hourStart, int hourEnd, int minuteStart, int minuteEnd, int week1, int week2, int week3, int week4, int week5, int week6, int week7) {
        this.id = id;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventStateNow = eventStateNow;
        this.completedDays = completedDays;
        this.yearStart = yearStart;
        this.monthStart = monthStart;
        this.dayStart = dayStart;
        this.yearEnd = yearEnd;
        this.monthEnd = monthEnd;
        this.dayEnd = dayEnd;
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
        this.minuteStart = minuteStart;
        this.minuteEnd = minuteEnd;
        this.week1 = week1;
        this.week2 = week2;
        this.week3 = week3;
        this.week4 = week4;
        this.week5 = week5;
        this.week6 = week6;
        this.week7 = week7;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public int getEventStateNow() {
        return eventStateNow;
    }

    public void setEventStateNow(int eventStateNow) {
        this.eventStateNow = eventStateNow;
    }

    public int getCompletedDays() {
        return completedDays;
    }

    public void setCompletedDays(int completedDays) {
        this.completedDays = completedDays;
    }

    public int getYearStart() {
        return yearStart;
    }

    public void setYearStart(int yearStart) {
        this.yearStart = yearStart;
    }

    public int getMonthStart() {
        return monthStart;
    }

    public void setMonthStart(int monthStart) {
        this.monthStart = monthStart;
    }

    public int getDayStart() {
        return dayStart;
    }

    public void setDayStart(int dayStart) {
        this.dayStart = dayStart;
    }

    public int getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(int yearEnd) {
        this.yearEnd = yearEnd;
    }

    public int getMonthEnd() {
        return monthEnd;
    }

    public void setMonthEnd(int monthEnd) {
        this.monthEnd = monthEnd;
    }

    public int getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(int dayEnd) {
        this.dayEnd = dayEnd;
    }

    public int getHourStart() {
        return hourStart;
    }

    public void setHourStart(int hourStart) {
        this.hourStart = hourStart;
    }

    public int getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(int hourEnd) {
        this.hourEnd = hourEnd;
    }

    public int getMinuteStart() {
        return minuteStart;
    }

    public void setMinuteStart(int minuteStart) {
        this.minuteStart = minuteStart;
    }

    public int getMinuteEnd() {
        return minuteEnd;
    }

    public void setMinuteEnd(int minuteEnd) {
        this.minuteEnd = minuteEnd;
    }

    public int getWeek1() {
        return week1;
    }

    public void setWeek1(int week1) {
        this.week1 = week1;
    }

    public int getWeek2() {
        return week2;
    }

    public void setWeek2(int week2) {
        this.week2 = week2;
    }

    public int getWeek3() {
        return week3;
    }

    public void setWeek3(int week3) {
        this.week3 = week3;
    }

    public int getWeek4() {
        return week4;
    }

    public void setWeek4(int week4) {
        this.week4 = week4;
    }

    public int getWeek5() {
        return week5;
    }

    public void setWeek5(int week5) {
        this.week5 = week5;
    }

    public int getWeek6() {
        return week6;
    }

    public void setWeek6(int week6) {
        this.week6 = week6;
    }

    public int getWeek7() {
        return week7;
    }

    public void setWeek7(int week7) {
        this.week7 = week7;
    }
}
