package com.example.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";
    TextView out;
    EditText inp;
    float dollarRate = (float) 0.14;
    float euroRate = (float)0.22;
    float wonRate = (float)171.34;
    //RateManager rateManager = new RateManager(this);
    //RateItem rateItem = new RateItem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        out = (TextView)findViewById(R.id.outdetail);
        inp = (EditText)findViewById(R.id.inp);

        SharedPreferences sharedPreferences = getSharedPreferences("Myrate", Activity.MODE_PRIVATE);
        dollarRate = sharedPreferences.getFloat("dollar_rate",0.1f);
        euroRate = sharedPreferences.getFloat("euro_rate",0.1f);
        wonRate = sharedPreferences.getFloat("won_rate",0.1f);

    }

    public void reversion(View btn){
        String str = inp.getText().toString();
        if(str==null || str.equals("") || str.equals(R.string.hint)){
            //no input
            Toast.makeText(MainActivity.this, "请输入金额", Toast.LENGTH_SHORT).show();
        }
        else{
            float a = Float.parseFloat(str);
            if(btn.getId()==R.id.btn_dollar){
                //rateItem = rateManager.findById(1);
                //dollarRate = rateItem.getCurrate();
                float b = a*dollarRate;
                String str1 = String.format("%.4f", b).toString();
                out.setText(str1+" dollar");
            }
            else if(btn.getId()==R.id.btn_euro){
                float b = a*euroRate;
                String str1 = String.format("%.4f", b).toString();
                out.setText(str1+" euro");
            }
            else if(btn.getId()==R.id.btn_won){
                float b = a*wonRate;
                String str1 = String.format("%.4f", b).toString();
                out.setText(str1+" won");
            }
        }
    }

    public void open(View btn){
        if(btn.getId()==R.id.btn_open){

            Intent second = new Intent(this,MainActivity2.class);

            //使用Extra传递参数
        /*;second.putExtra("dollar_rate_key",dollarRate);
        second.putExtra("euro_rate_key",euroRate);
        second.putExtra("won_rate_key",wonRate)*/

            //使用Bundle传递参数
            Bundle bdl = new Bundle();
            bdl.putFloat("dollar_rate_key",dollarRate);
            bdl.putFloat("euro_rate_key",euroRate);
            bdl.putFloat("won_rate_key",wonRate);
            second.putExtras(bdl);

            Log.i(TAG,"onCreate:dollarRate=" + dollarRate);
            Log.i(TAG,"onCreate:euroRate=" + euroRate);
            Log.i(TAG,"onCreate:wonRate=" + wonRate);

            startActivityForResult(second,1);
        }
        else if(btn.getId()==R.id.btn_open1){
            Intent list = new Intent(this,List_Activity.class);
            startActivityForResult(list,2);
        }else if(btn.getId()==R.id.btn_delete){
            Intent listdelete = new Intent(this,DeleteActivity.class);
            startActivityForResult(listdelete,3);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==1 && resultCode==1){
            Bundle bundle = data.getExtras();
            dollarRate = bundle.getFloat("dollar_rate_key",0.1f);
            euroRate = bundle.getFloat("euro_rate_key",0.1f);
            wonRate = bundle.getFloat("won_rate_key",0.1f);

            Log.i(TAG,"onActivityResult: dollarRate=" + dollarRate);
            Log.i(TAG,"onActivityResult: euroRate=" + euroRate);
            Log.i(TAG,"onActivityResult: wonRate=" + wonRate);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.first_menu,menu);
        return true;
    }

    @Override
    //事件处理代码
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu1){

        }
        return super.onOptionsItemSelected(item);
    }
}
