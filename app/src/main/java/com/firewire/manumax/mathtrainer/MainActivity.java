package com.firewire.manumax.mathtrainer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;



public class MainActivity extends AppCompatActivity {

    Button btnGo;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button btnplayAgain;

    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView timerTextView;

    RelativeLayout game;

    ArrayList<Integer> answers=new ArrayList<Integer>();



    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuetions=0;

    public void playAudio(){

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.cat);
        mp.start();
    }

    public void playAgain(View view){
        score=0;
        numberOfQuetions=0;

        timerTextView.setText("60s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");

        btnplayAgain.setVisibility(View.INVISIBLE);

        generateQuestion();
        playAudio();

        new CountDownTimer(60200,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                btnplayAgain.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score : "+ Integer.toString(score)+" / "+Integer.toString(numberOfQuetions));
            }
        }.start();
    }

    public void generateQuestion(){

        String[] operation={"+","-"};
        int mathOp;



        Random rand=new Random();
        int a=rand.nextInt(16);
        int b=rand.nextInt(16);
        mathOp=rand.nextInt(2);
        String cal=operation[mathOp];
        sumTextView.setText(Integer.toString(a)+ " "+operation[mathOp]+" " +Integer.toString(b));

        locationOfCorrectAnswer=rand.nextInt(4);

        answers.clear();//for clear the arraylist for add new elements related new question

        int incorrectAnswer;

        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                if(cal=="+"){
                    answers.add(a+b);
                }else{
                    answers.add(a-b);
                }
            }else{

                incorrectAnswer=rand.nextInt(31);
                while((incorrectAnswer==(a+b)) || (incorrectAnswer==(a-b))){
                    incorrectAnswer=rand.nextInt(31);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){

            score++;
            resultTextView.setText("CORRECT!");

        }else{
            resultTextView.setText("WRONG!");
        }
        numberOfQuetions++;
        pointsTextView.setText(Integer.toString(score)+" / "+Integer.toString(numberOfQuetions));
        generateQuestion();
    }

    public void start(View view){

        btnGo.setVisibility(View.INVISIBLE);
        game.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.btnplayAgain));

        playAudio();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGo=(Button) findViewById(R.id.btnGo);
        sumTextView=(TextView)findViewById(R.id.sumTextView);
        button0=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        btnplayAgain=(Button)findViewById(R.id.btnplayAgain);
        game=(RelativeLayout)findViewById(R.id.game);

        timerTextView=(TextView)findViewById(R.id.timerTextView);

        resultTextView=(TextView)findViewById(R.id.resultTextView);

        pointsTextView=(TextView)findViewById(R.id.pointsTextView);

        //playAudio();



    }



}
