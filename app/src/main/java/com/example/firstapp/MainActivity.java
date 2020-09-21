package com.example.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    TextView outA;
    TextView outB;
    //EditText inp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         outA = (TextView)findViewById(R.id.outA);
         outB = (TextView)findViewById(R.id.outB);
    }
    public void btn1(View v){
        String str = outA.getText().toString();
        int a = Integer.parseInt(str);
        int b = a + 3;
        String str1 = String.valueOf(b);
        outA.setText(str1);
    }
    public void btn2(View v){
        String str = outA.getText().toString();
        int a = Integer.parseInt(str);
        int b = a + 2;
        String str1 = String.valueOf(b);
        outA.setText(str1);
    }
    public void btn3(View v){
        String str = outA.getText().toString();
        int a = Integer.parseInt(str);
        int b = a + 1;
        String str1 = String.valueOf(b);
        outA.setText(str1);
    }
    public void btn4(View v){
        String str = outB.getText().toString();
        int a = Integer.parseInt(str);
        int b = a + 3;
        String str1 = String.valueOf(b);
        outB.setText(str1);
    }
    public void btn5(View v){
        String str = outB.getText().toString();
        int a = Integer.parseInt(str);
        int b = a + 2;
        String str1 = String.valueOf(b);
        outB.setText(str1);
    }
    public void btn6(View v){
        String str = outB.getText().toString();
        int a = Integer.parseInt(str);
        int b = a + 1;
        String str1 = String.valueOf(b);
        outB.setText(str1);
    }
    public void btn7(View v){
        int a = 0;
        String str1 = String.valueOf(a);
        outA.setText(str1);
        outB.setText(str1);
    }
}