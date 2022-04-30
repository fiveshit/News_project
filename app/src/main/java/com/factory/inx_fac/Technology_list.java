package com.factory.inx_fac;

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
    private final int MAX = 2;
    List<ItemBean> Technology_list = new ArrayList<>();
    function_interface[] Technology_function = new function_interface[MAX];

    public Technology_list(List<ItemBean> list, Context context)
    {
        Technology_list = list;
        Technology_function = new function_interface []{
                new function_interface (){ public boolean function(int key_code){return Set_Technology_function(key_code);} },
                new function_interface (){ public boolean function(int key_code){return Set_Technology_function(key_code);}},
        };

    }
    /*-----------------------------------------*/
    /*------function in the Technology list ------*/
    /*-----------------------------------------*/
    public void technology_list()
    {
        Technology_list.add(new ItemBean(Get_Technology_Ver()));
        Technology_list.add(new ItemBean(Get_Technology_Test()));
    }
    public String Get_Technology_Ver(){
        String Technology_Ver = "0.0.1";
        return Technology_Ver;
    }
    public String Get_Technology_Test(){
        String Technology_Ver_Test = "Technology";
        return Technology_Ver_Test;
    }
    /*-----------------------------------------*/
    /*---Set function in the Technology list -----*/
    /*-----------------------------------------*/
    public boolean Set_Technology_function(int key_code)
    {
        Log.d(TAG,"key_code :" + key_code);
        return true;
    }

}
