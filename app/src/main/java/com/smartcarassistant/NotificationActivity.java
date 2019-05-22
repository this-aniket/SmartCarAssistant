package com.smartcarassistant;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

public class NotificationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    public String str1;
    private PendingIntent pendingIntent;
    Button btnotify;
    DatePicker dp;
    TimePicker tp;
    Spinner sp;
    MyReceiver myrec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        btnotify=(Button)findViewById(R.id.btNotify);
        dp=(DatePicker)findViewById(R.id.datePicker);
        tp=(TimePicker)findViewById(R.id.timePicker);
        sp=(Spinner)findViewById(R.id.spinner);
        myrec=new MyReceiver();
        //String[] features=new String[]{"PUC","Car Servicing","Petrol","Tyre"};
        //ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,features);
        //sp.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.Features,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);


        btnotify.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {


                long time;
                long yinmill=31536000000L;
                long moinmill=2628002880L;
                long dinmill=86400000L;
                long hinmill=3600000L;
                long miinmill=60000L;
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, dp.getYear());
                calendar.set(Calendar.MONTH, dp.getMonth());
                calendar.set(Calendar.DAY_OF_MONTH, dp.getDayOfMonth());
                calendar.set(Calendar.HOUR_OF_DAY, tp.getHour());
                calendar.set(Calendar.MINUTE, tp.getMinute());
                calendar.set(Calendar.SECOND, 0);
                //calendar.set(Calendar.AM_PM,Calendar.PM);
                //calendar.set(Calendar.AM_PM,Calendar.AM);

                time=calendar.YEAR*yinmill+calendar.MONTH*moinmill+calendar.DAY_OF_MONTH*dinmill+calendar.HOUR_OF_DAY*hinmill+calendar.MINUTE*miinmill;

                Intent myIntent = new Intent(NotificationActivity.this, MyReceiver.class);
                myIntent.putExtra("key",str1);
                //myrec.onReceive(NotificationActivity.this,myIntent);

                Log.e("time",calendar.getTimeInMillis()+"  "+time+" "+System.currentTimeMillis());

                pendingIntent = PendingIntent.getBroadcast(NotificationActivity.this, 0, myIntent,0);

                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                Log.e("App","Calendar: " + calendar.getTime().toString());
                try{
                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    Log.e("App","Alarm set");
                }catch(Exception e)
                {
                    e.printStackTrace();
                    FileLog.e(e);
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        str1=adapterView.getItemAtPosition(position).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    }

