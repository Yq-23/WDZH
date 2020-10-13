package com.example.firstapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeleteActivity extends AppCompatActivity implements Runnable,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{

    private static final String TAG = "DeleteActivity";
    ListView listdelete;
    Handler handler;
    ArrayAdapter adapter;
    AlertDialog.Builder builder;

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
                    List<String> list2 = (List<String>) msg.obj;
                    adapter = new ArrayAdapter<String>(DeleteActivity.this,
                            android.R.layout.simple_list_item_1,list2);
                    listdelete.setAdapter(adapter);
                    //listdelete.setOnItemClickListener(DeleteActivity.this);//添加事件监听
                    listdelete.setOnItemLongClickListener(DeleteActivity.this);

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
            ArrayList<String> list1 = new ArrayList<String>();
            for(int i=0; i<tds.size(); i+=6){
                Element td1 = tds.get(i);
                Element td2 = tds.get(i + 5);
                String str1 = td1.text();
                String val = td2.text();
                //Log.i(TAG, "run: " + str1 + "==>" + val);
                float v = 100f / Float.parseFloat(val);
                float rate =(float)(Math.round(v*100))/100;
                String s = (String)(str1 + "==>" + val);
                list1.add(s);
            }
            msg.obj = list1;
            handler.sendMessage(msg);

        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "onItemClick: position=" + position);
        Log.i(TAG, "onItemClick: parent=" + parent);
        adapter.remove(parent.getItemAtPosition(position));
        // adapter.notifyDataSetChanged()会自动调用
    }

    @Override
    public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("请确认是否删除当前数据")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "onItemLongClick: 对话框事件处理");
                        adapter.remove(parent.getItemAtPosition(position));
                    }
                }).setNegativeButton("否", null);
        builder.create().show();
        return false;
    }
}