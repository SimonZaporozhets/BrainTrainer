package ru.startandroid.develop.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnPlay;
    RelativeLayout layoutGame;
    Random random;
    Button option0;
    Button option1;
    Button option2;
    Button option3;
    Button playAgain;
    TextView tvSume;
    TextView tvScore;
    TextView tvTimer;
    int correctAnswer;
    TextView tvResult;
    ArrayList<Integer> listOptions;
    CountDownTimer myTimer;
    int score = 0;
    int totalAnswers = 0;

    public void playAgain(View v) {
        generateOptions();
        playAgain.setVisibility(View.INVISIBLE);
        tvResult.setText("");
        option0.setEnabled(true);
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        score = 0;
        totalAnswers = 0;
        tvTimer.setText("30s");
        myTimer.start();
    }
    public void startGame(View v) {
        generateOptions();
        myTimer.start();
        btnPlay.setVisibility(View.INVISIBLE);
        layoutGame.setVisibility(View.VISIBLE);
    }

    public void generateOptions() {
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        int sume = a + b;
        tvSume.setText(a + " + " + b);


        correctAnswer = random.nextInt(4);
        int wrongAnswer;

        listOptions.clear();
        for (int i = 0; i <= 3; i++) {
            if (i == correctAnswer) {
                listOptions.add(sume);
            } else {
                wrongAnswer = random.nextInt(31);
                while (wrongAnswer == sume) {
                    wrongAnswer = random.nextInt(31);
                }
                listOptions.add(wrongAnswer);
            }
        }

        option0.setText(Integer.toString(listOptions.get(0)));
        option1.setText(Integer.toString(listOptions.get(1)));
        option2.setText(Integer.toString(listOptions.get(2)));
        option3.setText(Integer.toString(listOptions.get(3)));
    }

    public void chooseAnswer(View v) {
        if (v.getTag().equals(Integer.toString(correctAnswer))) {
            tvResult.setText("Correct!");
            score++;
        } else {
            tvResult.setText("Wrong!");
        }

        totalAnswers++;
        tvScore.setText(score + "/" + totalAnswers);


        generateOptions();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (Button) findViewById(R.id.btnPlay);
        layoutGame = (RelativeLayout) findViewById(R.id.layoutGame);
        option0 = (Button) findViewById(R.id.button0);
        option1 = (Button) findViewById(R.id.button1);
        option2 = (Button) findViewById(R.id.button2);
        option3 = (Button) findViewById(R.id.button3);
        tvSume = (TextView) findViewById(R.id.tvSume);
        playAgain = (Button) findViewById(R.id.playAgain);
        tvResult = (TextView) findViewById(R.id.tvResult);
        tvTimer = (TextView) findViewById(R.id.tvTimer);
        tvScore = (TextView) findViewById(R.id.tvScore);
        listOptions = new ArrayList<Integer>();
        random = new Random();

        myTimer = new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                tvResult.setText("Your Score: " + score + "/" + totalAnswers);
                playAgain.setVisibility(View.VISIBLE);
                tvScore.setText("0/0");
                option0.setEnabled(false);
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
            }
        };

    }
}