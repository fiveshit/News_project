package com.factory.news;
import android.content.Intent;
import android.icu.util.Output;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;


public class Capture_news_info extends Thread{
    private final static String url = "https://udn.com/news/breaknews/1/6#breaknews";
    /**
     *
     * param
     * describe url get news data
     * create: Joe
     * return: java.lang.String
     **/

    @Override
    public void run() {
        String TAG = "news_info";
        String result = "";
        HttpURLConnection connection;

        try {
            URL news_url = new URL(url);
            connection = (HttpURLConnection)news_url.openConnection();
            connection.setRequestMethod("GET");
            //connection.setRequestProperty("authentication",mainActivity.Authentication);
            //connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            //connection.setRequestProperty("Accept-Charset", "UTF-8");
            //connection.setDoOutput(true);
            connection.setDoInput(true);
            //connection.connect();
            //BufferedReader in = new BufferedReader(
            //new InputStreamReader(connection.getInputStream()));
            //OutputStream writer = new BufferedOutputStream(connection.getOutputStream());
            InputStream inputStream = connection.getInputStream();

            int status = connection.getResponseCode();
            Log.d(TAG, String.valueOf(status));
            if(inputStream != null){
               InputStreamReader reader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader in = new BufferedReader(reader);

                String line="";
                while ((line = in.readLine()) != null) {
                    result += (line+"\n");
                    //result += (line.indexOf("title")+"\n");
               }
            } else{
                result = "Did not work!";
            }
            System.out.println(result);

        }catch (Exception  ex){
            System.out.println(ex);
        }

    }

}
