package com.factory.news;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Output;
import android.net.Uri;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Capture_news_info extends Thread{
    public String url;
    public String[] result;
    public int news_size;
    /**
     *
     * function getCallback_size
     * describe get size
     * create: Joe
     * return: java.lang.int
     **/
    public int getCallback_size()
    {
        return news_size;
    }
    /**
     *
     * function getCallback
     * describe get value
     * create: Joe
     * return: java.lang.String[]
     **/
    public String[] getCallback()
    {
        return result;
    }
    /**
     *
     * function Set_url
     * describe set url
     * create: Joe
     * return: void
     **/
    public void Set_url(String url_data)
    {
        url = url_data;
    }
    /**
     *
     * function run
     * describe url get news data
     * create: Joe
     * return: java.lang.String
     **/
    @Override
    public void run() {
        String TAG = "news_info";
        try {
            URL news_url = new URL(url);
            Document doc =  Jsoup.parse(news_url, 3000);
            Elements title = doc.select("div.story-list__text");
            result = new String[title.size()];
            news_size = title.size();
            for(int i=0;i<news_size;i++) {            //用FOR個別抓取選定的Tag內容
                Elements title_select = title.get(i).select("a");//.attr("title");//選擇第i個後選取所有為td的Tag
                // title_select.get(0).text();        //只抓取第 0 Tag的文字
                if (title_select.attr("title") != null) {
                    result[i] = (title_select.attr("title")+"\n");
                    Thread.sleep(10);    //避免執行緒跑太快而UI執行續顯示太慢,覆蓋掉te01~03內容所以設個延遲,也可以使用AsyncTask-異步任務
                }
            }
            /*
            connection = (HttpURLConnection)news_url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setDoInput(true);
            InputStream inputStream = connection.getInputStream();

            int status = connection.getResponseCode();
            Log.d(TAG, String.valueOf(status));
            if(inputStream != null){
               InputStreamReader reader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader in = new BufferedReader(reader);

                String line="";
                while ((line = in.readLine()) != null) {
                    result += (line+"\n");
               }
            } else{
                result = "Did not work!";
            }

             */
            //System.out.println(result);

        }catch (Exception ex){
            System.out.println(ex);
        }

    }

}
