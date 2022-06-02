package com.factory.news;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.chaquo.python.PyObject;

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
    private String TAG = "Technology_UI";
    private final String url = "https://udn.com/news/breaknews/1/13#breaknews";
    private final int MAX = 2;
    List<ItemBean> Technology_list = new ArrayList<>();
    Context context;
    ArrayList<Integer> search_result = new ArrayList<Integer>();
    function_interface[] Technology_function = new function_interface[MAX];
    private static String[] result;
    private static String[] link;
    private int size = 0;
    public Technology_list(List<ItemBean> list, Context context)
    {
        Technology_list = list;
        this.context = context;
        Technology_function = new function_interface []{
               // new function_interface (){ public boolean function(int key_code){return Set_Technology_search_function();} },
                new function_interface (){ public boolean function(int key_code){return Set_Technology_function(search_result);}},
        };

    }
    /*-----------------------------------------*/
    /*------function in the Technology list ------*/
    /*-----------------------------------------*/
    public void technology_list(ArrayList<Integer> item)
    {
        Technology_list.add(new ItemBean(Get_Technology_Test()));
        /*---------------------------*/
        /*------dynamic update ------*/
        /*---------------------------*/
        if(!item.isEmpty())
        {
            for(int i = 0;i < item.size() ; i++)
            {
                Log.d(TAG,"arr :"+item.get(i));
                Technology_list.add(new ItemBean(result[item.get(i)]));
            }
        }else {
            for (int i = 0; i < size; i++) {
                Technology_list.add(new ItemBean(result[i]));
            }
        }
    }
    public String Get_Technology_Test(){
        String Technology_Ver_Test = "Refresh";
        return Technology_Ver_Test;
    }
    /*-------------------------------------------------*/
    /*-------get function in the Technology list ------*/
    /*-------------------------------------------------*/
    public String[] Get_Technology_link_info()
    {
        return link;
    }
    /*-----------------------------------------*/
    /*---Set function in the Technology list -----*/
    /*-----------------------------------------*/
    public boolean Set_Technology_search_function(ArrayList<Integer>search_link)
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
    public boolean Set_Technology_function(ArrayList<Integer> item)
    {
        Capture_news_info task5 = new Capture_news_info(url);
        Thread t5 = new Thread(task5);//.start()
        try {
            t5.start();
            t5.join();
        }catch (Exception e){
            System.out.println(e);
        }
        result = task5.getCallback();
        size = task5.getCallback_size();
        if(item.isEmpty()) {
            link = task5.getCallback_link();
        }
        return true;
    }

}
