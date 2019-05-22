package com.smartcarassistant;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class TravelPatActivity extends AppCompatActivity {
Button btIn,btOut;
String MEDIA_PATH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_pat);
        Button btIn=(Button)findViewById(R.id.buttonInFile);
        Button btOut=(Button)findViewById(R.id.buttonOutFile);
        btIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String new1="input";
                //String abc="/"+"sdcard"+"/"";
                //MEDIA_PATH = new String(abc);
                //File home = new File(MEDIA_PATH);
               File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"input");
                Uri path = Uri.fromFile(file);
                Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
                pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                pdfOpenintent.setDataAndType(path, "application/pdf");
                Intent intent=Intent.createChooser(pdfOpenintent,"Open File");
                try {
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e) {

                }
            }
        });
        btOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"output");
                Uri path = Uri.fromFile(file);
                Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
                pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                pdfOpenintent.setDataAndType(path, "application/pdf");
                Intent intent=Intent.createChooser(pdfOpenintent,"Open File");
                try {
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e) {

                }
            }
        });
    }
}
