package com.moca.mechanicallife2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.moca.mechanicallife2.dao.EventDao;
import com.moca.mechanicallife2.databinding.ActivityMainBinding;
import com.moca.mechanicallife2.myentity.DateAndTime;
import com.moca.mechanicallife2.myentity.MyEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    //定义属性
    private TextView tab_1,tab_2,tab_3;//底部标签
    private ViewPager viewPager;//切换区
    private List<Fragment> fragmentList;
    private BottomFragmentAdapter fragmentAdapter;//适配器


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        //初始化
        initUI();
        initTab();



//        EventDao eventDao = new EventDao(this);
//        List<MyEvent> myEvents = new ArrayList();
//        DateAndTime todayEventList = getTodayEventList();
//        myEvents = eventDao.queryAllByDate(todayEventList.getMyDateNumber(),todayEventList.strWeekFlag);
//
//        for (int i = 0; i < myEvents.size(); i++) {
//            MyEvent myEvent = myEvents.get(i);
//            setAlarmWindow(myEvent.getEventName(),myEvent.getHourStart(),myEvent.getMinuteStart(),i);
//            System.out.println("55555555"+" "+myEvent.getEventName()+" "+myEvent.getHourStart()+" "+myEvent.getMinuteStart());
//        }



    }


    /**
     * 初始化
     */
    private void initUI(){
        //初始化底部标签
        tab_1 = findViewById(R.id.bottom_btn1);
        tab_2 = findViewById(R.id.bottom_btn2);
        tab_3 = findViewById(R.id.bottom_btn3);

        //为底部标签添加点击事件
        tab_1.setOnClickListener(this);
        tab_2.setOnClickListener(this);
        tab_3.setOnClickListener(this);

        //初始化切换区
        viewPager = findViewById(R.id.main_viewpager);
    }


    /**
     * 初始化Fragment及第一个显示的标签
     */
    private void initTab(){
        //新建Fragment
        MainPlanFragment fragment1 = new MainPlanFragment();
        StatisticsFragment fragment2 = new StatisticsFragment();
        UserInfoFragment fragment3 = new UserInfoFragment();

        //建立列表

        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);

        //新建适配器
        fragmentAdapter = new BottomFragmentAdapter(getSupportFragmentManager(),fragmentList);
        //配置适配器
        viewPager.setAdapter(fragmentAdapter);

        //设置滑动监听
        viewPager.addOnPageChangeListener(new PageChangeListennr());

        //显示第一个页面
        showFragment(0);
    }


    /**
     * 显示Fragment
     */

    private void showFragment(int num){
        //按索引显示Fragment
        viewPager.setCurrentItem(num);
        //改变底部标签
        if (num == 0){
            tab_1.setBackgroundColor(Color.RED);
            tab_2.setBackgroundColor(Color.WHITE);
            tab_3.setBackgroundColor(Color.WHITE);
        }else if (num == 1){
            tab_1.setBackgroundColor(Color.WHITE);
            tab_2.setBackgroundColor(Color.RED);
            tab_3.setBackgroundColor(Color.WHITE);
        }else if (num == 2){
            tab_1.setBackgroundColor(Color.WHITE);
            tab_2.setBackgroundColor(Color.WHITE);
            tab_3.setBackgroundColor(Color.RED);
        }
    }

    /**
     * 底部标签点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.bottom_btn1){
            //第一个标签点击时
            showFragment(0);
        }else if (v.getId() == R.id.bottom_btn2){
            //第二个标签点击时
            showFragment(1);
        }else if (v.getId() == R.id.bottom_btn3){
            //第三个标签点击时
            showFragment(2);
        }
    }


    /**
     * 定义页面滑动的监听类，用于页面滑动是，底部导航跟着变化
     */

    public class PageChangeListennr implements ViewPager.OnPageChangeListener{


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        //页面选中是调用
        @Override
        public void onPageSelected(int position) {
            if (position == 0){
                tab_1.setBackgroundColor(Color.RED);
                tab_2.setBackgroundColor(Color.WHITE);
                tab_3.setBackgroundColor(Color.WHITE);
            }else if (position == 1){
                tab_1.setBackgroundColor(Color.WHITE);
                tab_2.setBackgroundColor(Color.RED);
                tab_3.setBackgroundColor(Color.WHITE);
            }else if (position == 2){
                tab_1.setBackgroundColor(Color.WHITE);
                tab_2.setBackgroundColor(Color.WHITE);
                tab_3.setBackgroundColor(Color.RED);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 设定每天的闹钟弹窗
     * 事件名称，设置小时，设置分钟，弹窗识别号
     *
     */

    public void setAlarmWindow(String eventName, int hour, int minute,int num){

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
