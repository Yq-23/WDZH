package com.example.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity2 extends AppCompatActivity implements Runnable{

    private static final String TAG = "MainActivity2";
    EditText txt_dollar,txt_euro,txt_won;
    Handler handler;

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
        float dollar2 = bundle.getFloat("dollar_rate_key",0.1f);
        float euro2 = bundle.getFloat("euro_rate_key",0.1f);
        float won2 = bundle.getFloat("won_rate_key",0.1f);

        Log.i(TAG,"onCreate:dollar2=" + dollar2);
        Log.i(TAG,"onCreate:euro2=" + euro2);
        Log.i(TAG,"onCreate:won2=" + won2);

        txt_dollar = (EditText)findViewById(R.id.txt_dollar);
        txt_euro = (EditText)findViewById(R.id.txt_euro);
        txt_won = (EditText)findViewById(R.id.txt_won);

        txt_dollar.setText(String.valueOf(dollar2));
        txt_euro.setText(String.valueOf(euro2));
        txt_won.setText(String.valueOf(won2));

        //开启子线程
        Thread t = new Thread(this);
        t.start();

        //线程间消息同步
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 5) {
                    String str = (String) msg.obj;
                    Log.i(TAG, "handleMessage:getMessage meg = " + str);
                }
                super.handleMessage(msg);
            }
        };
    }

    public void btn_save(View btn){
        if(btn.getId()==R.id.btn_save){
            Intent intent_save = getIntent();
            Bundle bdl = new Bundle();
            float newDollar = Float.parseFloat(txt_dollar.getText().toString());
            float newEuro = Float.parseFloat(txt_euro.getText().toString());
            float newWon = Float.parseFloat(txt_won.getText().toString());

            SharedPreferences sp = getSharedPreferences("Myrate", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putFloat("dollar_rate",newDollar);
            editor.putFloat("euro_rate",newEuro);
            editor.putFloat("won_rate",newWon);
            editor.apply();

            bdl.putFloat("dollar_rate_key",newDollar);
            bdl.putFloat("euro_rate_key",newEuro);
            bdl.putFloat("won_rate_key",newWon);
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

    @Override
    public void run() {
        Log.i(TAG,"run:run()......");

        URL url = null;
        try{
            url = new URL("https://www.swufe.edu.cn");
            HttpsURLConnection http = (HttpsURLConnection)url.openConnection();
            InputStream in = http.getInputStream();
            String html = inputStream2String(in);
            Log.i(TAG, "run:html=" + html);
            Message msg = handler.obtainMessage(5);
            msg.obj = html;
            handler.sendMessage(msg);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*//获取msg对象，用于返回主线程
        Message msg = handler.obtainMessage(5);
        //msg.what = 5;
        msg.obj = "Hello from run()";
        handler.sendMessage(msg);*/

    }

    //输入流转字符串
    private String inputStream2String(InputStream inputStream) throws IOException{
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, "utf-8");
        while(true){
            int rsz = in.read(buffer, 0, buffer.length);
            if(rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }
}