package com.factory.news;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

//---------------------------------------//
// Name : Politics class
// Description : Main UI level 2 from Politics next
// Input : List<ItemBean> , Context context
// Output : -
// Return : -
// ---------------------------------------//
public class Politics_list {
    public String TAG = "Politics_UI";
    private final String url = "https://udn.com/news/breaknews/1/1#breaknews";
    private final int MAX = 2;
    List<ItemBean> Politics_list = new ArrayList<>();
    function_interface[] Politics_function = new function_interface[MAX];
    private static String[] result;
    public int size = 0;
    public Politics_list(List<ItemBean> list, Context context)
    {
        Politics_list = list;
        Politics_function = new function_interface []{
                new function_interface (){ public boolean function(int key_code){return Set_Politics_function(key_code);} },
                new function_interface (){ public boolean function(int key_code){return Set_Politics_function(key_code);}},
        };

    }
    /*-----------------------------------------*/
    /*------function in the Politics list ------*/
    /*-----------------------------------------*/
    public void politics_list()
    {
        Politics_list.add(new ItemBean(Get_Politics_Ver()));
        Politics_list.add(new ItemBean(Get_Politics_Test()));

        /*---------------------------*/
        /*------dynamic update ------*/
        /*---------------------------*/
        for(int i =0;i<size;i++) {
            Politics_list.add(new ItemBean(result[i]));
        }
    }
    public String Get_Politics_Ver(){
        String Politics_Ver = "0.0.1";
        return Politics_Ver;
    }
    public String Get_Politics_Test(){
        String Politics_Ver_Test = "Refresh";
        return Politics_Ver_Test;
    }
    /*-----------------------------------------*/
    /*---Set function in the Politics list -----*/
    /*-----------------------------------------*/
    public boolean Set_Politics_function(int key_code)
    {
        Capture_news_info task3 = new Capture_news_info();
        task3.Set_url(url);
        Thread t3 = new Thread(task3);//.start()
        try {
            t3.start();
            t3.join();
        }catch (Exception e){
            System.out.println(e);
        }
        result = task3.getCallback();
        size = task3.getCallback_size();
        Log.d(TAG,"size :" + size);
        return true;
    }

}
