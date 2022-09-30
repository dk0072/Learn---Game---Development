package com.nogravity.learnit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Objects;

public class tutorialActivity extends AppCompatActivity implements RecycleViewAdapter.OnRecycleViewClick {

    String[] tutorialNames;
    int[] images = {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.six
    ,R.drawable.seven,R.drawable.eight,R.drawable.nine,R.drawable.ten};
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Tutorials");
        recyclerView = findViewById(R.id.introRecycleview);
        tutorialNames = getResources().getStringArray(R.array.tutorial);

        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(this,tutorialNames,images,this);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }



    @Override
    public void OnRecycleClick(int position) {

        Intent intent = new Intent(this,ShowTutorial.class);
        intent.putExtra("position",""+position);
        //Toast.makeText(this,"hello" +position, Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }
}