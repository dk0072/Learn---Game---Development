package com.nogravity.learnit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.Objects;

public class physicsActivity extends AppCompatActivity implements RecycleViewAdapter.OnRecycleViewClick {

    String[] physicsTitle;
    int[] images = {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.six,R.drawable.seven};
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Physics");


        recyclerView = findViewById(R.id.physicsRecycleview);
        physicsTitle = getResources().getStringArray(R.array.physics);

        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(this,physicsTitle,images,this);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void OnRecycleClick(int position) {

        Intent intent = new Intent(physicsActivity.this,ShowPhysics.class);
        intent.putExtra("position",""+position);
        startActivity(intent);
    }
}