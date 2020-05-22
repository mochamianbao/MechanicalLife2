package com.moca.mechanicallife2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.moca.mechanicallife2.dao.EventDao;
import com.moca.mechanicallife2.dao.UserDao;
import com.moca.mechanicallife2.databinding.ActivityLoginBinding;
import com.moca.mechanicallife2.myentity.DateAndTime;
import com.moca.mechanicallife2.myentity.MyEvent;
import com.moca.mechanicallife2.myentity.MyUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LoginActivity extends AppCompatActivity {



    private MyUser myUser = new MyUser();//输入的用户信息
    private static MyUser findUser = new MyUser();
    private List<MyUser> tableUser = new ArrayList<>();//获取数据库的用户信息
    private EditText editTextName,editTextPassword;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final ActivityLoginBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        Button btnLogin;
        TextView textViewRedister;
        btnLogin =  binding.buttonLogin;
        editTextName = binding.editTextName;
        editTextPassword = binding.editTextPassword;
        textViewRedister = binding.textViewRedister;


        //输入的用户名与信息



        //登录操作
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLogin = checkLogin();



                if (isLogin){
                    System.out.println("开始登陆操作");
                    //初始化统计页面数据
                    UserDao userDao = new UserDao(LoginActivity.this);
                    EventDao eventDao = new EventDao(LoginActivity.this);

                    DateAndTime todayEventList;

                    List<MyEvent> myEvents = new ArrayList();
                    //获取今天的日期信息
                    todayEventList = getTodayEventList();
                    //获取今天的所有事件信息
                    myEvents = eventDao.queryAllByDate(todayEventList.getMyDateNumber(),todayEventList.strWeekFlag);

                    //获取用户所有的事件，用于重置事件状态
                    List<MyEvent> queryAllEvent = eventDao.queryAllEvent();
                    if (findUser.getLastLoginDay() != todayEventList.myDay){
                        userDao.changelastLoginDay(findUser.getUid(),todayEventList.myDay);
                        userDao.changethisDayCompletedNum(findUser.getUid(),0);

                        for (int i = 0; i < queryAllEvent.size(); i++) {
                            System.out.println("进入开始重置今日事件状态");
                            MyEvent myEvent = queryAllEvent.get(i);
                            eventDao.updateEventStateNowById(myEvent.getId(),0);
                            System.out.println("重置状态完成，"+queryAllEvent.size()+"    "+myEvent.getId()+":"+myEvent.getEventStateNow());
                        }


                    }
                    if (findUser.getLastLoginMonth() != todayEventList.myMonth){
                        userDao.changelastLoginMonth(findUser.getUid(),todayEventList.myMonth);
                        userDao.changethisMonthCompletedNum(findUser.getUid(),0);

                    }
                    System.out.println("初始化1："+findUser.getLastLoginDay()+"   "+todayEventList.myDay+ "   " +findUser.getUid());
                    System.out.println("初始化2："+findUser.getLastLoginMonth()+"   "+todayEventList.myMonth+ "   " +findUser.getUid());
                    //初始化用户执行任务状态
                    userDao.changeEventprogress(findUser.getUid(),0);


                    //设置闹钟

                    for (int i = 0; i < myEvents.size(); i++) {
                        MyEvent myEvent = myEvents.get(i);
                        int todayNumber = todayEventList.myHour*60+todayEventList.myMinute;
                        int eventStartNumber = myEvent.getHourStart()*60+myEvent.getMinuteStart();
                        if (todayNumber>eventStartNumber) {
                            setAlarmWindow(myEvent.getEventName(), myEvent.getHourStart(), myEvent.getMinuteStart(), i);
                        }
                    }


                    //初始化今日事件完成状态信息
                    for (int i = 0; i < myEvents.size(); i++) {
                        MyEvent myEvent = myEvents.get(i);
                        System.out.println("进入开始初始化今日事件状态  ："+myEvent.getEventStateNow()+" "+myEvents.size());
                        if (myEvent.getEventStateNow() == 1){
                            //进行中事件在退出程序后直接判为未完成事件
                            eventDao.updateEventStateNowById(myEvent.getId(),3);
                            System.out.println("初始化今日事件状态，"+myEvent.getId()+":"+myEvent.getEventStateNow());
                        }
                        else if (myEvent.getEventStateNow() == 0){
                            //若待办事件的开始时间超过五分钟，直接转为未完成事件
                            int eventStartTimeNum = myEvent.getHourStart()*60+myEvent.getMinuteStart();
                            int nowTimeNum = todayEventList.myHour*60+todayEventList.myMinute;
                            if ((nowTimeNum-eventStartTimeNum)>5){
                                System.out.println("超过五分钟，直接转为未完成事件"+myEvent.getId());
                                eventDao.updateEventStateNowById(myEvent.getId(),3);
                            }

                        }
                    }
                    finish();


                }





            }
        });





        //注册页面跳转
        textViewRedister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,RegisterActivity.class );
                startActivity(intent);

            }


        });

    }


    //登录
    public boolean checkLogin(){
        boolean flag = false;
        UserDao userDao = new UserDao(this);
        myUser.setUserName(editTextName.getText().toString());
        myUser.setUserPassword(editTextPassword.getText().toString());
        tableUser = userDao.checkUser(myUser.getUserName());
        for (int i = 0 ; i <tableUser.size();i++) {
            if (tableUser.get(i).getUserName().equals(myUser.getUserName())){
                findUser = tableUser.get(i);
                break;
            }
        }

        if (!myUser.getUserName().equals(findUser.getUserName())){
            Toast.makeText(LoginActivity.this,"登录失败,用户名不存在",Toast.LENGTH_LONG).show();
        }
        else if (!myUser.getUserPassword().equals(findUser.getUserPassword()))
        {
            Toast.makeText(LoginActivity.this,"登录失败,密码不正确",Toast.LENGTH_LONG).show();

        }else {

            //记录当前用户信息
            MyApplication.setThisUser(findUser);

            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            flag = true;

        }

        return flag;
        
        
    }


    //初始化
