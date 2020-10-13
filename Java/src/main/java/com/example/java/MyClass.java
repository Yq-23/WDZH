package com.example.java;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Thread.sleep;

public class MyClass{
    public static void main(String[] args){
        System.out.println("Hello Studio");
        float dollar, euro, won;

        //开启子线程
        //Thread t = new Thread();
        //t.start();

        MyThread mt1 = new MyThread() ;    // 实例化对象
        mt1.start() ;   // 调用线程主体
    }

    static class MyThread extends Thread{  // 继承Thread类，作为线程的实现类
        public void run() {
            while (true) {
                try {
                    String url = "http://www.usd-cny.com/bankofchina.htm";
                    Document doc = Jsoup.connect(url).get();
                    Elements tables = doc.getElementsByTag("table");
                    Element table0 = tables.get(0);
                    // 获取 TD 中的数据
                    Elements tds = table0.getElementsByTag("td");
                    List<String> list2 = new ArrayList<String>();
                    for (int i = 0; i < tds.size(); i += 6) {
                        Element td1 = tds.get(i);
                        Element td2 = tds.get(i + 5);
                        String str1 = td1.text();
                        String val = td2.text();
                        //Log.i(TAG, "run: " + str1 + "==>" + val);
                        String s = (String) (str1 + "==>" + val);
                        list2.add(s);
                        float v = 100f / Float.parseFloat(val);
                        float rate = (float) (Math.round(v * 100)) / 100;
                     }
                    System.out.println(list2);

                    /*
                    Calendar c = Calendar.getInstance();//获得系统当前日期
                    int year = c.get(Calendar.YEAR);
                    int moth = c.get(Calendar.MONTH) + 1;//系统日期从0开始算起
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    int hour = c.get(Calendar.HOUR);//小时
                    int minute = c.get(Calendar.MINUTE);//分
                    int second = c.get(Calendar.SECOND);//秒

                    System.out.println(year + " year " + moth + " month " + day + " day " + hour + " hour " + minute + " min " + second + " s ");
                    */
                    Thread.sleep(2000);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /*public static String toutf8(String str) {
        String result = null;
        try {
            result = new String(str.toString().getBytes("UTF-8"), "gbk");
        } catch (UnsupportedEncodingException e) {
            //
            e.printStackTrace();
        }
        return result;
    }*/
}