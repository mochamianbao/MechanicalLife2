package com.moca.mechanicallife2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.moca.mechanicallife2.dao.EventDao;
import com.moca.mechanicallife2.databinding.ActivityReviseEventTimeBinding;
import com.moca.mechanicallife2.myentity.DateAndTime;
import com.moca.mechanicallife2.myentity.MyEvent;

import java.util.Calendar;

public class ReviseEventTimeActivity extends AppCompatActivity {

    private TextView resetTimeText1,resetTimeText2;

    private DateAndTime startDate = new DateAndTime();
    private DateAndTime endDate = new DateAndTime();
    private int hour, myMinute;
    private MyEvent myEvent;
    private int[] weekArray = new int[7];
    EventDao eventDao = new EventDao(this);
    int changeId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revise_event_time);
        ActivityReviseEventTimeBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_revise_event_time);

        Bundle bundle =getIntent().getExtras();
        changeId = bundle.getInt("id");
        Calendar cal = Calendar.getInstance();
        startDate.myYear=cal.get(Calendar.YEAR);       //获取年月日时分秒
        startDate.myMonth=cal.get(Calendar.MONTH)+1;   //获取到的月份是从0开始计数
        startDate.myDay=cal.get(Calendar.DAY_OF_MONTH);

        resetTimeText1= binding.resetTimeTextView1;
        resetTimeText2= binding.resetTimeTextView2;
        resetTimeText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeOnClick(v);
            }
        });
        resetTimeText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeOn2Click(v);

            }
        });


        myEvent = eventDao.queryById(changeId);
        endDate.myYear = myEvent.getYearEnd();
        endDate.myMonth = myEvent.getMonthEnd();
        endDate.myDay = myEvent.getDayEnd();
        weekArray[0] = myEvent.getWeek1();
        weekArray[1] = myEvent.getWeek2();
        weekArray[2] = myEvent.getWeek3();
        weekArray[3] = myEvent.getWeek4();
        weekArray[4] = myEvent.getWeek5();
        weekArray[5] = myEvent.getWeek6();
        weekArray[6] = myEvent.getWeek7();



        binding.reviseTimeSurebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeChange();
            }
        });





    }

    public void timeChange(){
        Boolean checkTalbe = eventDao.queryForCheckDataElseOne(startDate,endDate,weekArray,changeId);
        System.out.println("ReviseEventTimeActivity"+checkTalbe);

        if (startDate.myHour==0 && startDate.myMinute==0 ){
            Toast.makeText(this,"开始时间不能为空",Toast.LENGTH_LONG).show();
        }
        else if (endDate.myHour==0 && endDate.myMinute==0 ){
            Toast.makeText(this,"结束时间不能为空",Toast.LENGTH_LONG).show();
        }else if ((startDate.myHour>endDate.myHour)||(startDate.myHour == endDate.myHour && startDate.myMinute>endDate.myMinute) ){
            Toast.makeText(this,"结束时间不能设置在开始时间之前",Toast.LENGTH_LONG).show();
        }
        else if (startDate.myHour<6){
            Toast.makeText(this,"晚上12点到早上6点是睡眠时间，请保持充足睡眠",Toast.LENGTH_LONG).show();
        }
        else if (checkTalbe){
            Toast.makeText(this,"选择的日期时间与已在计划中的事件冲突，请重新选择",Toast.LENGTH_LONG).show();
        }
        else {
            eventDao.updateEventTime(startDate,endDate,changeId);
            Toast.makeText(this,"更改时间成功",Toast.LENGTH_LONG).show();
        }




    }



    //设置时间，开始时间
    public void timeOnClick(View view){
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                myMinute = minute;
                startDate.myHour = hour;
                startDate.myMinute = myMinute;
                if (myMinute < 10){
                    resetTimeText1.setText(hour +":"+"0"+ myMinute);
                }else {
                    resetTimeText1.setText(hour +":"+ myMinute);

                }
                Log.i("设置开始时间信息","---------------------"+hour+":"+myMinute);
            }
        };

        TimePickerDialog dialog = new TimePickerDialog(ReviseEventTimeActivity.this, AlertDialog.THEME_HOLO_DARK,listener,hour, myMinute,true);
        dialog.show();
    }

    //设置时间，结束时间
    public void timeOn2Click(View view){
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                myMinute = minute;
                endDate.myHour = hour;
                endDate.myMinute = myMinute;

                if (myMinute < 10){
                    resetTimeText2.setText(hour +":"+"0"+ myMinute);
                }else {
                    resetTimeText2.setText(hour +":"+ myMinute);

                }

                Log.i("设置结束时间信息","---------------------"+hour+":"+myMinute);
            }
        };

        TimePickerDialog dialog = new TimePickerDialog(ReviseEventTimeActivity.this, AlertDialog.THEME_HOLO_DARK,listener,hour, myMinute,true);
        dialog.show();
    }

}
