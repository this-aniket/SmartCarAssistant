package com.smartcarassistant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    FirebaseDatabase firebasedatabase;
    DatabaseReference databaseReference;
    EditText etName, etEmail,etMobNo,etCarNo,etDOP,etSUsername,etFavSeason,etCity,etState;
    Button btSubmit;
    EditText etPwd,etCfPwd;
    private Firebase mref;
    private FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Firebase.setAndroidContext(this);
        mref=new Firebase("https://smartcarassistant-6fa5b.firebaseio.com/");

        mauth=FirebaseAuth.getInstance();

        etName = (EditText) findViewById(R.id.editTextName);
        etEmail = (EditText) findViewById(R.id.editTextEmail);
        etMobNo = (EditText) findViewById(R.id.editTextMobNo);
        etCarNo = (EditText) findViewById(R.id.editTextCarNo);
        etDOP = (EditText) findViewById(R.id.editTextDOP);
        etSUsername = (EditText) findViewById(R.id.editTextSUsername);
        etPwd = (EditText) findViewById(R.id.editTextPwd);
        etCfPwd = (EditText) findViewById(R.id.editTextCfPwd);
        etFavSeason = (EditText) findViewById(R.id.editTextFavSeason);
        etCity = (EditText) findViewById(R.id.editTextCity);
        etState = (EditText) findViewById(R.id.editTextState);
        btSubmit = (Button) findViewById(R.id.buttonSubmit);
        databaseReference = FirebaseDatabase.getInstance().getReference("Car_user");

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");

                MyArray();
            }
        });
    }


        private void MyArray(){
            String name=etName.getText().toString().trim();
            String email=etEmail.getText().toString().trim();
            String mobileno=etMobNo.getText().toString().trim();
            String carno=etCarNo.getText().toString().trim();
            String dop=etDOP.getText().toString().trim();
            String username=etSUsername.getText().toString().trim();
            String password=etPwd.getText().toString().trim();
            String confirmpassword=etCfPwd.getText().toString().trim();
            String favseason=etFavSeason.getText().toString().trim();
            String city=etCity.getText().toString().trim();
            String state=etState.getText().toString().trim();
            if (TextUtils.isEmpty(name)){
                Toast.makeText(this,"Please enter name",Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(email)){
                Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(mobileno)){
                Toast.makeText(this,"Please enter mobile number",Toast.LENGTH_LONG).show();
            }else if(mobileno.length()!=10){
                Toast.makeText(this,"Please enter correct mobile number",Toast.LENGTH_LONG).show();
            }
            else if(TextUtils.isEmpty(carno)){
                Toast.makeText(this,"Please enter car number",Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(dop)){
                Toast.makeText(this,"Please enter date of purchase",Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(username)){
                Toast.makeText(this,"Please enter username",Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(password)){
                Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(confirmpassword)){
                Toast.makeText(this,"Please confirm password",Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(favseason)){
                Toast.makeText(this,"Please enter Favourite Season",Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(city)){
                Toast.makeText(this,"Please enter city",Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(state)){
                Toast.makeText(this,"Please enter state",Toast.LENGTH_LONG).show();
            }else if(!password.equals(confirmpassword))
            {
                Toast.makeText(this,"Check Password",Toast.LENGTH_LONG).show();
            }
            else{
                String id1=username;
                Car_user car_user=new Car_user( name, email, mobileno,  carno,  dop,  username,  password,  confirmpassword);
                databaseReference.child(id1).child("name").setValue(name.toString());
                databaseReference.child(id1).child("email").setValue(email.toString());
                databaseReference.child(id1).child("mobileno").setValue(mobileno.toString());
                databaseReference.child(id1).child("carno").setValue(carno.toString());
                databaseReference.child(id1).child("dop").setValue(dop.toString());
                databaseReference.child(id1).child("username").setValue(username.toString());
                databaseReference.child(id1).child("password").setValue(password.toString());
                databaseReference.child(id1).child("confirmpassword").setValue(confirmpassword.toString());
                databaseReference.child(id1).child("favseason").setValue(favseason.toString());
                databaseReference.child(id1).child("city").setValue(city.toString());
                databaseReference.child(id1).child("state").setValue(state.toString());
                Register();

                Toast.makeText(this,"User added..",Toast.LENGTH_LONG).show();
                ClearText();
            }
    }

    private void Register() {
        String email=etEmail.getText().toString().trim();
        String password=etPwd.getText().toString().trim();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(SignUpActivity.this,"Empty",Toast.LENGTH_LONG).show();
            return;//Stops the funnc from executing further
        }
        mauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(SignUpActivity.this, "Registration failed", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void ClearText(){
        etName.setText("");
        etEmail.setText("");
        etMobNo.setText("");
        etCarNo.setText("");
        etDOP.setText("");
        etSUsername.setText("");
        etPwd.setText("");
        etCfPwd.setText("");
        etFavSeason.setText("");
        etCity.setText("");
        etState.setText("");
    }
    }



