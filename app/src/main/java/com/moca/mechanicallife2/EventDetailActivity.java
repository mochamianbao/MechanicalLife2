package com.moca.mechanicallife2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.moca.mechanicallife2.dao.EventDao;
import com.moca.mechanicallife2.dao.UserDao;
import com.moca.mechanicallife2.databinding.ActivityCreateEventBinding;
import com.moca.mechanicallife2.databinding.ActivityEventDetailBinding;
import com.moca.mechanicallife2.myentity.MyEvent;
import com.moca.mechanicallife2.myentity.MyEventInfo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class EventDetailActivity extends AppCompatActivity {

    private List<MyEvent> myEvents;
    int thisHour;
    int thisMinute;
    int thisWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ActivityEventDetailBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_event_detail);


        Bundle bundle =getIntent().getExtras();
        int findId = bundle.getInt("id");
        //获取Dao数据操作类
        final EventDao eventDao = new EventDao(this);
        //获取用户数据操作类
        final UserDao userDao = new UserDao(this);
        //根据ID找出数据并存入MyEvent对象
        final MyEvent myEvent = eventDao.queryById(findId);
        //创建MyEventInfo对象处理数据
        MyEventInfo myEventInfo = new MyEventInfo(myEvent);
        binding.setThisEventDetail(myEventInfo);


        //开始事件确定按钮
        binding.planStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog alertDialog1 = new AlertDialog.Builder(EventDetailActivity.this)
                        .setTitle("开始计划事件")//标题
                        .setMessage("是否确定开始执行"+myEvent.getEventName()+"事件")//内容
                        .setIcon(R.mipmap.ic_launcher)//图标
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“确认”后的操作

                                getTime();
                                int eventStartTimeNum = myEvent.getHourStart()*60+myEvent.getMonthStart();
                                int nowTimeNum = thisHour*60+thisMinute;


                                if (isTodayDo(myEvent) == 0){
                                    Toast.makeText(EventDetailActivity.this,"此事件不在今日执行",Toast.LENGTH_LONG).show();
                                }
                                else if (myEvent.getEventStateNow()==1){
                                    Toast.makeText(EventDetailActivity.this,"此事件进行中",Toast.LENGTH_LONG).show();
                                }
                                else if (myEvent.getEventStateNow()==2){
                                    Toast.makeText(EventDetailActivity.this,"今天已完成此事件",Toast.LENGTH_LONG).show();
                                }
                                else if (myEvent.getEventStateNow()==3){
                                    Toast.makeText(EventDetailActivity.this,"此事件今天已过时",Toast.LENGTH_LONG).show();
                                }
                                else if ((nowTimeNum-eventStartTimeNum)>5){
                                    Toast.makeText(EventDetailActivity.this,"离事件开始已超过5分钟，今天不能再进行，请下次按时完成",Toast.LENGTH_LONG).show();
                                }
                                else if (userDao.findEventprogress(MyApplication.getThisUser().getUid()) ==  0){
                                System.out.println(myEvent.getEventName()+"   "+myEvent.getEventStateNow());
                                eventDao.updateEventStateNowById(myEvent.getId(),1);//设置当前事件状态为进行中
                                userDao.changeEventprogress(MyApplication.getThisUser().getUid(),1);
                                setFinishWindow(myEvent.getEventName(),myEvent.getId(),myEvent.getHourEnd(),myEvent.getMinuteEnd(),0);
                                    Toast.makeText(EventDetailActivity.this,myEvent.getEventName()+"事件开始执行，请不要中途退出程序",Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(EventDetailActivity.this,"已经有进行的事件了",Toast.LENGTH_LONG).show();

                                }
                                finish();

                            }
                        }).show() ;
                alertDialog1.show();

            }
        });
        //修改事件时间确定按钮
        binding.planDelayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog alertDialog1 = new AlertDialog.Builder(EventDetailActivity.this)
                        .setTitle("修改计划事件时间")//标题
                        .setMessage("是否确定修改"+myEvent.getEventName()+"事件的时间")//内容
                        .setIcon(R.mipmap.ic_launcher)//图标
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“确认”后的操作

                                Intent intent= new Intent(EventDetailActivity.this,ReviseEventTimeActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("id",myEvent.getId());
                                intent.putExtras(bundle);
                                startActivity(intent);


                            }
                        }).show() ;
                alertDialog1.show();

            }
        });

        //借来测试
        //确定按钮返回
        binding.planEventBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();


            }
        });

    }

    public void setFinishWindow(String eventName,int eventId, int endHour, int endMinute, int num){


        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());

        //设置在几点提醒  设置的为0点
        mCalendar.set(Calendar.HOUR_OF_DAY, endHour);
        //设置在几分提醒  设置的为0分
        mCalendar.set(Calendar.MINUTE, endMinute);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("eventId",eventId);
        bundle.putString("eventName",eventName);

        intent.putExtras(bundle);
//        intent.putExtra("endHour",endHour);
//        intent.putExtra("endMinute",endMinute);
//        intent.putExtra("num",num);
        intent.setComponent(new ComponentName("com.moca.mechanicallife2",
                "com.moca.mechanicallife2.Utils.FinishWindowReceiver"));
//        String content = input.getText().toString();
//        intent.putExtra(Constants.KEY_CONTENT,content);
        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), num, intent, 0);

        //得到AlarmManager实例
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pi);


    }


    //获取当前时间
    private void getTime() {
        Calendar cal=Calendar.getInstance();
//        cal.add(Calendar.DAY_OF_MONTH,1);
        thisHour=cal.get(Calendar.HOUR_OF_DAY);
        thisMinute =cal.get(Calendar.MINUTE);
        thisWeek = cal.get(Calendar.DAY_OF_WEEK);

    }

    //观察是否今天执行
    public int isTodayDo(MyEvent myEvent){
        int flag = 0;
        if (thisWeek == 2){
            flag = myEvent.getWeek1();
        }
        else if (thisWeek == 3){
            flag = myEvent.getWeek2();
        }
        else if (thisWeek == 4){
            flag = myEvent.getWeek3();
        }
        else if (thisWeek == 5){
            flag = myEvent.getWeek4();
        }
        else if (thisWeek == 6){
            flag = myEvent.getWeek5();
        }
        else if (thisWeek == 7){
            flag = myEvent.getWeek6();
        }
        else if (thisWeek == 1){
            flag = myEvent.getWeek7();
        }

        return flag;

    }


}
