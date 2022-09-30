package com.nogravity.learnit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import java.util.Objects;

public class ShowPhysics extends AppCompatActivity {

    WebView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Physics");

        setContentView(R.layout.activity_show_physics);

        view = findViewById(R.id.physicsWebview);

        Intent intent = getIntent();

        String pos = intent.getStringExtra("position");

        switch (pos) {
            case "0":
                view.loadUrl("file:///android_asset/basicP.html");
                break;
            case "1":
                view.loadUrl("file:///android_asset/p1.html");
                break;
            case "2":
                view.loadUrl("file:///android_asset/p2.html");
                break;
            case "3":
                view.loadUrl("file:///android_asset/p4.html");
                break;
            case "4":
                view.loadUrl("file:///android_asset/p5.html");
                break;
            case "5":
                view.loadUrl("file:///android_asset/p6.html");
                break;
            case "6":
                view.loadUrl("file:///android_asset/p7.html");
                break;
        }
    }
}