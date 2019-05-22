package com.smartcarassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AppListActivity extends AppCompatActivity {
    private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_app_list);
            ImageView btNotification = (ImageView) findViewById(R.id.buttonNotification);
            ImageView btHelpdesk = (ImageView) findViewById(R.id.buttonHelpDesk);
            ImageView btMonthlyExpenses = (ImageView) findViewById(R.id.buttonMonthlyExpenses);
            ImageView btMoodyMediaPlayer = (ImageView) findViewById(R.id.buttonMoodyMusicPlayer);
            ImageView btExploreAroundme = (ImageView) findViewById(R.id.buttonExploreAroundme);
            ImageView btEmergencyTrapped = (ImageView)findViewById(R.id.buttonEmergencyTrapped);
            ImageView btDetect=(ImageView)findViewById(R.id.buttondetect);
            ImageView btTravelpat=(ImageView)findViewById(R.id.buttontravelpat);
            Button btLogout=(Button)findViewById(R.id.buttonLogout);
            btNotification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent4 = new Intent(AppListActivity.this, NotificationActivity.class);
                    startActivity(intent4);
                }
            });
            btMonthlyExpenses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent3 = new Intent(AppListActivity.this, MonthlyExpensesActivity.class);
                    startActivity(intent3);
                }
            });
            btHelpdesk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent5=new Intent(AppListActivity.this, HelpDeskActivity.class);
                    startActivity(intent5);
                }
            });
            btMoodyMediaPlayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent6=new Intent(AppListActivity.this,MoodyMusicPlayerActivity.class);
                    startActivity(intent6);
                }
            });
            btExploreAroundme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent7 = new Intent(AppListActivity.this, ExploreAroundme.class);
                    startActivity(intent7);
                }
            });
            btEmergencyTrapped.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent8 = new Intent(AppListActivity.this, EmergencyTrapped.class);
                    startActivity(intent8);
                }
            });
            btDetect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent9=new Intent(AppListActivity.this,SplashScreen.class);
                    startActivity(intent9);
                }
            });
            btTravelpat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent10=new Intent(AppListActivity.this,TravelPatActivity.class);
                    startActivity(intent10);
                }
            });
            btLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mauth.getInstance().signOut();
                    Intent intent11=new Intent(AppListActivity.this,LoginActivity.class);
                    startActivity(intent11);
                }
            });
        }catch (Exception e){
            FileLog.e(e);
        }
    }
}