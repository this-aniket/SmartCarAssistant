package com.smartcarassistant;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MoodyMusicPlayerActivity extends AppCompatActivity {
    ImageView btspeak;
    TextView tvresult;
    ArrayList<String> result;
    String strres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moody_music_player);
        btspeak=(ImageView) findViewById(R.id.butspeak);
        tvresult=(TextView)findViewById(R.id.textView);
        btspeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==(R.id.butspeak))
                {

                    PromptInputSpeech();
                }
            }
        });
    }
    public void PromptInputSpeech()
    {
        Intent i=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say Something..");

        try
        {
            startActivityForResult(i,100);
        }
        catch(ActivityNotFoundException e)
        {
            Toast.makeText(MoodyMusicPlayerActivity.this,"CHECK",Toast.LENGTH_LONG).show();
        }

    }
    public void onActivityResult(int requestcode,int resultcode,Intent intent)
    {
        super.onActivityResult(requestcode,resultcode,intent);
        switch (requestcode)
        {
            case 100:if(resultcode ==RESULT_OK && intent!=null)
            {
                result=intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                tvresult.setText(result.get(0));

                Toast.makeText(MoodyMusicPlayerActivity.this,result.get(0),Toast.LENGTH_SHORT).show();
                strres=result.get(0);
                Intent i=new Intent(MoodyMusicPlayerActivity.this,Tunes.class);
                i.putExtra("key",strres);
                startActivity(i);
            }
                break;
        }


    }
}
