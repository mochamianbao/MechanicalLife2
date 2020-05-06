package com.moca.mechanicallife2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.moca.mechanicallife2.dao.EViewModel;
import com.moca.mechanicallife2.dao.EventDao;
import com.moca.mechanicallife2.databinding.ActivityCreateEventBinding;
import com.moca.mechanicallife2.myentity.DateAndTime;
import com.moca.mechanicallife2.myentity.MyEvent;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreateEventActivity extends AppCompatActivity {

    private TextView setTimeText1,setTimeText2,setDateText1,setDateText2;
    private Calendar cal;
    private int year,month,day;
    private int hour, myMinute2;
    private MyEvent myEvent = new MyEvent();
    private DateAndTime startDate = new DateAndTime();
    private DateAndTime endDate = new DateAndTime();
    private DateAndTime nowDate = new DateAndTime();

    EViewModel eViewModel;


    private int[] weekArray = new int[7];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        ActivityCreateEventBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_create_event);
//        eViewModel = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(EViewModel.class);
        setTimeText1 = binding.setTimeTextView1;
        setTimeText2 = binding.setTimeTextView2;
        setDateText1 = binding.setDateTextView1;
        setDateText2 = binding.setDateTextView2;


        getTime();
        getDate();
        //时间选择
        binding.setTimeTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeOnClick(v);
            }
        });
        binding.setTimeTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeOn2Click(v);

            }
        });
        binding.setDateTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateOnClick(v);
            }
        });
        binding.setDateTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date2OnClick(v);
            }
        });
        //事件类型选择
        binding.createEventChoosebox.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.createEventChoose1:
                        myEvent.setEventType(0);
                        break;
                    case R.id.createEventChoose2:
                        myEvent.setEventType(1);
                        break;
                    case R.id.createEventChoose3:
                        myEvent.setEventType(2);
                        break;
                    case R.id.createEventChoose4:
                        myEvent.setEventType(3);
                        break;
                    default:

                        break;
                }
            }
        });

        //重复星期选择,一周内哪几天要做这件事
        binding.weekCheck1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    myEvent.setWeek1(1);
                    weekArray[0]=1;
                }else {
                    myEvent.setWeek1(0);
                    weekArray[0]=0;
                }
            }
        });

        binding.weekCheck2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    myEvent.setWeek2(1);
                    weekArray[1]=1;
                }else {
                    myEvent.setWeek2(0);
                    weekArray[1]=0;
                }
            }
        });

        binding.weekCheck3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    myEvent.setWeek3(1);
                    weekArray[2]=1;
                }else {
                    myEvent.setWeek3(0);
                    weekArray[2]=0;
                }
            }
        });

        binding.weekCheck4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    myEvent.setWeek4(1);
                    weekArray[3]=1;
                }else {
                    myEvent.setWeek4(0);
                    weekArray[3]=0;
                }
            }
        });

        binding.weekCheck5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    myEvent.setWeek5(1);
                    weekArray[4]=1;
                }else {
                    myEvent.setWeek5(0);
                    weekArray[4]=0;
                }
            }
        });

        binding.weekCheck6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    myEvent.setWeek6(1);
                    weekArray[5]=1;
                }else {
                    myEvent.setWeek6(0);
                    weekArray[5]=0;
                }
            }
        });

        binding.weekCheck7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    myEvent.setWeek7(1);
                    weekArray[6]=1;
                }else {
                    myEvent.setWeek7(0);
                    weekArray[6]=0;
                }
            }
        });



        //点击按钮,新增事件
        binding.createEventBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Log.i("增加事件信息","---------------------------------");
                try {

                    createNewEvent();
                } catch (ParseException e) {

                    e.printStackTrace();
                }
            }
        });

    }







    //创建事件方法
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNewEvent() throws ParseException {
        EventDao eventDao = new EventDao(this);
        int weekAll = 0;
        for (int i = 0 ; i < 7;i++) {
            weekAll= weekAll+weekArray[i];
        }
        System.out.println(weekArray[0]+weekArray[1]+weekArray[2]+weekArray[3]+weekArray[4]+weekArray[5]+weekArray[6]);

        EditText editText =findViewById(R.id.setEventName);
        String inputText = editText.getText().toString();
        Log.i("传参方法设置结束时间信息","---------------------"+endDate.myHour+":"+endDate.myMinute);
        Boolean checkTalbe = eventDao.queryForCheckData(startDate,endDate,weekArray);
        Log.i("传参方法设置结束时间信息","---------------------"+endDate.myHour+":"+endDate.myMinute);



        if (inputText==null||"".equals(inputText)){
            Toast.makeText(this,"事件名字不能为空",Toast.LENGTH_LONG).show();
        }
        else if(inputText.length()>7){
            Toast.makeText(this,"事件名字长度不能大于7",Toast.LENGTH_LONG).show();
        }
        else if(startDate.myYear==0 && startDate.myMonth==0 && startDate.myDay==0){
            Toast.makeText(this,"开始日期不能为空",Toast.LENGTH_LONG).show();
        }
        else if (endDate.myYear==0 && endDate.myMonth==0 && endDate.myDay==0){
            Toast.makeText(this,"结束日期不能为空",Toast.LENGTH_LONG).show();
        }
        else if (startDate.myHour==0 && startDate.myMinute==0 ){
            Toast.makeText(this,"开始时间不能为空",Toast.LENGTH_LONG).show();
        }
        else if (endDate.myHour==0 && endDate.myMinute==0 ){
            Toast.makeText(this,"结束时间不能为空",Toast.LENGTH_LONG).show();
        }
        else if (countDifferDay(nowDate,startDate)<0 ){
            Toast.makeText(this,"开始日期不能设置在现在日期之前",Toast.LENGTH_LONG).show();
        }
        else if (countDifferDay(startDate,endDate)<0 ){
            Toast.makeText(this,"结束日期不能设置在开始日期之前",Toast.LENGTH_LONG).show();
        }
        else if ((startDate.myHour>endDate.myHour)||(startDate.myHour == endDate.myHour && startDate.myMinute>endDate.myMinute) ){
            Toast.makeText(this,"结束时间不能设置在开始时间之前",Toast.LENGTH_LONG).show();
        }
        else if (startDate.myHour<6){
            Toast.makeText(this,"晚上12点到早上6点是睡眠时间，请保持充足睡眠",Toast.LENGTH_LONG).show();
        }
        else if (weekAll==0){
            Toast.makeText(this,"请选择每周的工作日",Toast.LENGTH_LONG).show();
        }
        else if (checkTalbe){

            Toast.makeText(this,"选择的日期时间与已在计划中的事件冲突，请重新选择",Toast.LENGTH_LONG).show();
        }


        else{



//            Log.i("事件创建事件测试","月："+nowDate.myMonth+"----"+startDate.myMonth+"--日："+nowDate.myDay+"--"+startDate.myDay);
            //获取时间
            myEvent.setYearStart(startDate.myYear);
            myEvent.setMonthStart(startDate.myMonth);
            myEvent.setDayStart(startDate.myDay);
            myEvent.setYearEnd(endDate.myYear);
            myEvent.setMonthEnd(endDate.myMonth);
            myEvent.setDayEnd(endDate.myDay);
            myEvent.setHourStart(startDate.myHour);
            myEvent.setMinuteStart(startDate.myMinute);
            myEvent.setHourEnd(endDate.myHour);
            myEvent.setMinuteEnd(endDate.myMinute);
            //获取事件名字
            myEvent.setEventName(inputText);



            Log.i("增加事件信息","开始日期:"+myEvent.getYearStart()+"/"+myEvent.getMonthStart()+"/"+myEvent.getDayStart()
                    +"结束日期:"+myEvent.getYearEnd()+"/"+myEvent.getMonthEnd()+"/"+myEvent.getDayEnd()
                    +"开始时间:"+myEvent.getHourStart()+":"+myEvent.getMinuteStart()
                    +"结束时间:"+myEvent.getHourEnd()+":"+myEvent.getMinuteEnd()+
                    "事件类型"+myEvent.getEventType()+"事件名字"+myEvent.getEventName()+
                    "1:"+myEvent.getWeek1()+"2:"+myEvent.getWeek2()+"3:"+myEvent.getWeek3()+
                    "4:"+myEvent.getWeek4()+"5:"+myEvent.getWeek5()+"6:"+myEvent.getWeek6()+
                    "7:"+myEvent.getWeek7());
//
            eventDao.insert(myEvent);

//            finish();
        }



    }


    //计算两个日期相差天数
    @RequiresApi(api = Build.VERSION_CODES.O)
    public int countDifferDay(DateAndTime dateandTime1, DateAndTime dateandTime2) throws ParseException {
        LocalDate start = LocalDate.of(dateandTime1.myYear,dateandTime1.myMonth,dateandTime1.myDay);
        LocalDate end = LocalDate.of(dateandTime2.myYear,dateandTime2.myMonth,dateandTime2.myDay);
        int num = (int) (end.toEpochDay()-start.toEpochDay());

        Log.i("相差日期测试","*****************"+num);
        Log.i("获取日期1","年："+dateandTime1.myYear+",月："+(dateandTime1.myMonth+1)+",日："+dateandTime1.myDay);
        Log.i("获取日期2","年："+dateandTime2.myYear+",月："+(dateandTime2.myMonth+1)+",日："+dateandTime2.myDay);


        return num;
    }



    //获取当前日期
    private void getDate() {
        cal=Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒
        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
        nowDate.myYear = year;
        //为方便计算相差日期，此处+1处理，往后日期出错重点考虑此处
        nowDate.myMonth = month+1;
        nowDate.myDay = day;

        Log.i("获取当天日期","年："+year+",月："+(month+1)+",日："+day);

    }

    //设置日期，开始日期
    public void dateOnClick(View view) {
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker arg0, int year, int month, int day) {
                //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                setDateText1.setText(year+"-"+(++month)+"-"+day);
                startDate.myYear = year;
                startDate.myMonth = month;
                startDate.myDay = day;
                Log.i("设置日期，开始日期",year+","+month+","+day);
            }
        };
        //后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
        DatePickerDialog dialog=new DatePickerDialog(CreateEventActivity.this, AlertDialog.THEME_HOLO_DARK,listener,year,month,day);
        dialog.show();


    }

    //设置日期，结束日期
    public void date2OnClick(View view) {
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker arg0, int year, int month, int day) {
                //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                setDateText2.setText(year+"-"+(++month)+"-"+day);
                endDate.myYear = year;
                endDate.myMonth = month;
                endDate.myDay = day;
                Log.i("设置日期，结束日期",year+","+month+","+day);
            }
        };
        //后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
        DatePickerDialog dialog=new DatePickerDialog(CreateEventActivity.this, AlertDialog.THEME_HOLO_DARK,listener,year,month,day);
        dialog.show();


    }

    //获取当前时间
    private void getTime() {
        cal=Calendar.getInstance();
        hour=cal.get(Calendar.HOUR_OF_DAY);       //获取年月日时分秒
        Log.i("wxy","hour"+hour);
        myMinute2 =cal.get(Calendar.MINUTE);   //获取到的月份是从0开始计数
        nowDate.myHour = hour;
        nowDate.myMinute = myMinute2;
    }

    //设置时间，开始时间
    public void timeOnClick(View view){
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                myMinute2 = minute;
                startDate.myHour = hour;
                startDate.myMinute = myMinute2;
                if (myMinute2 < 10){
                    setTimeText1.setText(hour +":"+"0"+ myMinute2);
                }else {
                    setTimeText1.setText(hour +":"+ myMinute2);

                }
                Log.i("设置开始时间信息","---------------------"+hour+":"+myMinute2);
            }
        };

        TimePickerDialog dialog = new TimePickerDialog(CreateEventActivity.this, AlertDialog.THEME_HOLO_DARK,listener,hour, myMinute2,true);
        dialog.show();
    }

    //设置时间，结束时间
    public void timeOn2Click(View view){
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                myMinute2 = minute;
                endDate.myHour = hour;
                endDate.myMinute = myMinute2;



                if (myMinute2 < 10){
                    setTimeText2.setText(hour +":"+"0"+ myMinute2);
                }else {
                    setTimeText2.setText(hour +":"+ myMinute2);

                }

                Log.i("设置结束时间信息","---------------------"+hour+":"+myMinute2);
            }
        };

        TimePickerDialog dialog = new TimePickerDialog(CreateEventActivity.this, AlertDialog.THEME_HOLO_DARK,listener,hour, myMinute2,true);
        dialog.show();
    }













}
