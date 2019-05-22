package com.smartcarassistant;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.github.mikephil.charting.charts.PieChart;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class MonthlyExpensesActivity extends AppCompatActivity {
    FirebaseDatabase firebasedatabase;
    DatabaseReference databaseReference;

    private Firebase mref;
    EditText etPetrol,etWashing,etTyre,etParking;
    Button btDisplay,btPreviousExpenses,btAnalysis;
    PieChart pieChart;
    TextView tvResult,tvPrevResult;
    int petrol,washing,tyre,parking;
    int total=0;
    Calendar c=Calendar.getInstance();
    int Day=c.get(Calendar.DAY_OF_MONTH);
    int Month=c.get(Calendar.MONTH)+1;
    int Month1=Month-1;
    int Month2=Month-2;
    int Year=c.get(Calendar.YEAR);
    private static  String TAG="MonthlyExpensesActivity";
    //private float[] yData = {25.3f, 10.6f, 66.76f, 44.32f, 46.01f, 16.89f, 23.9f};
    public int[] yData ;
    private String[] xData={"Petrol","Tyre","Washing","Parking"};
    String date=Day+"-"+Month+"-"+Year;
    String date1=Day+"-"+Month1+"-"+Year;
    //String date2=Day+"-"+Month2+"-"+Year;
    String id="Monthly Expenses"+date;
    String id2="Monthly Expenses "+"18-1-2018";
    String id3="Monthly Expenses "+"18-2-2018";
    String id4="Monthly Expenses "+"18-3-2018";
    String idprev="Monthly Expenses "+"30-3-2018";
    int pt,pk,w,t,val1,val2,val3;
    String cemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        cemail=intent.getStringExtra("key");
        
        Firebase.setAndroidContext(this);
        mref=new Firebase("https://smartcarassistant-6fa5b.firebaseio.com/");

        setContentView(R.layout.activity_monthly_expenses);
        etPetrol=(EditText)findViewById(R.id.editTextPetrol);
        etWashing=(EditText)findViewById(R.id.editTextWashing);
        etTyre=(EditText)findViewById(R.id.editTextTyre);
        etParking=(EditText)findViewById(R.id.editTextParking);
        tvResult=(TextView)findViewById(R.id.textviewresult);
        btDisplay=(Button)findViewById(R.id.buttonDisplay);
        btPreviousExpenses=(Button)findViewById(R.id.buttonPreviousExpenses);
        btAnalysis=(Button)findViewById(R.id.buttonAnalysis);
        tvPrevResult=(TextView)findViewById(R.id.textviewprevresult);

        databaseReference = FirebaseDatabase.getInstance().getReference("Monthly_Expenses");
        //ref=FirebaseDatabase.getInstance().getReference();
        //ref1=ref.child("smartcarassistant-6fa5b").child("Monthly_Expenses").child(id2).child("previous expenses");
        Log.d(TAG,"onCreate:starting to create chart");
        pieChart=(PieChart)findViewById(R.id.piechartanalysis);
        btDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                petrol = Integer.parseInt(etPetrol.getText().toString());
                parking = Integer.parseInt(etParking.getText().toString());
                tyre = Integer.parseInt(etTyre.getText().toString());
                washing = Integer.parseInt(etWashing.getText().toString());
                total = total+petrol + parking + tyre + washing;



                Myarray();
                tvResult.setText(Integer.toString(total));

            }
        });
                btPreviousExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.child(idprev).child("total").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value=dataSnapshot.getValue(String.class);
                        tvPrevResult.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
       // pieChart.setDescription("Sales by employee (In Thousands $) ");
        pieChart.setRotationEnabled(true);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Analysis");
        pieChart.setCenterTextSize(10);
        //pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!
        btAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(id2).child("total").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value1=dataSnapshot.getValue(String.class);
                        val1=Integer.parseInt(value1);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                databaseReference.child(id3).child("total").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value2=dataSnapshot.getValue(String.class);
                        val2=Integer.parseInt(value2);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                databaseReference.child(id4).child("total").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value3=dataSnapshot.getValue(String.class);
                         val3=Integer.parseInt(value3);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                yData= new int[]{val1, val2, val3};
                addDataSet();
            }
        });
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: Value select from chart.");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());

                int pos1 = e.toString().indexOf("(sum): ");
                String sales = e.toString().substring(pos1 + 7);

                for(int i = 0; i < yData.length; i++){
                    if(yData[i] == Integer.parseInt(sales)){
                        pos1 = i;
                        break;
                    }
                }
                String employee = xData[pos1 + 1];
                Toast.makeText(MonthlyExpensesActivity.this, "Employee " + employee + "\n" + "Sales: $" + sales + "K", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });



    }

    private void addDataSet() {
        Log.d(TAG,"addDataset started");

        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Analyze your expenses");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(7);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        //colors.add(Color.CYAN);
       // colors.add(Color.YELLOW);
       // colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }

    private void  Myarray()
    {
        String Petrol=etPetrol.getText().toString().trim();
        String Parking=etParking.getText().toString().trim();
        String Tyre=etTyre.getText().toString().trim();
        String Washing=etWashing.getText().toString().trim();
        String Total=Integer.toString(total);

        if (TextUtils.isEmpty(Petrol)){
            Toast.makeText(this,"Please Enter Petrol Cost",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(Parking)){
            Toast.makeText(this,"Please Enter Parking Cost",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(Washing)){
            Toast.makeText(this,"Please Enter Washing Cost",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(Tyre)){
            Toast.makeText(this,"Please Enter Tyre Cost",Toast.LENGTH_LONG).show();
        }else{

            Monthly_Expenses monthly_expenses=new Monthly_Expenses(Petrol,Washing,Tyre,Parking,Total);

            databaseReference.child(id).child("petrol").setValue(Petrol.toString());
            databaseReference.child(id).child("washing").setValue(Washing.toString());
            databaseReference.child(id).child("tyre").setValue(Tyre.toString());
            databaseReference.child(id).child("parking").setValue(Parking.toString());
            databaseReference.child(id).child("total").setValue(Total.toString());




            //databaseReference.child("value").setValue(val);


            Toast.makeText(this,"Data added..",Toast.LENGTH_LONG).show();

            ClearText();
        }
    }
    private void ClearText(){
        etPetrol.setText("");
        etWashing.setText("");
        etTyre.setText("");
        etParking.setText("");
        tvResult.setText("");

    }
    }
