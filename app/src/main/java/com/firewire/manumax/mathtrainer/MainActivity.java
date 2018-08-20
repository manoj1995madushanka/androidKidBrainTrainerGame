package com.firewire.manumax.mathtrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;



public class MainActivity extends AppCompatActivity {

    Button btnGo;
    ArrayList<Integer> answers;
    int locationOfCorrectAnswer;

    public void start(View view){
        btnGo.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGo=(Button)findViewById(R.id.btnGo);
        TextView sumTextView=(TextView)findViewById(R.id.sumTextView);

        Random rand=new Random();
        int a=rand.nextInt(16);
        int b=rand.nextInt(16);

        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));
        locationOfCorrectAnswer=rand.nextInt(4);

        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }else{
                int incorrectAnswer=rand.nextInt(31);

                //now we need while loop for check is incorrect answer equal to correct answer if true we generate another random number
                while(incorrectAnswer==a+b){
                    incorrectAnswer=rand.nextInt(31);
                }
                
            }
        }
    }
}
