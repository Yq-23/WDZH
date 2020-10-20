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
import android.widget.Toast;

import java.text.DecimalFormat;

public class List_MainActivity_1 extends AppCompatActivity {

    private static final String TAG = "List_MainActivity_1";
    TextView out,title;
    EditText in;
    DecimalFormat df;
    float currency_rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__main_1);

        Intent intent = getIntent();
        //使用Bundle传递参数时获取数据
        Bundle bdl_gain = intent.getExtras();
        String itemTitle = bdl_gain.getString("itemTitle","");
        float rate = bdl_gain.getFloat("itemDetail",0.1f);
        Log.i(TAG,"oneCreate:itemTitle = " + itemTitle);
        Log.i(TAG,"oneCreate:itemDetail = " + rate);

        title = (TextView) findViewById(R.id.nametitle);
        in = (EditText)findViewById(R.id.indetail);
        out = (TextView) findViewById(R.id.outdetail);
        df = new DecimalFormat( "0.0000");//设置double类型小数点后位数格式

        title.setText(itemTitle);
        currency_rate = rate;
    }

    public void convert(View btn){
        String str = in.getText().toString();
        if(str == null || str.equals("") || str.equals(R.string.hint)){//no input
            Toast.makeText(this, "请输入人民币金额", Toast.LENGTH_SHORT).show();
        }else{
            float f = Float.parseFloat(in.getText().toString());
            float f1 = f * currency_rate;
            String s = String.valueOf(df.format(f1));
            String str1 = in.getText().toString() + " 人民币 = " + s + " " + title.getText();
            out.setText(str1);
        }
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
        if(item.getItemId()==R.id.menu3){
            //设置功能，与设置按钮的事件一样
            Intent returnPrevious = getIntent();
            //设置resultCode及带回的数据
            setResult(3,returnPrevious);
            //返回上一个界面
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}