package com.factory.news;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Stock_list {
    public String TAG = "Stock_UI";
    private final String url = "https://udn.com/news/breaknews/1/11#breaknews";
    private final int MAX_STOCK_LIST = 2;
    List<ItemBean> Stock_list = new ArrayList<>();
    Context context;
    private static String[] result;
    private static String[] link;
    private int size = 0;
    function_interface[] stock_function = new function_interface[MAX_STOCK_LIST];
    public Stock_list(List<ItemBean> list, Context context)
    {
        result = new String[size];
        Stock_list = list;
        this.context = context;
        stock_function = new function_interface []{
                new function_interface (){ public boolean function(int key_code){return Set_Stock_search_function();} },
                new function_interface (){ public boolean function(int key_code){return Set_Stock_function();}},
        };

    }
    /*-----------------------------------------*/
    /*------function in the Finance list ------*/
    /*-----------------------------------------*/
    public void stock_list()
    {
        Stock_list.add(new ItemBean(Get_Stock_Ver()));
        Stock_list.add(new ItemBean(Get_Stock_Test()));
        /*---------------------------*/
        /*------dynamic update ------*/
        /*---------------------------*/
        for(int i =0;i<size;i++) {
            Stock_list.add(new ItemBean(result[i]));
        }

    }
    public String Get_Stock_Ver(){
        String Stock_Ver = "Search";
        return Stock_Ver;
    }
    public String Get_Stock_Test(){
        String Stock_Test = "Refresh";
        return Stock_Test;
    }
    /*--------------------------------------------*/
    /*-------get function in the Stock list ------*/
    /*--------------------------------------------*/
    public String[] Get_Stock_link_info()
    {
        return link;
    }

    /*-----------------------------------------*/
    /*---Set function in the Finance list -----*/
    /*-----------------------------------------*/
    public boolean Set_Stock_search_function()
    {
        Toast.makeText(this.context,"Not Support !!!!",Toast.LENGTH_LONG).show();
        return true;
    }
    public boolean Set_Stock_function()
    {
        Capture_news_info task6 = new Capture_news_info(url);
        //task6.Set_url(url);
        Thread t6 = new Thread(task6);//.start()
        try {
            t6.start();
            t6.join();
        }catch (Exception e){
            System.out.println(e);
        }
        result = task6.getCallback();
        size = task6.getCallback_size();
        link = task6.getCallback_link();
        return true;
    }
}
