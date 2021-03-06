package com.moca.mechanicallife2;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moca.mechanicallife2.Utils.MyDateUtil;
import com.moca.mechanicallife2.dao.EventDao;
import com.moca.mechanicallife2.databinding.FragmentMainPlanBinding;
import com.moca.mechanicallife2.databinding.FragmentStatisticsBinding;
import com.moca.mechanicallife2.myentity.DateAndTime;
import com.moca.mechanicallife2.myentity.MyEvent;

import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends Fragment {


    FragmentStatisticsBinding binding;
    public StatisticsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentStatisticsBinding.inflate(inflater);

        return binding.getRoot();
//        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onResume() {
        super.onResume();
        final MyDateUtil myDateUtil = new MyDateUtil();
        myDateUtil.getMonthFrist();
        myDateUtil.getMonthLast();
        myDateUtil.getWeekFrist();
        myDateUtil.getWeekLast();
        //获得本月计划事件数
        int monthPlanNumber = getSpanPlanNumber(myDateUtil.getMonthFrist(), myDateUtil.getMonthLast());
        //获得本周计划事件数
        int weekPlanNumber = getSpanPlanNumber(myDateUtil.getWeekFrist(),myDateUtil.getWeekLast());
        //获得本日计划事件数
        int dayPlanNumber = getSpanPlanNumberToday();

        binding.thisWeekPlan.setText(String.valueOf(weekPlanNumber));
        binding.thisMonthPlan.setText(String.valueOf(monthPlanNumber));
        binding.thisDayPlan.setText(String.valueOf(dayPlanNumber));

        binding.thisDayFinish.setText(String.valueOf(MyApplication.getThisUser().getThisDayCompletedNum()));
        binding.thisWeekFinish.setText(String.valueOf(MyApplication.getThisUser().getThisWeekCompletedNum()));
        binding.thisMonthFinish.setText(String.valueOf(MyApplication.getThisUser().getThisMonthCompletedNum()));


        binding.dayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ShowEventListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("startDateNumber",getTodayDate().getMyDateNumber());
                bundle.putInt("endDateNumber",getTodayDate().getMyDateNumber());
                bundle.putInt("week",getTodayDate().myWeek);
                bundle.putInt("flag",0);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        binding.weekLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ShowEventListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("startDateNumber",myDateUtil.getWeekFrist().myDateNumber);
                bundle.putInt("endDateNumber",myDateUtil.getWeekLast().myDateNumber);
                bundle.putInt("flag",1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        binding.monthLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ShowEventListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("startDateNumber",myDateUtil.getMonthFrist().myDateNumber);
                bundle.putInt("endDateNumber",myDateUtil.getMonthLast().myDateNumber);
                bundle.putInt("flag",1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



        binding.statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


    public int getSpanPlanNumber(DateAndTime startDate, DateAndTime endDate){
        EventDao eventDao = new EventDao(getActivity());
        List<MyEvent> list = eventDao.querySpanByDate(startDate.myDateNumber, endDate.myDateNumber);
        int fristWeek = startDate.myWeek-1;//为方便遍历进行减一处理，循环中会加上
        Calendar calendar = startDate.myCalendar;
        Log.i("结果","进来了吗0000"+endDate.myDay+"-"+startDate.myDay);
        int num= 0;
        for (int i = 0 ; i < endDate.myDay - startDate.myDay+1 ; i++){
            int nowDateNumber =calendar.get(Calendar.YEAR)*10000+(calendar.get(Calendar.MONTH)+1)*100+calendar.get(Calendar.DAY_OF_MONTH);
            String eventWeekState = "";
            switch (fristWeek+1){
                case 1:
                    eventWeekState =" week1 ";
                    break;
                case 2:
                    eventWeekState =" week2 ";
                    break;
                case 3:
                    eventWeekState =" week3 ";
                    break;
                case 4:
                    eventWeekState =" week4 ";
                    break;
                case 5:
                    eventWeekState =" week5 ";
                    break;
                case 6:
                    eventWeekState =" week6 ";
                    break;
                case 7:
                    eventWeekState =" week7 ";
                default:
                    break;
            }
            num = num+eventDao.queryIdByDate(nowDateNumber,eventWeekState);


            calendar.add(Calendar.DATE,1);
            fristWeek=(fristWeek+1)%7;
//            System.out.println("周"+(fristWeek+1)+":"+list.get(i).getEventName());

        }

        Log.i("结果",":"+num);

        return num;

    }

    public int getSpanPlanNumberToday(){
        DateAndTime dateAndTime = getTodayDate();
        EventDao eventDao = new EventDao(getActivity());

        int num = 0 ;


        String str = "";
        switch (dateAndTime.myWeek){
            case 1:
                str =" week7 ";
                break;
            case 2:
                str =" week1 ";
                break;
            case 3:
                str =" week2 ";
                break;
            case 4:
                str =" week3 ";
                break;
            case 5:
                str =" week4 ";
                break;
            case 6:
                str =" week5 ";
                break;
            case 7:
                str =" week6 ";
            default:
                break;
        }
        num = eventDao.queryIdByDate(dateAndTime.getMyDateNumber(),str);
        System.out.println("统计页面测试"+dateAndTime.getMyDateNumber());
     return num;
    }
    public DateAndTime getTodayDate(){
        DateAndTime dateAndTime = new DateAndTime();
        Calendar cal = Calendar.getInstance();
        dateAndTime.myYear = cal.get(Calendar.YEAR);
        dateAndTime.myMonth = cal.get(Calendar.MONTH)+1;
        dateAndTime.myDay = cal.get(Calendar.DAY_OF_MONTH);
        dateAndTime.myWeek = cal.get(Calendar.DAY_OF_WEEK);
        return dateAndTime;
    }


}
