package com.example.firstapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class List_Activity extends AppCompatActivity implements Runnable,AdapterView.OnItemClickListener {

    private static final String TAG = "List_Activity";
    ListView list;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_);
        Intent intent = getIntent();

        list = (ListView)findViewById(R.id.list);

        //开启子线程
        Thread t = new Thread(this);
        t.start();

        //线程间消息同步
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 5) {
                    ArrayList<HashMap<String, String>> listItems = (ArrayList<HashMap<String, String>>) msg.obj;
                    MyAdapter myAdapter = new MyAdapter(List_Activity.this, R.layout.list_item,listItems);
                    list.setAdapter(myAdapter);
                    list.setOnItemClickListener(List_Activity.this);//添加事件监听
                    //String str = (String) msg.obj;
                    //Log.i(TAG, "handleMessage:getMessage meg = " + str);
                }
                super.handleMessage(msg);
            }
        };
    }

    public void run() {
        Log.i(TAG,"run:run()......");
        Message msg = handler.obtainMessage(5);
        try{
            String url = "http://www.usd-cny.com/bankofchina.htm";
            Document doc = null;
            doc = Jsoup.connect(url).get();
            //Log.i(TAG, "run: "+ doc.title());
            Elements tables = doc.getElementsByTag("table");
            Element table0 = tables.get(0);
            // 获取 TD 中的数据
            Elements tds = table0.getElementsByTag("td");
            //List<String> list2 = new ArrayList<String>();
            ArrayList<HashMap<String, String>> list1 = new ArrayList<HashMap<String, String>>();
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
                list1.add(map);
            }
            msg.obj = list1;
            handler.sendMessage(msg);

        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object ip = list.getItemAtPosition(position);
        HashMap<String, String> map = (HashMap<String, String>)ip;
        String titlestr = map.get("ItemTitle");
        String detailstr = map.get("ItemDetail");
        Float curr_rate = Float.parseFloat(detailstr);
        Log.i(TAG, "onItemClick: title=" + titlestr);
        Log.i(TAG, "onItemClick: detail=" + detailstr);

        /*TextView title = (TextView)view.findViewById(R.id.itemTitle);
        TextView detail = (TextView)view.findViewById(R.id.itemDetail);

        String title2 = String.valueOf(title.getText());
        String detail2 = String.valueOf(detail.getText());

        Log.i(TAG, "onItemClick: title2=" + title2);
        Log.i(TAG, "onItemClick: detail2=" + detail2);*/

        Intent config_new = new Intent(this,List_MainActivity_1.class);
        //传递参数
        Bundle bdl = new Bundle();
        bdl.putString("itemTitle",titlestr);
        bdl.putFloat("itemDetail",curr_rate);
        config_new.putExtras(bdl);

        Log.i(TAG,"openOne:itemTitle="+titlestr);
        Log.i(TAG,"openOne:itemDetail="+curr_rate);
        //打开新页面
        startActivity(config_new);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//启用菜单项
        getMenuInflater().inflate(R.menu.first_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {//处理菜单事件，设置菜单项中每一个item的功能
        if(item.getItemId()==R.id.menu1){
            //设置功能，与设置按钮的事件一样
        }
        return super.onOptionsItemSelected(item);
    }
}
