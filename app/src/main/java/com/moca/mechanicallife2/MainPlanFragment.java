package com.moca.mechanicallife2;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moca.mechanicallife2.Utils.AboutCreate;
import com.moca.mechanicallife2.dao.EventDao;
import com.moca.mechanicallife2.databinding.FragmentMainPlanBinding;
import com.moca.mechanicallife2.myentity.MyEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainPlanFragment extends Fragment {

    FragmentMainPlanBinding binding;
    private Calendar cal;
    private int year,month,day,week;
    private class DateandTime {
        int myYear;
        int myMonth;
        int myDay;
        String myWeek;
        DateandTime(int myYear,int myMonth,int myDay){
            this.myYear = myYear;
            this.myMonth = myMonth;
            this.myDay = myDay;
        }
    }

    //获取对应的日期信息
    public DateandTime setDateandTime(int i){




        //获取本星期第一天的信息
        cal=Calendar.getInstance();
        week=cal.get(Calendar.DAY_OF_WEEK);//获取今天星期几
        cal.add(Calendar.DAY_OF_MONTH,2-week);

        //获取目标日期的信息
        cal.add(Calendar.DAY_OF_MONTH,i-1);
        year=cal.get(Calendar.YEAR);       //获取年月日
        month=cal.get(Calendar.MONTH)+1;   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
        week=cal.get(Calendar.DAY_OF_WEEK);
        DateandTime dateandTime = new DateandTime(year,month,day);

        //国外以周日为第一天，这里调成国内惯用模式，周一为第一天
        switch (week){
            case 2:
                dateandTime.myWeek = "week1";
                break;
            case 3:
                dateandTime.myWeek = "week2";
                break;
            case 4:
                dateandTime.myWeek = "week3";
                break;
            case 5:
                dateandTime.myWeek = "week4";
                break;
            case 6:
                dateandTime.myWeek = "week5";
                break;
            case 7:
                dateandTime.myWeek = "week6";
                break;
            case 1:
                dateandTime.myWeek = "week7";
                break;
            default:
                break;
        }

        return dateandTime;

    }



    public MainPlanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //fragment绑定binding
        binding = FragmentMainPlanBinding.inflate(inflater);

        return binding.getRoot();
//        return inflater.inflate(R.layout.fragment_main_plan, container, false);
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //日期标题跳转的代码

        final DateandTime dateandTime1 = setDateandTime(1);
        binding.topWeek1.setText("周一"+dateandTime1.myMonth+"/"+dateandTime1.myDay);
        //跳转到日计划页面
        binding.topWeek1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ccc(1);
            }
        });


        final DateandTime dateandTime2 = setDateandTime(2);
        binding.topWeek2.setText("周二"+dateandTime2.myMonth+"/"+dateandTime2.myDay);
        //跳转到日计划页面
        binding.topWeek2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ccc(2);
            }

        });

        final DateandTime dateandTime3 = setDateandTime(3);
        binding.topWeek3.setText("周三"+dateandTime3.myMonth+"/"+dateandTime3.myDay);
        //跳转到日计划页面
        binding.topWeek3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ccc(3);
            }

        });

        final DateandTime dateandTime4 = setDateandTime(4);
        binding.topWeek4.setText("周四"+dateandTime4.myMonth+"/"+dateandTime4.myDay);
        //跳转到日计划页面
        binding.topWeek4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ccc(4);
            }

        });

        final DateandTime dateandTime5 = setDateandTime(5);
        binding.topWeek5.setText("周五"+dateandTime5.myMonth+"/"+dateandTime5.myDay);
        //跳转到日计划页面
        binding.topWeek5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ccc(5);
            }

        });


        final DateandTime dateandTime6 = setDateandTime(6);
        binding.topWeek6.setText("周六"+dateandTime6.myMonth+"/"+dateandTime6.myDay);
        //跳转到日计划页面
        binding.topWeek6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ccc(6);
            }

        });


        final DateandTime dateandTime7 = setDateandTime(7);
        binding.topWeek7.setText("周日"+dateandTime7.myMonth+"/"+dateandTime7.myDay);
        //跳转到日计划页面
        binding.topWeek7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ccc(7);
            }

        });

        binding.mainPlanCreateNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),CreateEventActivity.class));
            }
        });


    }


    //绘制事件，onResume方法在每次重新打开页面时都会再次执行
    @Override
    public void onResume() {
        super.onResume();
        //清除之前绘制的子控件
        binding.linearLayout1.removeAllViews();
        createMyView(dayEvents(1),binding.linearLayout1);
        binding.linearLayout2.removeAllViews();
        createMyView(dayEvents(2),binding.linearLayout2);
        binding.linearLayout3.removeAllViews();
        createMyView(dayEvents(3),binding.linearLayout3);
        binding.linearLayout4.removeAllViews();
        createMyView(dayEvents(4),binding.linearLayout4);
        binding.linearLayout5.removeAllViews();
        createMyView(dayEvents(5),binding.linearLayout5);
        binding.linearLayout6.removeAllViews();
        createMyView(dayEvents(6),binding.linearLayout6);
        binding.linearLayout7.removeAllViews();
        createMyView(dayEvents(7),binding.linearLayout7);
    }

    //提取日期跳转的重复代码
    public void ccc(int i){
        final DateandTime dateandTime = setDateandTime(i);
        Intent intent = new Intent(getActivity(),DayPlanActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("year",dateandTime.myYear);
        bundle.putInt("month",dateandTime.myMonth);
        bundle.putInt("day",dateandTime.myDay);
        bundle.putInt("week",i);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //获取某一天的事件
    public List<MyEvent> dayEvents(int i){
        final DateandTime dateandTime = setDateandTime(i);
        //获取数据
        EventDao eventDao = new EventDao(getActivity());
        int findEventDateNumber = dateandTime.myYear*10000+dateandTime.myMonth*100+dateandTime.myDay;
        List<MyEvent> myEvents  = eventDao.queryAllByDate(findEventDateNumber,dateandTime.myWeek);
        return myEvents;
    }


    //设置子控件样式
    public void createMyView(List<MyEvent> getMyEvent,LinearLayout thisLinearLayout){

        int Topline = 0;
        int k = 0;
        for (int i = 0; i < getMyEvent.size(); i++) {

            AboutCreate aboutCreate2 = new AboutCreate(getMyEvent.get(i), 1558);
            Topline = aboutCreate2.computeFristHiget();//与顶部的距离

            //设置子控件之间的距离
            if(i == 0){
                k=Topline;
            }
            else {
                k=Topline-k;
            }

            String str = getMyEvent.get(i).getEventName();
            AboutCreate aboutCreate1 = new AboutCreate(getMyEvent.get(i), 1558);
            int thisEventHight = aboutCreate1.computeHiget();
//            Log.i("thisEventHight","+++++++++++++++"+ thisEventHight);
            TextView textView = new TextView(getActivity());
            textView.setText(str);
            textView.setTextSize(10);
            textView.setGravity(Gravity.CENTER);

            textView.setBackgroundColor(Color.RED);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,k,0,0);
            lp.height = thisEventHight;
            textView.setLayoutParams(lp);
            thisLinearLayout.addView(textView);
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

            final int thisId = getMyEvent.get(i).getId();
            //设置各个子控件的跳转
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),EventDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",thisId);
                    intent.putExtras(bundle);
                    startActivity(intent);                }
            });

            k=Topline+thisEventHight;
        }

    }


}
