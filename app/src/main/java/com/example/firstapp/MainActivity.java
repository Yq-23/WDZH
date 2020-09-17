package com.example.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    //private static final String TAG = "MainActivity";
    TextView out;
    //EditText inp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        //获取控件 out
        TextView outobj = findViewById(R.id.out);
        outobj.setText("Hello");
        Log.i(TAG, "onCreate:...... ");
        EditText inp = findViewById(R.id.inp);
        //String text = inp.getText().toString();
        //Button btn = findViewById(R.id.btn);*/
         out = (TextView)findViewById(R.id.out);
         //inp = (EditText)findViewById(R.id.inp);
    }
    /*public void btn(View v){
        String str = inp.getText().toString();
        Double a = Double.parseDouble(str);
        Double b = a*1.8 + 32.0;
        String str1 = String.valueOf(b);
        out.setText("结果是：" + str1);
    }*/
    public void btn1(View v){
        String str = out.getText().toString();
        int a = Integer.parseInt(str);
        int b = a + 3;
        String str1 = String.valueOf(b);
        out.setText(str1);
    }
    public void btn2(View v){
        String str = out.getText().toString();
        int a = Integer.parseInt(str);
        int b = a + 2;
        String str1 = String.valueOf(b);
        out.setText(str1);
    }
    public void btn3(View v){
        String str = out.getText().toString();
        int a = Integer.parseInt(str);
        int b = a + 1;
        String str1 = String.valueOf(b);
        out.setText(str1);
    }
    public void btn4(View v){
        int a = 0;
        String str1 = String.valueOf(a);
        out.setText(str1);
    }
}