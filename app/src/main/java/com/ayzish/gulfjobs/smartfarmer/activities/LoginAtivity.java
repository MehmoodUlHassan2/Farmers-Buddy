package com.ayzish.gulfjobs.smartfarmer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ayzish.gulfjobs.smartfarmer.HomePage;
import com.ayzish.gulfjobs.smartfarmer.R;

public class LoginAtivity extends AppCompatActivity {
    EditText username, password;
    String user,pass,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ativity);

        SharedPreferences preferences = getSharedPreferences("GoodDb", 0);

        username = findViewById(R.id.Signin_username);
        password = findViewById(R.id.Signin_password);
        user = preferences.getString("username","");
        email = preferences.getString("email","");
        pass = preferences.getString("password","");

    }

    public void gotoSignup(View view) {
        Intent intent = new Intent(LoginAtivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void guestUser(View view) {
        Intent intent = new Intent(LoginAtivity.this, HomePage.class);
        startActivity(intent);
    }

    public void signInHere(View view) {
        if (username.getText().toString().trim().equalsIgnoreCase("")){
            username.setError("Please fill this field..");
            return;
        }

        if (password.getText().toString().trim().equalsIgnoreCase("")){
            password.setError("Please fill this field..");
            return;
        }

        if (user.equals(username.getText().toString()) || email.equals(username.getText().toString()) && pass.equals(password.getText().toString())){
            Toast.makeText(this, "Sign in Successfully...", Toast.LENGTH_SHORT).show();
            SharedPreferences preferences = getSharedPreferences("SAVEDB", 0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username",user);
            editor.apply();
            Intent intent = new Intent(LoginAtivity.this, HomePage.class);
            intent.putExtra("username",user);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Please register first to signin...", Toast.LENGTH_SHORT).show();
        }
    }
}