package com.factory.news;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

//---------------------------------------//
// Name : Society class
// Description : Main UI level 2 from Society next
// Input : List<ItemBean> , Context context
// Output : -
// Return : -
// ---------------------------------------//
public class Society_list {
    public String TAG = "Society_UI";
    private final int MAX_AQ_LIST = 2;
    List<ItemBean> Society_list = new ArrayList<>();
    function_interface[] Society_function = new function_interface[MAX_AQ_LIST];

    public Society_list(List<ItemBean> list, Context context)
    {
        Society_list = list;
        Society_function = new function_interface []{
                new function_interface (){ public boolean function(int key_code){return Set_Society_function(key_code);} },
                new function_interface (){ public boolean function(int key_code){return Set_Society_function(key_code);}},
        };

    }
    /*-----------------------------------------*/
    /*------function in the Society list ------*/
    /*-----------------------------------------*/
    public void society_list()
    {
        Society_list.add(new ItemBean(Get_Society_Ver()));
        Society_list.add(new ItemBean(Get_Society_Test()));
    }
    public String Get_Society_Ver(){
        String Society_Ver = "0.0.1";
        return Society_Ver;
    }
    public String Get_Society_Test(){
        String Society_Ver_Test = "Society";
        return Society_Ver_Test;
    }
    /*-----------------------------------------*/
    /*---Set function in the Society list -----*/
    /*-----------------------------------------*/
    public boolean Set_Society_function(int key_code)
    {
        Log.d(TAG,"key_code :" + key_code);
        return true;
    }

}
