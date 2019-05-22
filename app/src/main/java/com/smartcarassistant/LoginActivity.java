package com.smartcarassistant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    String email;
    private FirebaseAuth mauth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);
        final EditText etEmail=(EditText)findViewById(R.id.editTextEmail);
        final EditText etPassword=(EditText)findViewById(R.id.editTextPassword);
        Button btLogin=(Button)findViewById(R.id.buttonLogin);
        Button btSignUp=(Button)findViewById(R.id.buttonSignUp);
        mauth=FirebaseAuth.getInstance();
        //SIGNUP
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        //LOGIN
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email=etEmail.getText().toString();
                String password=etPassword.getText().toString();
                mauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_LONG).show();
                            Intent intent1=new Intent(LoginActivity.this,AppListActivity.class);
                            Intent intentmail=new Intent(LoginActivity.this,MonthlyExpensesActivity.class);
                            intentmail.putExtra("key",email);
                            startActivity(intentmail);
                            startActivity(intent1);
                            etEmail.setText(" ");
                            etPassword.setText(" ");

                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Login Failed!!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }



}

