package com.nogravity.learnit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import java.util.Objects;

public class ShowTutorial extends AppCompatActivity {
    WebView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tutorial);
        view = findViewById(R.id.introWebview);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Tutorials");


        Intent intent = getIntent();
        String pos = intent.getStringExtra("position");

        switch (pos) {
            case "0":
                view.loadUrl("file:///android_asset/t1.html");
                break;
            case "1":
                view.loadUrl("file:///android_asset/t2.html");
                break;
            case "2":
                view.loadUrl("file:///android_asset/t3.html");
                break;
            case "3":
                view.loadUrl("file:///android_asset/t4.html");
                break;
            case "4":
                view.loadUrl("file:///android_asset/t5.html");
                break;
            case "5":
                view.loadUrl("file:///android_asset/t6.html");
                break;
            case "6":
                view.loadUrl("file:///android_asset/t7.html");
                break;
            case "7":
                view.loadUrl("file:///android_asset/t8.html");
                break;
            case "8":
                view.loadUrl("file:///android_asset/t9.html");
                break;
            case "9":
                view.loadUrl("file:///android_asset/t10.html");
                break;

        }



}
}