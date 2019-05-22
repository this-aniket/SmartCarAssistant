package com.smartcarassistant;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HelpDeskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_desk);

        ImageView btHealthCare = (ImageView) findViewById(R.id.buttonHealthCare);
        ImageView btSalesPurchase = (ImageView) findViewById(R.id.buttonSalesPurchase);
        ImageView btCarServicing = (ImageView) findViewById(R.id.buttonCarServicing);
        ImageView btEmergencyCall = (ImageView) findViewById(R.id.buttonEmergencyCall);


        btHealthCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HelpDeskActivity.this,HealthLinkActivity.class);
                startActivity(i);
            }
        });
        btSalesPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HelpDeskActivity.this,SalesPurchaseActivity.class);
                startActivity(i);
            }
        });
        btCarServicing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HelpDeskActivity.this,CarServicingActivity.class);
                startActivity(i);
            }
        });
        btEmergencyCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9822907191"));
                startActivity(intent);
            }
        });

    }
}
