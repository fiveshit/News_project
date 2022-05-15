package com.factory.news;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.util.ArrayList;
import java.util.List;

public class Search_news_info{
    private String TAG = "Thread_proc";
    Context context;
    EditText search_text = null;
    private String[] content;
    List<PyObject> search_result = new ArrayList<PyObject>();

    Search_news_info(String[] content, EditText text, Context context)
    {
        this.content = content;
        this.search_text = text;
        this.context = context;
    }
    /*
    *  init Python
    *  If java needs to use python that call init function
    *  create: Joe
     */
    void initPython() {
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this.context));
        }
    }
    /*
     *
     * function get_search_result
     * describe get result
     * create: Joe
     * return: java.lang.list
     */
    public List<PyObject> getSearch_result()
    {
        return search_result;
    }
    /*
    *   toJava(class type) ex: toJava(Integer.class)
    *   Hwo to use PyObject :
    *   py.getModule("file name") , get("class name") , callAttr("function name",parameter,new Kwarg("parameter"))
    *   Python type is List  ex: List<PyObject> res = obj.asList()
    *   the other way : List<PyObject> obj = py.getModule("main").callAttr("calculations").asList()
    *                   int[] res1 = obj.get(0).toJava(int[].class);
    *                   int[] res2 = obj.get(1).toJava(int[].class);
     */
    public void run() {
        List<PyObject> params = new ArrayList<PyObject>();
        try {
            initPython();
            Python py = Python.getInstance();
            Log.d(TAG, "key word :" + this.search_text.getText());
            for(int i=0;i<this.content.length;i++)
            {
                params.add(PyObject.fromJava(this.content[i]));
            }
            PyObject obj1 = py.getModule("news_capture").callAttr("search_word",params,this.search_text.getText());//new Kwarg("wav_path", path)
            search_result = obj1.asList();
        }catch (Exception e){
            Log.d(TAG, "Exception :" + e);
        }
    }
}
