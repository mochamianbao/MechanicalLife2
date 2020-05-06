package com.moca.mechanicallife2.Utils;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;

public class AlarmWindowReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String content = intent.getStringExtra("eventName");
        Log.d("onReceive2","content is -————>"+content);


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle("事件开始提醒");
        dialogBuilder.setMessage("计划中的"+ content+"事件开始了，请在五分钟内在事件详情中开始计划");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        switch (which) {
                            case AlertDialog.BUTTON_POSITIVE:
                                Log.d("onReceive1", "action is -————>" + 999);

                                Log.d("onReceive2", "content is -————>" + 888);
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
