package com.example.java;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public class MyClass {
    public static void main(String[] args){
        System.out.println("Hello Studio");
        float dollar, euro, won;
        try{
            String url = "http://www.usd-cny.com/bankofchina.htm";
            Document doc = Jsoup.connect(url).get();
            System.out.println("run: "+ doc.title());
            Elements tables = doc.getElementsByTag("table");
            Element table0 = tables.get(0);
            // 获取 TD 中的数据
            Elements tds = table0.getElementsByTag("td");
            for(int i=0; i<tds.size(); i+=6){
                Element td1 = tds.get(i);
                Element td2 = tds.get(i + 5);
                String str1 = td1.text();
                String val = td2.text();
                //System.out.println("run: " + str1 + "==>" + val);
                float v = 100f / Float.parseFloat(val);
                // 获取数据并返回
                if(toutf8(str1).equals("美元")){
                    dollar = v;
                    System.out.println("run: " + str1 + "==>" + val);
                    System.out.println("dollar_rate = " + dollar);
                }else if(toutf8(str1).equals("欧元")){
                    euro = v;
                    System.out.println("run: " + str1 + "==>" + val);
                    System.out.println("euro_rate = " + euro);
                }else if(toutf8(str1).equals("韩元")){
                    won = v;
                    System.out.println("run: " + str1 + "==>" + val);
                    System.out.println("won_rate = " + won);
                }else{
                    System.out.println("Failed!");
                }
            }

        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String toutf8(String str) {
        String result = null;
        try {
            result = new String(str.toString().getBytes("UTF-8"), "gbk");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}