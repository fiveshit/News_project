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
    private final int MAX = 2;
    List<ItemBean> Politics_list = new ArrayList<>();
    function_interface[] Politics_function = new function_interface[MAX];

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
    }
    public String Get_Politics_Ver(){
        String Politics_Ver = "0.0.1";
        return Politics_Ver;
    }
    public String Get_Politics_Test(){
        String Politics_Ver_Test = "Politics";
        return Politics_Ver_Test;
    }
    /*-----------------------------------------*/
    /*---Set function in the Politics list -----*/
    /*-----------------------------------------*/
    public boolean Set_Politics_function(int key_code)
    {
        Log.d(TAG,"key_code :" + key_code);
        return true;
    }

}
