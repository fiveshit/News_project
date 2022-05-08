package com.factory.news;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//---------------------------------------//
// Name : Internationality class
// Description : Main UI level 2 from Internationality next
// Input : List<ItemBean> , Context context
// Output : -
// Return : -
// ---------------------------------------//
public class Internationality_list {
    public String TAG = "Internationality_UI";
    private final String url = "https://udn.com/news/breaknews/1/5#breaknews";
    private final int MAX_AQ_LIST = 2;
    private static String[] result;
    private static String[] link;
    private int size = 0;
    List<ItemBean> Internationality_list = new ArrayList<>();
    Context context;
    function_interface[] Internationality_function = new function_interface[MAX_AQ_LIST];

    public Internationality_list(List<ItemBean> list, Context context)
    {
        Internationality_list = list;
        this.context = context;
        Internationality_function = new function_interface []{
                new function_interface (){ public boolean function(int key_code){return Set_Internationality_search_function();} },
                new function_interface (){ public boolean function(int key_code){return Set_Internationality_function();}},
        };

    }
    /*--------------------------------------------------*/
    /*------function in the internationality list ------*/
    /*--------------------------------------------------*/
    public void internationality_list()
    {
        Internationality_list.add(new ItemBean(Get_Internationality_Ver()));
        Internationality_list.add(new ItemBean(Get_Internationality_Test()));
        /*---------------------------*/
        /*------dynamic update ------*/
        /*---------------------------*/
        for(int i =0;i<size;i++) {
            Internationality_list.add(new ItemBean(result[i]));
        }

    }
    public String Get_Internationality_Ver(){
        String Internationality_Ver = "Search";
        return Internationality_Ver;
    }
    public String Get_Internationality_Test(){
        String Internationality_Ver_Test = "Refresh";
        return Internationality_Ver_Test;
    }
    /*------------------------------------------------------*/
    /*-------get function in the internationality list -----*/
    /*------------------------------------------------------*/
    public String[] Get_internationality_link_info()
    {
        return link;
    }
    /*-----------------------------------------*/
    /*---Set function in the internationality list -----*/
    /*-----------------------------------------*/
    public boolean Set_Internationality_search_function()
    {
        Toast.makeText(this.context,"Not Support !!!!",Toast.LENGTH_LONG).show();
        return true;
    }
    public boolean Set_Internationality_function()
    {
        Capture_news_info task2 = new Capture_news_info(url);
        Thread t2 = new Thread(task2);//.start()
        try {
            t2.start();
            t2.join();
        }catch (Exception e){
            System.out.println(e);
        }
        result = task2.getCallback();
        size = task2.getCallback_size();
        link = task2.getCallback_link();
        return true;
    }

}
