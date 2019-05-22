package com.smartcarassistant;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by lenovo on 25-02-2018.
 */

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String str2;
        Bundle bundle=intent.getExtras();
        str2=bundle.getString("key");


      /*Intent service1 = new Intent(context, MyAlarmService.class);
        context.startService(service1);*/
        Log.e("App", "called receiver method");
        try{



            if(str2.equals("PUC"))
            {
            Utils.generatePUCNotification(context);
            }
            else  if(str2.equals("Car Servicing"))
            {
                Utils.generateCarServicingNotification(context);
            }
            else if(str2.equals("Petrol"))
            {
                Utils.generatePetrolNotification(context);
            }
            else if(str2.equals("Tyre"))
            {
                Utils.generateTyreNotification(context);
            }
            else
            {
                Log.d("App", "wrong input");
            }



        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
