package com.nogravity.learnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import io.reactivex.rxjava3.internal.subscriptions.BooleanSubscription;

public class MainActivity extends AppCompatActivity {

    CardView tutorial;
    CardView script;
    CardView physics;
    CardView comment;
    CardView quiz;
    CardView share;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_main);

        tutorial = findViewById(R.id.tutorial);
        script = findViewById(R.id.script);
        physics = findViewById(R.id.physic);
        comment = findViewById(R.id.comment);
        quiz = findViewById(R.id.quiz);
        share = findViewById(R.id.share);

        auth = FirebaseAuth.getInstance();

        Objects.requireNonNull(getSupportActionBar()).setTitle("LearnIt - Unity3D");

        onClicks();




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            auth.signOut();
            startActivity(new Intent(MainActivity.this, Authentication.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void onClicks(){
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,tutorialActivity.class);
                startActivity(i);
            }
        });
        script.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,scriptActivity.class);
                startActivity(i);
            }
        });
        physics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,physicsActivity.class);
                startActivity(i);
            }
        });
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,commentActivity.class);
                startActivity(i);
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,quizActivity.class);
                startActivity(i);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                String Body = "Learn Game Development Free of Cost !! - www.AppLink.com";

                i.putExtra(Intent.EXTRA_TEXT, Body);

                startActivity(Intent.createChooser(i,"Share Via"));

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }
}