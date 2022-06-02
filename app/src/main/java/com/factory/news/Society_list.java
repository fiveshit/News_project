package com.factory.news;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.chaquo.python.PyObject;

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
    private String TAG = "Society_UI";
    private final String url = "https://udn.com/news/breaknews/1/2#breaknews";
    private final int MAX_AQ_LIST = 2;
    List<ItemBean> Society_list = new ArrayList<>();
    Context context;
    ArrayList<Integer> search_result = new ArrayList<Integer>();
    function_interface[] Society_function = new function_interface[MAX_AQ_LIST];
    private static String[] result;
    private static String[] link;
    private int size = 0;
    public Society_list(List<ItemBean> list, Context context)
    {
        Society_list = list;
        this.context = context;
        Society_function = new function_interface []{
               // new function_interface (){ public boolean function(int key_code){return Set_Society_search_function();} },
                new function_interface (){ public boolean function(int key_code){return Set_Society_function(search_result);}},
        };

    }
    /*-----------------------------------------*/
    /*------function in the Society list ------*/
    /*-----------------------------------------*/
    public void society_list(ArrayList<Integer> item)
    {
        Society_list.add(new ItemBean(Get_Society_Test()));
        /*---------------------------*/
        /*------dynamic update ------*/
        /*---------------------------*/
        if(!item.isEmpty())
        {
            for(int i = 0;i < item.size() ; i++)
            {
                Log.d(TAG,"arr :"+item.get(i));
                Society_list.add(new ItemBean(result[item.get(i)]));
            }
        }else {
            for (int i = 0; i < size; i++) {
                Society_list.add(new ItemBean(result[i]));
            }
        }
    }
    public String Get_Society_Test(){
        String Society_Ver_Test = "Refresh";
        return Society_Ver_Test;
    }
    /*----------------------------------------------*/
    /*-------get function in the Society list ------*/
    /*----------------------------------------------*/
    public String[] Get_Society_link_info()
    {
        return link;
    }
    /*-----------------------------------------*/
    /*---Set function in the Society list -----*/
    /*-----------------------------------------*/
    public boolean Set_Society_search_function(ArrayList<Integer> search_link)
    {
        for(int i = 0;i < search_link.size() ; i++)
        {
            //Log.d(TAG,"search_link :"+search_link.get(i).toInt());
            String tmp  = link[search_link.get(i)];
            link[i] = tmp;
            //Log.d(TAG,"link  :"+link[i]);

        }
        //Toast.makeText(this.context,"Not Support !!!!",Toast.LENGTH_LONG).show();
        return true;
    }
    public boolean Set_Society_function(ArrayList<Integer> item)
    {
        Capture_news_info task4 = new Capture_news_info(url);
        Thread t4 = new Thread(task4);//.start()
        try {
            t4.start();
            t4.join();
        }catch (Exception e){
            System.out.println(e);
        }
        result = task4.getCallback();
        size = task4.getCallback_size();
        if(item.isEmpty()) {
            link = task4.getCallback_link();
        }
        return true;
    }

}
