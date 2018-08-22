package com.firewire.manumax.mathtrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;



public class MainActivity extends AppCompatActivity {

    Button btnGo;
    Button button0;
    Button button1;
    Button button2;
    Button button3;

    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;

    ArrayList<Integer> answers=new ArrayList<Integer>();

    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuetions=0;

    public void generateQuestion(){
        Random rand=new Random();
        int a=rand.nextInt(16);
        int b=rand.nextInt(16);
        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));

        locationOfCorrectAnswer=rand.nextInt(4);

        answers.clear();//for clear the arraylist for add new elements related new question

        int incorrectAnswer;

        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }else{

                incorrectAnswer=rand.nextInt(31);
                while(incorrectAnswer==(a+b)){
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
        //btnGo.setVisible()
        btnGo.setVisibility(View.INVISIBLE);
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

        resultTextView=(TextView)findViewById(R.id.resultTextView);

        pointsTextView=(TextView)findViewById(R.id.pointsTextView);



        generateQuestion();


    }



}
