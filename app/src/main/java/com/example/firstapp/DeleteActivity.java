package com.example.firstapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DeleteActivity extends AppCompatActivity implements Runnable{

    private static final String TAG = "DeleteActivity";
    ListView listdelete;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Intent intent = getIntent();
        listdelete = (ListView)findViewById(R.id.listdelete);
        //开启子线程
        Thread t = new Thread(this);
        t.start();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 5) {
                    ArrayList<HashMap<String, String>> listItems = (ArrayList<HashMap<String, String>>) msg.obj;
                    MyAdapter myAdapter = new MyAdapter(DeleteActivity.this, R.layout.list_item,listItems);
                    listdelete.setAdapter(myAdapter);
                    //listdelete.setOnItemClickListener(DeleteActivity.this);//添加事件监听
                    //String str = (String) msg.obj;
                    //Log.i(TAG, "handleMessage:getMessage meg = " + str);
                }
                super.handleMessage(msg);
            }
        };
    }

    @Override
    public void run() {
        Log.i(TAG,"run:run()......");
        Message msg = handler.obtainMessage(5);
        try{
            String url = "http://www.usd-cny.com/bankofchina.htm";
            Document doc = null;
            doc = Jsoup.connect(url).get();
            Log.i(TAG, "run: "+ doc.title());
            Elements tables = doc.getElementsByTag("table");
            Element table0 = tables.get(0);
            // 获取 TD 中的数据
            Elements tds = table0.getElementsByTag("td");
            //List<String> list2 = new ArrayList<String>();
            ArrayList<HashMap<String, String>> list2 = new ArrayList<HashMap<String, String>>();
            for(int i=0; i<tds.size(); i+=6){
                Element td1 = tds.get(i);
                Element td2 = tds.get(i + 5);
                String str1 = td1.text();
                String val = td2.text();
                Log.i(TAG, "run: " + str1 + "==>" + val);
                HashMap<String, String> map = new HashMap<String, String>();
                float v = 100f / Float.parseFloat(val);
                float rate =(float)(Math.round(v*100))/100;
                map.put("ItemTitle", str1);
                map.put("ItemDetail", String.valueOf(rate));
                list2.add(map);
            }
            msg.obj = list2;
            handler.sendMessage(msg);

        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}