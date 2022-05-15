package com.factory.news;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.chaquo.python.PyObject;

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
    private String TAG = "Politics_UI";
    private final String url = "https://udn.com/news/breaknews/1/1#breaknews";
    private final int MAX = 2;
    List<ItemBean> Politics_list = new ArrayList<>();
    Context context;
    List<PyObject> search_result = new ArrayList<PyObject>();
    function_interface[] Politics_function = new function_interface[MAX];
    private static String[] result;
    private static String[] link;
    private int size = 0;
    public Politics_list(List<ItemBean> list, Context context)
    {
        Politics_list = list;
        this.context = context;
        Politics_function = new function_interface []{
                //new function_interface (){ public boolean function(int key_code){return Set_Politics_search_function();} },
                new function_interface (){ public boolean function(int key_code){return Set_Politics_function(search_result);}},
        };

    }
    /*-----------------------------------------*/
    /*------function in the Politics list ------*/
    /*-----------------------------------------*/
    public void politics_list(List<PyObject> item)
    {

        Politics_list.add(new ItemBean(Get_Politics_Test()));

        /*---------------------------*/
        /*------dynamic update ------*/
        /*---------------------------*/
        if(!item.isEmpty())
        {
            for(int i = 0;i < item.size() ; i++)
            {
                Log.d(TAG,"arr :"+item.get(i).toInt());
                Politics_list.add(new ItemBean(result[item.get(i).toInt()]));
            }
        }else {
            for (int i = 0; i < size; i++) {
                Politics_list.add(new ItemBean(result[i]));
            }
        }
    }

    public String Get_Politics_Test(){
        String Politics_Ver_Test = "Refresh";
        return Politics_Ver_Test;
    }
    /*----------------------------------------------*/
    /*-------get function in the Politics list -----*/
    /*----------------------------------------------*/
    public String[] Get_Politics_link_info()
    {
        return link;
    }
    /*-----------------------------------------*/
    /*---Set function in the Politics list -----*/
    /*-----------------------------------------*/
    public boolean Set_Politics_search_function(List<PyObject> search_link)
    {
        for(int i = 0;i < search_link.size() ; i++)
        {
            //Log.d(TAG,"search_link :"+search_link.get(i).toInt());
            String tmp  = link[search_link.get(i).toInt()];
            link[i] = tmp;
            //Log.d(TAG,"link  :"+link[i]);

        }
        //Toast.makeText(this.context,"Not Support !!!!",Toast.LENGTH_LONG).show();
        return true;
    }
    public boolean Set_Politics_function(List<PyObject> item)
    {
        Capture_news_info task3 = new Capture_news_info(url);
        Thread t3 = new Thread(task3);//.start()
        try {
            t3.start();
            t3.join();
        }catch (Exception e){
            System.out.println(e);
        }
        result = task3.getCallback();
        size = task3.getCallback_size();
        if(item.isEmpty()) {
            link = task3.getCallback_link();
        }
        return true;
    }

}
