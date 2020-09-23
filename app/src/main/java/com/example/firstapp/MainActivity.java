package com.example.firstapp;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";
    TextView out;
    EditText inp;
    double dollarRate = 0.1471;
    double euroRate = 0.1251;
    double wonRate = 171.3427;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        out = (TextView)findViewById(R.id.out);
        inp = (EditText)findViewById(R.id.inp);

    }

    public void reversion(View btn){
        String str = inp.getText().toString();
        if(str==null || str.equals("") || str.equals(R.string.hint)){
            //no input
            Toast.makeText(MainActivity.this, "请输入金额", Toast.LENGTH_SHORT).show();
        }
        else{
            double a = Double.parseDouble(str);
            if(btn.getId()==R.id.btn_dollar){
                double b = a*dollarRate;
                String str1 = String.format("%.4f", b).toString();
                out.setText(str1+" dollar");
            }
            else if(btn.getId()==R.id.btn_euro){
                double b = a*euroRate;
                String str1 = String.format("%.4f", b).toString();
                out.setText(str1+" euro");
            }
            else if(btn.getId()==R.id.btn_won){
                double b = a*wonRate;
                String str1 = String.format("%.4f", b).toString();
                out.setText(str1+" won");
            }
        }
    }

    public void open(View V){
        Intent second = new Intent(this,MainActivity2.class);

        //使用Extra传递参数
        /*second.putExtra("dollar_rate_key",dollarRate);
        second.putExtra("euro_rate_key",euroRate);
        second.putExtra("won_rate_key",wonRate);*/

        //使用Bundle传递参数
        Bundle bdl = new Bundle();
        bdl.putDouble("dollar_rate_key",dollarRate);
        bdl.putDouble("euro_rate_key",euroRate);
        bdl.putDouble("won_rate_key",wonRate);
        second.putExtras(bdl);

        Log.i(TAG,"onCreate:dollarRate=" + dollarRate);
        Log.i(TAG,"onCreate:euroRate=" + euroRate);
        Log.i(TAG,"onCreate:wonRate=" + wonRate);

        startActivityForResult(second,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==1 && resultCode==1){
            Bundle bundle = data.getExtras();
            dollarRate = bundle.getDouble("key_dollar",0.1f);
            euroRate = bundle.getDouble("key_euro",0.1f);
            wonRate = bundle.getDouble("key_won",0.1f);

            /*dollarRate = data.getDoubleExtra("key_dollar",0.1f);
            euroRate = data.getDoubleExtra("key_euro",0.1f);
            wonRate = data.getDoubleExtra("key_won",0.1f);*/

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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu1){

        }
        return super.onOptionsItemSelected(item);
    }
}
