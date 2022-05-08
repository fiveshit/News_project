package com.factory.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

//---------------------------------------//
// Name : Finance class
// Description : Main UI level 2 from Finance next
// Input : List<ItemBean> , Context context
// Output : -
// Return : -
// ---------------------------------------//
public class Finance_list {
    public String TAG = "Finance_UI";
    private final String url = "https://udn.com/news/breaknews/1/6#breaknews";
    private final int MAX_FINANCE_LIST = 2;
    List<ItemBean> Finance_list = new ArrayList<>();
    MainActivity mainActivity = new MainActivity();
    private static String[] result;
    private static String[] link;
    private int size = 0;
    function_interface[] finance_function;// = new function_interface[MAX_FINANCE_LIST];
    public Finance_list(List<ItemBean> list ,Context context)
    {
        result = new String[size];
        Finance_list = list;
        finance_function = new function_interface []{
            new function_interface (){ public boolean function(int key_code){return Set_Finance_function();} },
            new function_interface (){ public boolean function(int key_code){return Set_Finance_function();}},
        };


        //for(int i = 2;i < size;i++)
        //{
        //    Log.d(TAG,"AAAAAAA:"+i);
        //    finance_function[i] = new function_interface(){public  boolean function(int key_code){return Set_Link_function(key_code);}};
        //}

    }
    /*-----------------------------------------*/
    /*------function in the Finance list ------*/
    /*-----------------------------------------*/
    public void finance_list()
    {
        Finance_list.add(new ItemBean(Get_Finance_Ver()));
        Finance_list.add(new ItemBean(Get_Finance_Test()));
        /*---------------------------*/
        /*------dynamic update ------*/
        /*---------------------------*/
        for(int i =0;i<size;i++) {
            Finance_list.add(new ItemBean(result[i]));
        }

    }
    public String Get_Finance_Ver(){
        String Finance_Ver = "0.0.1";
        return Finance_Ver;
    }
    public String Get_Finance_Test(){
        String Finance_Test = "Refresh";
        return Finance_Test;
    }
    /*---------------------------------------------*/
    /*-------get function in the Finance list -----*/
    /*---------------------------------------------*/
    public String[] Get_Finance_link_info()
    {
        return link;
    }
    /*-----------------------------------------*/
    /*---Set function in the Finance list -----*/
    /*-----------------------------------------*/
    public boolean Set_Finance_function()
    {
        Capture_news_info task1 = new Capture_news_info(url);
        Thread t1 = new Thread(task1);//.start()
        try {
            t1.start();
            t1.join();
        }catch (Exception e){
            System.out.println(e);
        }
        result = task1.getCallback();
        link = task1.getCallback_link();
        size = task1.getCallback_size();
        return true;
    }
    public boolean Set_Link_function(int index)
    {
        Uri uri = Uri.parse(link[index]);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        mainActivity.startActivity(intent);
        return true;
    }
}
