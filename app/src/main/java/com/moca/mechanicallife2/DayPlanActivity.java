package com.moca.mechanicallife2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moca.mechanicallife2.Utils.AboutCreate;
import com.moca.mechanicallife2.dao.EventDao;
import com.moca.mechanicallife2.databinding.ActivityDayPlanBinding;
import com.moca.mechanicallife2.myentity.MyEvent;

import java.util.ArrayList;
import java.util.List;

public class DayPlanActivity extends AppCompatActivity {


    private LinearLayout linearLayout;
    private List<MyEvent> myEvents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_plan);

        ActivityDayPlanBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_day_plan);

        linearLayout = binding.dayPlanListLayout;

        Bundle bundle =getIntent().getExtras();
        int thisYear = bundle.getInt("year");
        int thisMonth = bundle.getInt("month");
        int thisDay = bundle.getInt("day");
        int thisWeek = bundle.getInt("week");
        String strWeekFlag = "";
        Log.i("增加时间信息","------------"+thisMonth+"---"+thisDay+"---"+thisWeek);
        binding.dayPlanThisDay.setText(thisMonth+"/"+thisDay);

        //星期的顺序在主计划页面处理过，此处不用处理
        switch (thisWeek){
            case 1:
                binding.dayPlanThisWeek.setText("周一");
                strWeekFlag = "week1";
                break;
            case 2:
                binding.dayPlanThisWeek.setText("周二");
                strWeekFlag = "week2";
                break;
            case 3:
                binding.dayPlanThisWeek.setText("周三");
                strWeekFlag = "week3";
                break;
            case 4:
                binding.dayPlanThisWeek.setText("周四");
                strWeekFlag = "week4";
                break;
            case 5:
                binding.dayPlanThisWeek.setText("周五");
                strWeekFlag = "week5";
                break;
            case 6:
                binding.dayPlanThisWeek.setText("周六");
                strWeekFlag = "week6";
                break;
            case 7:
                binding.dayPlanThisWeek.setText("周日");
                strWeekFlag = "week7";
                break;
            default:
                break;
        }

        //获取数据
        EventDao eventDao = new EventDao(this);
        int findEventDateNumber = thisYear*10000+thisMonth*100+thisDay;
        myEvents  = eventDao.queryAllByDate(findEventDateNumber,strWeekFlag);




        //测试子控件
        List<MyEvent> fortestEvent = new ArrayList<>();
        MyEvent myEvent4 = new MyEvent();
        myEvent4.setHourStart(8);
        myEvent4.setMinuteStart(0);
        myEvent4.setHourEnd(9);
        myEvent4.setMinuteEnd(0);
        myEvent4.setEventName("555555");
        MyEvent myEvent7 = new MyEvent();
        myEvent7.setHourStart(23);
        myEvent7.setMinuteStart(0);
        myEvent7.setHourEnd(24);
        myEvent7.setMinuteEnd(0);
        myEvent7.setEventName("88888");
        fortestEvent.add(myEvent4);
        fortestEvent.add(myEvent7);
//        aboutCreate = new AboutCreate(myEvent4,1558);
//        int i = aboutCreate.computeHiget();
//        Log.i("测试高度","+++++++++++++++"+ i);



        //测试
        if(myEvents.size()>0) {
            MyEvent myEvent = myEvents.get(0);

//        MyEvent myEvent = eventDao.queryById(findId);
            Log.i("增加事件信息", "开始日期:" + myEvent.getYearStart() + "/" + myEvent.getMonthStart() + "/" + myEvent.getDayStart()
                    + "结束日期:" + myEvent.getYearEnd() + "/" + myEvent.getMonthEnd() + "/" + myEvent.getDayEnd()
                    + "开始时间:" + myEvent.getHourStart() + ":" + myEvent.getMinuteStart()
                    + "结束时间:" + myEvent.getHourEnd() + ":" + myEvent.getMinuteEnd() +
                    "事件类型" + myEvent.getEventType() + "事件名字" + myEvent.getEventName() +
                    "-----week1" + myEvent.getWeek1() + "-----week2" + myEvent.getWeek2() + "-----week2" + myEvent.getWeek3() +
                    "-----week4" + myEvent.getWeek4() + "-----week5" + myEvent.getWeek5() + "-----week6" + myEvent.getWeek6() +
                    "-----week7" + myEvent.getWeek7());
        }else {
            Log.i("没有数据","---------------------------------+++++++++++++++++++++");
        }


        /////////////////////////////////////////////////////////////以下为跳转页面
        //跳转到创建事件页面
        binding.dayPlanCreateNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DayPlanActivity.this,CreateEventActivity.class));
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        linearLayout.removeAllViews();
        //加入事件列表的子控件
        createMyView(myEvents);
    }

    //加入事件列表方法实现
    public void createMyView(List<MyEvent> getMyEvent){

        int Topline = 0;
        int k = 0;
        for (int i = 0; i < getMyEvent.size(); i++) {

            AboutCreate aboutCreate = new AboutCreate(getMyEvent.get(i), 1558);
            Topline = aboutCreate.computeFristHiget();//与顶部的距离
            TextView textView = new TextView(this);

            //获取子控件名字
            String str = getMyEvent.get(i).getEventName();
            textView.setText(str);
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER);

            //获取子控件高度
            AboutCreate aboutCreate1 = new AboutCreate(getMyEvent.get(i), 1558);
            int thisEventHight = aboutCreate1.computeHiget();

            //设置子控件之间的距离，外上边距
            if(i == 0){
                k=Topline;
            }
            else {
                k=Topline-k;
            }
//            Log.i("thisEventHight","+++++++++++++++"+ thisEventHight);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,k,0,0);
            lp.height = thisEventHight;
            textView.setLayoutParams(lp);

            //设置子控件样式，根据事件类型选择颜色
            switch (getMyEvent.get(i).getEventType()){
                case 0:
                    textView.setBackgroundResource(R.drawable.corner_view1);
                    break;
                case 1:
                    textView.setBackgroundResource(R.drawable.corner_view2);
                    break;
                case 2:
                    textView.setBackgroundResource(R.drawable.corner_view3);
                    break;
                case 3:
                    textView.setBackgroundResource(R.drawable.corner_view4);
                    break;
                default:
                        break;
            }


            linearLayout.addView(textView);

            final int thisId = getMyEvent.get(i).getId();
                //设置各个子控件的跳转
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DayPlanActivity.this,EventDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",thisId);
                    intent.putExtras(bundle);
                    startActivity(intent);                }
            });

            //设置下一个子控件的外上边距
            k=Topline+thisEventHight;
        }

    }



    //获取控件高度,父控件，无用
    public int getViewhight(){
        ViewTreeObserver vto = linearLayout.getViewTreeObserver();
        final int[] height = new int[1];
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linearLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height[0] = linearLayout.getHeight();
//                Log.i("测试控件高度","+++++++++++++++"+ height[0]);
            }
        });
        return height[0];





    }




}
