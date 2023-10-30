package com.ayzish.gulfjobs.smartfarmer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ayzish.gulfjobs.smartfarmer.R;

public class SignUpActivity extends AppCompatActivity {
    EditText username, email, password;
    String user, emailS, pass;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = findViewById(R.id.Signup_username);
        email = findViewById(R.id.Signup_email);
        password = findViewById(R.id.Signup_password);
        Context context = getApplicationContext();
        preferences = context.getSharedPreferences("GoodDb", Context.MODE_PRIVATE);
        editor = preferences.edit();

    }


    public void signUpHere(View view) {
        user = username.getText().toString();
        emailS = email.getText().toString();
        pass = password.getText().toString();
        if (username.getText().toString().trim().equalsIgnoreCase("")){
            username.setError("Please fill this field..");
            return;
        }

        if (password.getText().toString().trim().equalsIgnoreCase("")){
            password.setError("Please fill this field..");
            return;
        }

        editor.putString("username", user);
        editor.putString("email", emailS);
        editor.putString("password", pass);

        editor.apply();

        Toast.makeText(this, "Registered Successfully Please Sign in", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignUpActivity.this, LoginAtivity.class);
        startActivity(intent);
    }
}