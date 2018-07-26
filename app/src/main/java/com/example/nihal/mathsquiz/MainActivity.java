package com.example.nihal.mathsquiz;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button button;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView resultTextView;
    TextView pointsTextView;
    TextView timerTextView;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestion = 0;
    Button playAgainButoon;
    RelativeLayout gameRelativeLayout;

    public void playAgain(final View view ){

        score =0;
        numberOfQuestion =0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButoon.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30100,1000){


            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {


                playAgainButoon.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your score :" + Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
            }
        }.start();

    }

    public void generateQuestion() {

        Random rand = new Random();
        int a = rand.nextInt(11);
        int b = rand.nextInt(11);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        answer.clear();

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answer.add(a + b);
            } else {
                incorrectAnswer = rand.nextInt(21);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(21);
                }
                answer.add(incorrectAnswer);
            }
        }
        button.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));

    }

    public void chooseAnswer(View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            score++;
            resultTextView.setText("Correct");
        } else {
            resultTextView.setText("Wrong");
        }
        numberOfQuestion++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
        generateQuestion();
    }

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        sumTextView = findViewById(R.id.sumTextView);
        button = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButoon = findViewById(R.id.playAgainButton);

        gameRelativeLayout=findViewById(R.id.gameRelativeLayout);




    }
}
