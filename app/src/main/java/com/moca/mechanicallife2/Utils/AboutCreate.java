package com.moca.mechanicallife2.Utils;

import android.util.Log;

import com.moca.mechanicallife2.myentity.MyEvent;

import java.text.DecimalFormat;

public class AboutCreate {

    public static final int REFE_NUMBER = 1080;//(24-6)*60
    private int hourStart;
    private int hourEnd;
    private int minuteStart;
    private int minuteEnd;
    public int thisViewHight;
    private int superViewHight;

    public AboutCreate(MyEvent myEvent,int superViewHight) {
        hourStart =   myEvent.getHourStart();
        hourEnd =     myEvent.getHourEnd();
        minuteStart = myEvent.getMinuteStart();
        minuteEnd =   myEvent.getMinuteEnd();
        this.superViewHight = superViewHight;

//        Log.i("测试1","+++++++++++++++"+ superViewHight);

    }


        public int computeHiget(){
            thisViewHight =((hourEnd*60+minuteEnd-hourStart*60-minuteStart)*superViewHight)/REFE_NUMBER;

//            Log.i("测试2","+++++++++++++++"+ thisViewHight);

            return thisViewHight;

        }

        public int computeFristHiget(){
            thisViewHight =((hourStart*60+minuteStart-360)*superViewHight)/REFE_NUMBER;

//            Log.i("top","+++++++++++++++"+ thisViewHight);

            return thisViewHight;

        }



}
