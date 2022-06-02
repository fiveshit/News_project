package com.factory.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.chaquo.python.Kwarg;
import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.android.AndroidPlatform;
import com.chaquo.python.Python;


//---------------------------------------//
// Name : Finance class
// Description : Main UI level 2 from Finance next
// Input : List<ItemBean> , Context context
// Output : -
// Return : -
// ---------------------------------------//
public class Finance_list {

    private String TAG = "Finance_UI";
    private final String url = "https://udn.com/news/breaknews/1/6#breaknews";
    private final int MAX_FINANCE_LIST = 2;
    List<ItemBean> Finance_list = new ArrayList<>();
    Context context;
    private static String[] result;
    private static String[] link;
    private int size = 0;
    ArrayList<Integer> search_result = new ArrayList<>();
    function_interface[] finance_function;// = new function_interface[MAX_FINANCE_LIST];
    public Finance_list(List<ItemBean> list , Context context)
    {
        Finance_list = list;
        this.context = context;
        finance_function = new function_interface []{
            //new function_interface (){ public boolean function(int key_code){return Set_Finance_search_function();} },
            new function_interface (){ public boolean function(int key_code){return Set_Finance_function(search_result);}},
        };

    }

    /*-----------------------------------------*/
    /*------function in the Finance list ------*/
    /*-----------------------------------------*/
    public void finance_list( ArrayList<Integer> item)
    {
        Finance_list.add(new ItemBean(Get_Finance_Test()));
        /*---------------------------*/
        /*------dynamic update ------*/
        /*---------------------------*/

        if(!item.isEmpty())
        {
            for(int i = 0;i < item.size() ; i++)
            {
                Log.d(TAG,"arr :"+item.get(i));
                Finance_list.add(new ItemBean(result[item.get(i)]));
            }
        }else
        {
            for (int i = 0; i < size; i++) {
                Finance_list.add(new ItemBean(result[i]));
            }
        }
    }

    public String Get_Finance_Test(){
        String Finance_Test = "Refresh";
        return Finance_Test;
    }
    /*---------------------------------------------*/
    /*-------get function in the Finance list -----*/
    /*---------------------------------------------*/
    public static String[] Get_Finance_link_info()
    {
        return link;
    }
    /*-----------------------------------------*/
    /*---Set function in the Finance list -----*/
    /*-----------------------------------------*/
    public boolean Set_Finance_search_function(ArrayList<Integer> search_link)
    {

        for(int i = 0;i < search_link.size() ; i++)
        {
            //Log.d(TAG,"search_link :"+search_link.get(i).toInt());
            String tmp  = link[search_link.get(i)];
            link[i] = tmp;
            //Log.d(TAG,"link  :"+link[i]);

        }
        return true;
        //Toast.makeText(this.context,"Not Support !!!!",Toast.LENGTH_LONG).show();

    }
    public boolean Set_Finance_function(ArrayList<Integer> item)
    {
        Capture_news_info task1 = new Capture_news_info(url);
        Thread t1 = new Thread(task1);//.start()
        try {
            t1.start();
            t1.join();
        }catch (Exception e){
            System.out.println(e);
        }
        result = task1.getCallback();
        if(item.isEmpty()) {
            link = task1.getCallback_link();
        }
        size = task1.getCallback_size();
        return true;
    }

}
