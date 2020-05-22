package com.moca.mechanicallife2.Utils;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.moca.mechanicallife2.MyApplication;
import com.moca.mechanicallife2.dao.EventDao;
import com.moca.mechanicallife2.dao.UserDao;
import com.moca.mechanicallife2.myentity.MyEvent;

public class FinishWindowReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        String content = intent.getStringExtra("eventName");
        final int eventId = intent.getIntExtra("eventId",0);
        final EventDao eventDao = new EventDao(context);
        final UserDao userDao = new UserDao(context);
        final MyEvent myEvent = eventDao.queryById(eventId);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle("事件结束提醒");
        dialogBuilder.setMessage("计划中的"+ content+"事件结束了，请点击确定完成事件(若中途离开程序会直接判定未完成)");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case AlertDialog.BUTTON_POSITIVE:
                                   eventDao.updateEventStateNowById(eventId,2);
                                    userDao.changethisDayCompletedNum(MyApplication.getThisUser().getUid(),MyApplication.getThisUser().getThisDayCompletedNum()+1);
                                    userDao.changethisWeekCompletedNum(MyApplication.getThisUser().getUid(),MyApplication.getThisUser().getThisWeekCompletedNum()+1);
                                    userDao.changethisMonthCompletedNum(MyApplication.getThisUser().getUid(),MyApplication.getThisUser().getThisMonthCompletedNum()+1);
                                    userDao.changethisUserCompletedNum(MyApplication.getThisUser().getUid(),MyApplication.getThisUser().getThisUserCompletedNum()+1);
                                    userDao.changeEventprogress(MyApplication.getThisUser().getUid(),0);
                                    System.out.println("FinishWindowReceiver完成");

                                break;
                            default:
                                break;
                        }

                    }
                });
        AlertDialog alertDialog = dialogBuilder.create();

        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        alertDialog.show();


    }
}
