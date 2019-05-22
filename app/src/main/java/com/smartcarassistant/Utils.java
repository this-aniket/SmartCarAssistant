package com.smartcarassistant;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

/**
 * Created by lenovo on 25-02-2018.
 */

public class Utils extends NotificationActivity {
    public static NotificationManager mManager;
    private  String str;




    @SuppressWarnings("static-access")
    public  static void generatePUCNotification(Context context) {

        NotificationCompat.Builder nb = new NotificationCompat.Builder(context, "str");
        nb.setSmallIcon(R.mipmap.ic_launcher);
        nb.setContentTitle("PUC Renewal");
        nb.setContentText("PUC");
        nb.setTicker("Take a look");
        nb.setAutoCancel(true);


        //get the bitmap to show in notification bar
        Bitmap bitmap_image = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        NotificationCompat.BigPictureStyle s = new NotificationCompat.BigPictureStyle().bigPicture(bitmap_image);
        s.setSummaryText("PUC Renewal coming soon");
        nb.setStyle(s);


        Intent resultIntent = new Intent(context, NotificationActivity.class);
        TaskStackBuilder TSB = TaskStackBuilder.create(context);
        TSB.addParentStack(NotificationActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        TSB.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                TSB.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        nb.setContentIntent(resultPendingIntent);
        nb.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(11221, nb.build());


    }
    @SuppressWarnings("static-access")
    public  static void generateCarServicingNotification(Context context) {

        NotificationCompat.Builder nb = new NotificationCompat.Builder(context, "str");
        nb.setSmallIcon(R.mipmap.ic_launcher);
        nb.setContentTitle("Car Servicing");
        nb.setContentText("Car");
        nb.setTicker("Take a look");
        nb.setAutoCancel(true);


        //get the bitmap to show in notification bar
        Bitmap bitmap_image = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        NotificationCompat.BigPictureStyle s = new NotificationCompat.BigPictureStyle().bigPicture(bitmap_image);
        s.setSummaryText("Car Servicing date ahead");
        nb.setStyle(s);


        Intent resultIntent = new Intent(context, NotificationActivity.class);
        TaskStackBuilder TSB = TaskStackBuilder.create(context);
        TSB.addParentStack(NotificationActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        TSB.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                TSB.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        nb.setContentIntent(resultPendingIntent);
        nb.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.

        try{
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(11221, nb.build());
        }catch(Exception e)
        {
            e.printStackTrace();
            FileLog.e(e);
        }


    }
    @SuppressWarnings("static-access")
    public  static void generatePetrolNotification(Context context) {

        NotificationCompat.Builder nb = new NotificationCompat.Builder(context, "str");
        nb.setSmallIcon(R.mipmap.ic_launcher);
        nb.setContentTitle("Petrol Check");
        nb.setContentText("Petrol");
        nb.setTicker("Take a look");
        nb.setAutoCancel(true);


        //get the bitmap to show in notification bar
        Bitmap bitmap_image = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        NotificationCompat.BigPictureStyle s = new NotificationCompat.BigPictureStyle().bigPicture(bitmap_image);
        s.setSummaryText("Please check your petrol");
        nb.setStyle(s);


        Intent resultIntent = new Intent(context, NotificationActivity.class);
        TaskStackBuilder TSB = TaskStackBuilder.create(context);
        TSB.addParentStack(NotificationActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        TSB.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                TSB.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        nb.setContentIntent(resultPendingIntent);
        nb.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(11221, nb.build());
    }
    @SuppressWarnings("static-access")
    public  static void generateTyreNotification(Context context) {

        NotificationCompat.Builder nb = new NotificationCompat.Builder(context, "str");
        nb.setSmallIcon(R.mipmap.ic_launcher);
        nb.setContentTitle("Tyre ");
        nb.setContentText("Tyre ");
        nb.setTicker("Tyre");
        nb.setAutoCancel(true);


        //get the bitmap to show in notification bar
        Bitmap bitmap_image = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        NotificationCompat.BigPictureStyle s = new NotificationCompat.BigPictureStyle().bigPicture(bitmap_image);
        s.setSummaryText("Please check your Tyre Pressure ");
        nb.setStyle(s);


        Intent resultIntent = new Intent(context, NotificationActivity.class);
        TaskStackBuilder TSB = TaskStackBuilder.create(context);
        TSB.addParentStack(NotificationActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        TSB.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                TSB.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        nb.setContentIntent(resultPendingIntent);
        nb.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(11221, nb.build());


    }
}
