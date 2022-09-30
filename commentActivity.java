package com.nogravity.learnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class commentActivity extends AppCompatActivity {

    RecyclerView recycleView;
    ChatAdapter adapter;
    Button sendButton;
    EditText msg;
    FirebaseDatabase database;
    DatabaseReference reference;
    Date d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Live QNA");

        setContentView(R.layout.activity_comment);


        Binding();
        setInputSize();
        setupRecyclerView();

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onPause() {
        super.onPause();
        adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.startListening();
    }

    void setInputSize(){
        recycleView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if(bottom <oldBottom){
                    recycleView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ViewGroup.LayoutParams layoutParams = recycleView.getLayoutParams();
                            layoutParams.height = 1100;
                            recycleView.setLayoutParams(layoutParams);
                            recycleView.smoothScrollToPosition(bottom);
                        }
                    },100);
                }
                ViewGroup.LayoutParams layoutParams = recycleView.getLayoutParams();
                layoutParams.height = 1800;
                recycleView.setLayoutParams(layoutParams);
            }
        });


    }
    void Binding(){
        recycleView = findViewById(R.id.firebaseRecycleview);
        sendButton = findViewById(R.id.sendButton);
        msg = findViewById(R.id.userMessageText);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("UserChats");

        Objects.requireNonNull(getSupportActionBar()).setTitle("Live QNA");
    }
    void setupRecyclerView(){
        d = new Date();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh = getSharedPreferences("userLogs",MODE_PRIVATE);
                String username = sh.getString("username","No User Detail");
                String message = msg.getText().toString();
               


                if(!message.equals("")){

                    Calendar cal = Calendar.getInstance();
                    Date time = Calendar.getInstance().getTime();
                    String m_time = new SimpleDateFormat("hh-mm",Locale.getDefault()).format(time) + " "+ (cal.get(Calendar.AM_PM)== Calendar.PM ?"PM":"AM");
                    String date = new SimpleDateFormat("dd-MM-yyyy EEE", Locale.getDefault()).format(new Date());

                    //String time = sdf.format(d);
                    String key = reference.push().getKey();

                    assert key != null;


                    reference.child(key).child("UserName").setValue(username);
                    reference.child(key).child("Message").setValue(message);
                    reference.child(key).child("TimeStamp").setValue(""+m_time);
                    reference.child(key).child("Date").setValue(""+date);


                    msg.setText("");
                    setInputSize();

                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                    recycleView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recycleView.smoothScrollToPosition(adapter.getItemCount());
                        }
                    },100);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Message cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        recycleView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Chat> options = new FirebaseRecyclerOptions.Builder<Chat>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("UserChats"),Chat.class)
                .build();

        adapter = new ChatAdapter(options,this);
        recycleView.setAdapter(adapter);
    }
}