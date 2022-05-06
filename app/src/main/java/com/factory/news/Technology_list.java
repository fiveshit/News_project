package com.factory.news;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

//---------------------------------------//
// Name : Technology class
// Description : Main UI level 2 from Technology next
// Input : List<ItemBean> , Context context
// Output : -
// Return : -
// ---------------------------------------//
public class Technology_list {
    public String TAG = "Technology_UI";
    private final String url = "https://udn.com/news/breaknews/1/13#breaknews";
    private final int MAX = 2;
    List<ItemBean> Technology_list = new ArrayList<>();
    function_interface[] Technology_function = new function_interface[MAX];
    private static String[] result;
    public int size = 0;
    public Technology_list(List<ItemBean> list, Context context)
    {
        Technology_list = list;
        Technology_function = new function_interface []{
                new function_interface (){ public boolean function(int key_code){return Set_Technology_function();} },
                new function_interface (){ public boolean function(int key_code){return Set_Technology_function();}},
        };

    }
    /*-----------------------------------------*/
    /*------function in the Technology list ------*/
    /*-----------------------------------------*/
    public void technology_list()
    {
        Technology_list.add(new ItemBean(Get_Technology_Ver()));
        Technology_list.add(new ItemBean(Get_Technology_Test()));
        /*---------------------------*/
        /*------dynamic update ------*/
        /*---------------------------*/
        for(int i =0;i<size;i++) {
            Technology_list.add(new ItemBean(result[i]));
        }
    }
    public String Get_Technology_Ver(){
        String Technology_Ver = "0.0.1";
        return Technology_Ver;
    }
    public String Get_Technology_Test(){
        String Technology_Ver_Test = "Refresh";
        return Technology_Ver_Test;
    }
    /*-----------------------------------------*/
    /*---Set function in the Technology list -----*/
    /*-----------------------------------------*/
    public boolean Set_Technology_function()
    {
        Capture_news_info task5 = new Capture_news_info();
        task5.Set_url(url);
        Thread t5 = new Thread(task5);//.start()
        try {
            t5.start();
            t5.join();
        }catch (Exception e){
            System.out.println(e);
        }
        result = task5.getCallback();
        size = task5.getCallback_size();
        return true;
    }

}
