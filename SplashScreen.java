package com.nogravity.learnit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    FirebaseAuth m_auth;
    int startCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Objects.requireNonNull(getSupportActionBar()).hide();
        m_auth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(SplashScreen.this);

                if(m_auth.getCurrentUser()!=null ||account!=null)
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                else
                    startActivity(new Intent(SplashScreen.this,Authentication.class));
            }
        },2000);
    }



    @Override
    protected void onStart() {
        super.onStart();
        startCounter++;
        if(startCounter==2)
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    if(m_auth.getCurrentUser()!=null)
                        startActivity(new Intent(SplashScreen.this,MainActivity.class));
                    else
                        startActivity(new Intent(SplashScreen.this,Authentication.class));
                }
            },2000);
        }
    }
}