package com.example.firstapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    EditText txt_dollar,txt_euro,txt_won;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();

        //使用extra获得数据
        /*double dollar2 = intent.getDoubleExtra("dollar_rate_key",0.0f);
        double euro2 = intent.getDoubleExtra("euro_rate_key",0.0f);
        double won2 = intent.getDoubleExtra("won_rate_key",0.0f);*/

        //使用bundle获得数据
        Bundle bundle = intent.getExtras();
        double dollar2 = bundle.getDouble("dollar_rate_key",0.1f);
        double euro2 = bundle.getDouble("euro_rate_key",0.1f);
        double won2 = bundle.getDouble("won_rate_key",0.1f);

        Log.i(TAG,"onCreate:dollar2=" + dollar2);
        Log.i(TAG,"onCreate:euro2=" + euro2);
        Log.i(TAG,"onCreate:won2=" + won2);

        txt_dollar = (EditText)findViewById(R.id.txt_dollar);
        txt_euro = (EditText)findViewById(R.id.txt_euro);
        txt_won = (EditText)findViewById(R.id.txt_won);

        txt_dollar.setText(String.valueOf(dollar2));
        txt_euro.setText(String.valueOf(euro2));
        txt_won.setText(String.valueOf(won2));
    }

    public void btn_save(View btn){
        if(btn.getId()==R.id.btn_save){
            Intent intent_save = getIntent();
            Bundle bdl = new Bundle();
            double newDollar = Double.parseDouble(txt_dollar.getText().toString());
            double newEuro = Double.parseDouble(txt_euro.getText().toString());
            double newWon = Double.parseDouble(txt_won.getText().toString());

            bdl.putDouble("key_dollar",newDollar);
            bdl.putDouble("key_euro",newEuro);
            bdl.putDouble("key_won",newWon);
            intent_save.putExtras(bdl);

            //设置resultCode及带回的数据
            setResult(1,intent_save);
            //返回到调用页面
            finish();
        }
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