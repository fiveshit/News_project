package com.factory.news;

import android.content.Context;
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
    private final int MAX_FINANCE_LIST = 2;
    List<ItemBean> Finance_list = new ArrayList<>();
    function_interface[] finance_function = new function_interface[MAX_FINANCE_LIST];
    public Finance_list(List<ItemBean> list, Context context)
    {

        Finance_list = list;
        finance_function = new function_interface []{
                new function_interface (){ public boolean function(int key_code){return Set_Finance_function(key_code);} },
                new function_interface (){ public boolean function(int key_code){return Set_Finance_function(key_code);}},
        };

    }
    /*-----------------------------------------*/
    /*------function in the Finance list ------*/
    /*-----------------------------------------*/
    public void finance_list()
    {
        Finance_list.add(new ItemBean(Get_Finance_Ver()));
        Finance_list.add(new ItemBean(Get_Finance_Test()));
    }
    public String Get_Finance_Ver(){
        String Finance_Ver = "0.0.1";
        return Finance_Ver;
    }
    public String Get_Finance_Test(){
        String Finance_Test = "Finance";
        return Finance_Test;
    }
    /*-----------------------------------------*/
    /*---Set function in the Finance list -----*/
    /*-----------------------------------------*/
    public boolean Set_Finance_function(int key_code)
    {
        new Thread(new Capture_news_info()).start();
        Log.d(TAG,"key_code :" + key_code);
        return true;
    }
}
