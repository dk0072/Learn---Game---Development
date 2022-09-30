package com.nogravity.learnit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class quizActivity extends AppCompatActivity {

    String[] questions;
    int selectedAnswer;
    int realAnswer;
    int questionID = 1;
    int score = 0;
    CardView Qcanvas,Fcanvas;
    TextView finalScore,finalRating;
    TextView question;
    TextView scoreText;
    TextView scoreUI;

    Button optionA,optionB,optionC,optionD,next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Quiz");

        findView();
        getSelectedOption();
        resetButtons();

        questions = new String[]
                {
                        "Q1: Which one of following is Physics Component ?",
                        "Q2: Why we use static keyword ?",
                        "Q3: What is Canvas ?",
                        "Q4: What is CineMachine ?",
                        "Q5: What is Photon ?"
                };

        question.setText(questions[0]);

        setOption("RigidBody","Canvas","Scene","TextMesh");
        realAnswer = 1;

        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                questionID++;
                switch(questionID)
                {
                    case 2 : question.setText(questions[1]);
                             realAnswer = 3;
                        setOption("server connect","player move","Memory Optimize","provide UI");

                        resetButtons();
                        break;
                    case 3 : question.setText(questions[2]);
                             realAnswer = 1;
                        setOption("UI component","Physics","Memory Optimize","Server");
                        resetButtons();
                        break;
                    case 4 : question.setText(questions[3]);
                        realAnswer = 4;
                        resetButtons();
                        setOption("Collider Tool","Physics Tool","Memory Optimize Tool","Camera Tool");
                        break;
                    case 5 : question.setText(questions[4]);
                        realAnswer = 3;
                        resetButtons();
                        setOption("client engine","Unity's Main Class","Server","Camera Tool");
                        break;
                    case 6:
                        finalScoreUI();
                        break;
                }
            }
        });

    }

    void findView(){

        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        question = findViewById(R.id.question);
        scoreText = findViewById(R.id.score);
        scoreUI = findViewById(R.id.scoreText);
        Qcanvas = findViewById(R.id.questionCanvas);
        Fcanvas = findViewById(R.id.scoreCanvas);
        finalRating = findViewById(R.id.finalRating);
        finalScore = findViewById(R.id.finalScore);
        next = findViewById(R.id.next);

    }

    void getSelectedOption(){

        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = 1;
                checkAnswer();
            }
        });

        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = 2;
                checkAnswer();

            }
        });

        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = 3;
                checkAnswer();

            }
        });

        optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = 4;
                checkAnswer();

            }
        });
    }

    void checkAnswer(){
        if(realAnswer == 1)
            optionA.setBackgroundColor(getResources().getColor(R.color.green));
        if(realAnswer == 2)
            optionB.setBackgroundColor(getResources().getColor(R.color.green));
        if(realAnswer == 3)
            optionC.setBackgroundColor(getResources().getColor(R.color.green));
        if(realAnswer == 4)
            optionD.setBackgroundColor(getResources().getColor(R.color.green));

        if(selectedAnswer == realAnswer)
            AddScore();
    }

    void resetButtons(){
        optionA.setBackgroundColor(getResources().getColor(R.color.blue));
        optionB.setBackgroundColor(getResources().getColor(R.color.blue));
        optionC.setBackgroundColor(getResources().getColor(R.color.blue));
        optionD.setBackgroundColor(getResources().getColor(R.color.blue));
    }

    void AddScore(){
        score++;
        scoreText.setText(score+"");
    }

    void setOption(String text1,String text2,String text3,String text4){
        optionA.setText(text1);
        optionB.setText(text2);
        optionC.setText(text3);
        optionD.setText(text4);
    }

    void finalScoreUI(){
        String score = scoreText.getText().toString();
        scoreText.setVisibility(View.INVISIBLE);
        scoreUI.setVisibility(View.INVISIBLE);
        Qcanvas.setVisibility(View.VISIBLE);

        Fcanvas.setVisibility(View.VISIBLE);
        finalScore.setText("SCORE : " + score);

        int scoreData = Integer.parseInt(score);
        String performance = scoreData <= 2 ? "Need Improvement" : "Excellent";
        finalRating.setText("PERFORMANCE : "+performance);
    }

}