package com.moca.mechanicallife2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.moca.mechanicallife2.dao.EventDao;
import com.moca.mechanicallife2.databinding.ActivityCreateEventBinding;
import com.moca.mechanicallife2.databinding.ActivityEventDetailBinding;
import com.moca.mechanicallife2.myentity.MyEvent;
import com.moca.mechanicallife2.myentity.MyEventInfo;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class EventDetailActivity extends AppCompatActivity {

    private List<MyEvent> myEvents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ActivityEventDetailBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_event_detail);


        Bundle bundle =getIntent().getExtras();
        int findId = bundle.getInt("id");
        //获取Dao数据操作类
        final EventDao eventDao = new EventDao(this);
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
                                if (MyApplication.getHaveEventProgress()==0){
                                System.out.println(myEvent.getEventName()+"   "+myEvent.getEventStateNow());
                                eventDao.updateEventStateNowById(myEvent.getId(),1);
                                MyApplication.setHaveEventProgress(1);
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
}
