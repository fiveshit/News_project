package com.factory.inx_fac;

import android.content.Context;
import android.util.Log;

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
    private final int MAX_AQ_LIST = 2;
    List<ItemBean> Internationality_list = new ArrayList<>();
    function_interface[] Internationality_function = new function_interface[MAX_AQ_LIST];

    public Internationality_list(List<ItemBean> list, Context context)
    {
        Internationality_list = list;
        Internationality_function = new function_interface []{
                new function_interface (){ public boolean function(int key_code){return Set_Internationality_function(key_code);} },
                new function_interface (){ public boolean function(int key_code){return Set_Internationality_function(key_code);}},
        };

    }
    /*-----------------------------------------*/
    /*------function in the internationality list ------*/
    /*-----------------------------------------*/
    public void internationality_list()
    {
        Internationality_list.add(new ItemBean(Get_Internationality_Ver()));
        Internationality_list.add(new ItemBean(Get_Internationality_Test()));
    }
    public String Get_Internationality_Ver(){
        String Internationality_Ver = "0.0.1";
        return Internationality_Ver;
    }
    public String Get_Internationality_Test(){
        String Internationality_Ver_Test = "Internationality";
        return Internationality_Ver_Test;
    }
    /*-----------------------------------------*/
    /*---Set function in the internationality list -----*/
    /*-----------------------------------------*/
    public boolean Set_Internationality_function(int key_code)
    {
        Log.d(TAG,"key_code :" + key_code);
        return true;
    }

}
