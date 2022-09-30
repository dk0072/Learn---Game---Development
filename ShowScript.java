package com.nogravity.learnit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import java.util.Objects;

public class ShowScript extends AppCompatActivity {
    WebView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Scripts");
        setContentView(R.layout.activity_show_script);

        view = findViewById(R.id.scriptWebview);

        Intent intent = getIntent();

        String pos = intent.getStringExtra("position");

        switch (pos) {
            case "0":
                view.loadUrl("file:///android_asset/s1.html");
                break;
            case "1":
                view.loadUrl("file:///android_asset/s2.html");
                break;
            case "2":
                view.loadUrl("file:///android_asset/s3.html");
                break;
            case "3":
                view.loadUrl("file:///android_asset/s4.html");
                break;
            case "4":
                view.loadUrl("file:///android_asset/s5.html");
                break;
            case "5":
                view.loadUrl("file:///android_asset/s6.html");
                break;
            case "6":
                view.loadUrl("file:///android_asset/s7.html");
                break;
            case "7":
                view.loadUrl("file:///android_asset/tips.html");
                break;
        }
    }
}