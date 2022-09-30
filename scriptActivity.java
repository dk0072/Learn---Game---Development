package com.nogravity.learnit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class scriptActivity extends AppCompatActivity implements RecycleViewAdapter.OnRecycleViewClick {

    String[] scriptingTitle;
    int[] images =
            {
                R.drawable.one,R.drawable.two,R.drawable.three,
                    R.drawable.four,R.drawable.five,R.drawable.six,
                    R.drawable.seven,R.drawable.eight
            };
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_script);

        recyclerView = findViewById(R.id.scriptRecycleview);
        scriptingTitle = getResources().getStringArray(R.array.script);

        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(this,scriptingTitle,images,this);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void OnRecycleClick(int position) {
        Intent intent = new Intent(this,ShowScript.class);
        intent.putExtra("position",""+position);
        startActivity(intent);
    }
}