package com.moca.mechanicallife2.myentity;

public class MyUser {

    private int uid;
    private String userName;
    private String userPassword;
    private int userAge;
    private int userSex;
    private int lastLoginYear;
    private int lastLoginMonth;
    private int lastLoginDay;
    private int thisDayCompletedNum;
    private int thisWeekCompletedNum;
    private int thisMonthCompletedNum;
    private int thisUserCompletedNum;
    private int haveEventprogress;

    public MyUser() {
    }

    public MyUser(int uid, String userName, String userPassword) {
        this.uid = uid;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userage) {
        this.userAge = userage;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public int getLastLoginYear() {
        return lastLoginYear;
    }

    public void setLastLoginYear(int lastLoginYear) {
        this.lastLoginYear = lastLoginYear;
    }

    public int getLastLoginMonth() {
        return lastLoginMonth;
    }

    public void setLastLoginMonth(int lastLoginMonth) {
        this.lastLoginMonth = lastLoginMonth;
    }

    public int getLastLoginDay() {
        return lastLoginDay;
    }

    public void setLastLoginDay(int lastLoginDay) {
        this.lastLoginDay = lastLoginDay;
    }

    public int getThisDayCompletedNum() {
        return thisDayCompletedNum;
    }

    public void setThisDayCompletedNum(int thisDayCompletedNum) {
        this.thisDayCompletedNum = thisDayCompletedNum;
    }

    public int getThisWeekCompletedNum() {
        return thisWeekCompletedNum;
    }

    public void setThisWeekCompletedNum(int thisWeekCompletedNum) {
        this.thisWeekCompletedNum = thisWeekCompletedNum;
    }

    public int getThisMonthCompletedNum() {
        return thisMonthCompletedNum;
    }

    public void setThisMonthCompletedNum(int thisMonthCompletedNum) {
        this.thisMonthCompletedNum = thisMonthCompletedNum;
    }

    public int getThisUserCompletedNum() {
        return thisUserCompletedNum;
    }

    public void setThisUserCompletedNum(int thisUserCompletedNum) {
        this.thisUserCompletedNum = thisUserCompletedNum;
    }

    public int getHaveEventprogress() {
        return haveEventprogress;
    }

    public void setHaveEventprogress(int haveEventprogress) {
        this.haveEventprogress = haveEventprogress;
    }
}