//    public static void init (Context context){
//        UserDao userDao = new UserDao(context);
//        EventDao eventDao = new EventDao(context);
//        if (findUser.getLastLoginDay() != MyApplication.todayEventList.myDay){
//            userDao.changelastLoginDay(findUser.getUid(),MyApplication.todayEventList.myDay);
//            userDao.changethisDayCompletedNum(findUser.getUid(),0);
//        }
//        if (findUser.getLastLoginMonth() != MyApplication.todayEventList.myMonth){
//            userDao.changelastLoginMonth(findUser.getUid(),MyApplication.todayEventList.myMonth);
//            userDao.changethisMonthCompletedNum(findUser.getUid(),0);
//
//        }
//        System.out.println("初始化1"+findUser.getLastLoginDay()+"   "+MyApplication.todayEventList.myDay+ "   " +findUser.getUid());
//        System.out.println("初始化2"+findUser.getLastLoginMonth()+"   "+MyApplication.todayEventList.myMonth+ "   " +findUser.getUid());
//
//
//        userDao.changeEventprogress(findUser.getUid(),0);
//
//
//
//
//
//    }



    public void setAlarmWindow(String eventName, int startHour, int startMinute, int num){

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());

        //设置在几点提醒  设置的为0点
        mCalendar.set(Calendar.HOUR_OF_DAY, startHour);
        //设置在几分提醒  设置的为0分
        mCalendar.set(Calendar.MINUTE, startMinute);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("eventName",eventName);

        intent.setComponent(new ComponentName("com.moca.mechanicallife2",
                "com.moca.mechanicallife2.Utils.AlarmWindowReceiver"));

        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), num, intent, 0);

        //得到AlarmManager实例
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pi);


    }

    public static DateAndTime getTodayEventList(){
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

        System.out.println("今日时间："+dateAndTime.myMonth+"---"+dateAndTime.myDay);

        return dateAndTime;
    }





}
