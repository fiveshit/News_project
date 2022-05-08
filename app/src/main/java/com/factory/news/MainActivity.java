package com.factory.news;

//import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import android.view.KeyEvent;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import org.jsoup.Jsoup;



public class MainActivity extends Activity {

    public String TAG = "INX_Factory_UI";
    private ListView listView = null;
    // Item info class
    private Finance_list finance_info = null;
    private Internationality_list Internationality_info = null;
    private Politics_list Politics_info = null;
    private Society_list Society_info = null;
    private Technology_list Technology_info = null;
    private Stock_list Stock_info = null;

    private MyAdapter adapter = null;
    List<ItemBean> list = new ArrayList<>();
    static Stack<Integer> level = new Stack<>();
    // web link
    private static String[] link;

    // level status
    private static final int MAIN_INFO = 1;
    private static final int FINANCE_INFO = 2;
    private static final int INTERNATION_INFO = 3;
    private static final int POLITICAL_INFO = 4;
    private static final int SOCIETY_INFO = 5;
    private static final int TECH_INFO = 6;
    private static final int STOCK_INFO = 7;

    // Main Page MAX item
    private static final int MAX_ITEM = 8;

    // usb Page First item
    private static final int FIRST_ITEM = 2;

    public static Integer level_status = MAIN_INFO;
    function_interface[] call_function = new function_interface[MAX_ITEM];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG ", "onCreate");
        //  inx factory UI list
        factory_list();
        level.push(level_status);
        // function array
        call_function = new function_interface[]{
                new function_interface() {
                    public boolean function(int key_code) {
                        return Finance_info_next();
                    }
                },
                new function_interface() {
                    public boolean function(int key_code) {
                        return Internationlity_info_next();
                    }
                },
                new function_interface() {
                    public boolean function(int key_code) {
                        return Politics_info_next();
                    }
                },
                new function_interface() {
                    public boolean function(int key_code) {
                        return Society_info_next();
                    }
                },
                new function_interface() {
                    public boolean function(int key_code) {
                        return Technology_info_next();
                    }
                },
                new function_interface() {
                    public boolean function(int key_code) {
                        return Stock_info_next();
                    }
                },


        };

        // other list
        adapter = new MyAdapter(list, this);
        // item list
        finance_info = new Finance_list(list, this);
        Internationality_info = new Internationality_list(list, this);
        Politics_info = new Politics_list(list, this);
        Society_info = new Society_list(list, this);
        Technology_info = new Technology_list(list, this);
        Stock_info = new Stock_list(list, this);
        listView = findViewById(R.id.factory_ui_list);
        listView.setAdapter(adapter); //將設定好的 adapter 丟進 ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                // 利用索引值取得點擊的項目內容。
                Log.d(TAG, "index : " + index + "view :" + view.getId());

                if (level_status == MAIN_INFO) {
                    call_function[index].function(index);
                } else if (level_status == FINANCE_INFO) {
                    if (index >= FIRST_ITEM) {
                        link = finance_info.Get_Finance_link_info();
                        open_url_web(link[index - FIRST_ITEM]);
                        //Toast.makeText(MainActivity.this,"Not Support !!!!",Toast.LENGTH_LONG).show();
                    } else {
                        finance_info.finance_function[index].function(index);
                    }
                } else if (level_status == INTERNATION_INFO) {
                    if (index >= FIRST_ITEM) {
                        link = Internationality_info.Get_internationality_link_info();
                        open_url_web(link[index - FIRST_ITEM]);
                        //Toast.makeText(MainActivity.this,"Not Support !!!!",Toast.LENGTH_LONG).show();
                    } else {
                        Internationality_info.Internationality_function[index].function(index);
                    }
                } else if (level_status == POLITICAL_INFO) {
                    if (index >= FIRST_ITEM) {
                        link = Politics_info.Get_Politics_link_info();
                        open_url_web(link[index - FIRST_ITEM]);
                        //Toast.makeText(MainActivity.this,"Not Support !!!!",Toast.LENGTH_LONG).show();
                    } else {
                        Politics_info.Politics_function[index].function(index);
                    }
                } else if (level_status == SOCIETY_INFO) {
                    if (index >= FIRST_ITEM) {
                        link = Society_info.Get_Society_link_info();
                        open_url_web(link[index - FIRST_ITEM]);
                        //Toast.makeText(MainActivity.this,"Not Support !!!!",Toast.LENGTH_LONG).show();
                    } else {
                        Society_info.Society_function[index].function(index);
                    }
                } else if (level_status == TECH_INFO) {
                    if (index >= FIRST_ITEM) {
                        Log.d(TAG, "TECH_INFO index :" + index);
                        link = Technology_info.Get_Technology_link_info();
                        open_url_web(link[index - FIRST_ITEM]);
                        //Toast.makeText(MainActivity.this, "Not Support !!!!", Toast.LENGTH_LONG).show();
                    } else {
                        Technology_info.Technology_function[index].function(index);
                    }
                } else if (level_status == STOCK_INFO) {
                    if (index >= FIRST_ITEM) {
                        link = Stock_info.Get_Stock_link_info();
                        open_url_web(link[index - FIRST_ITEM]);
                        //Toast.makeText(MainActivity.this, "Not Support !!!!", Toast.LENGTH_LONG).show();
                    } else {
                        Stock_info.stock_function[index].function(index);
                    }
                }
                dataChanged(level_status);

            }
        });


    }


    @Override
    public void onBackPressed() {

    }

    public void factory_list() {
        list.add(new ItemBean( "財經"));
        list.add(new ItemBean("國際"));
        list.add(new ItemBean("政治"));
        list.add(new ItemBean( "社會"));
        list.add(new ItemBean("科技"));
        list.add(new ItemBean("股市"));
    }
    // push_previous_level_to_stack
    static void push_previous_level_to_stack() {
        if (level.search(level_status) == -1) {
            level.push(level_status);
        }
    }

    //key pad
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int iCurrItem = listView.getSelectedItemPosition();
        Log.d(TAG, "key : " + keyCode + "  , Event : " + event + " , item : " + iCurrItem);
        if (keyCode == KeyEvent.ACTION_DOWN) {

            if(level_status == MAIN_INFO) {
                call_function[iCurrItem].function(keyCode);
            }else if(level_status == FINANCE_INFO){
                finance_info.finance_function[iCurrItem].function(keyCode);
            }else if(level_status == INTERNATION_INFO){
                Internationality_info.Internationality_function[iCurrItem].function(keyCode);
            }else if(level_status == POLITICAL_INFO){
                Politics_info.Politics_function[iCurrItem].function(keyCode);
            }else if(level_status == SOCIETY_INFO){
                Society_info.Society_function[iCurrItem].function(keyCode);
            }else if(level_status == TECH_INFO){
                Technology_info.Technology_function[iCurrItem].function(keyCode);
            }
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            Log.d(TAG,"press center key");
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(level_status == MAIN_INFO)
            {
                finish();
                System.exit(0);
            }
            level_status = level.pop();
        }

        // update listview and TextView show
        dataChanged(level_status);
        super.onKeyDown(keyCode,event);
        return true;
    }



    public void dataChanged(int level_status) {
        int position = listView.getSelectedItemPosition();
        Log.d(TAG, " item position : " + position + " level_status : " + level_status);

        list.clear();
        if (level_status == MAIN_INFO) {
            factory_list();
        } else if (level_status == FINANCE_INFO) {
            finance_info.Set_Finance_function();
            finance_info.finance_list();
        } else if (level_status == INTERNATION_INFO) {
            Internationality_info.Set_Internationality_function();
            Internationality_info.internationality_list();
        } else if (level_status == POLITICAL_INFO) {
            Politics_info.Set_Politics_function();
            Politics_info.politics_list();
        } else if (level_status == SOCIETY_INFO) {
            Society_info.Set_Society_function();
            Society_info.society_list();
        } else if (level_status == TECH_INFO) {
            Technology_info.Set_Technology_function();
            Technology_info.technology_list();
        } else if (level_status == STOCK_INFO) {
            Stock_info.Set_Stock_function();
            Stock_info.stock_list();
        }


        listView.setAdapter(adapter);

        //Set listView init position
        listView.setSelection(position);
    }

    /*---------------------------------------------*/
    /*---execute function in the Finance list -----*/
    /*---------------------------------------------*/
    public void open_url_web(String link)
    {
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    /*-----------------------------------------*/
    /*---Set function the in Main info list----*/
    /*-----------------------------------------*/

    public boolean Finance_info_next(){
        push_previous_level_to_stack();
        level_status = FINANCE_INFO;
        return true;
    }
    public boolean Internationlity_info_next(){
        push_previous_level_to_stack();
        level_status = INTERNATION_INFO;
        return true;
    }
    public boolean Politics_info_next(){
        push_previous_level_to_stack();
        level_status = POLITICAL_INFO;
        return true;
    }
    public boolean Society_info_next(){
        push_previous_level_to_stack();
        level_status = SOCIETY_INFO;
        return true;
    }
    public boolean Technology_info_next(){
        push_previous_level_to_stack();
        level_status = TECH_INFO;
        return true;
    }
    public boolean Stock_info_next(){
        push_previous_level_to_stack();
        level_status = STOCK_INFO;
        return true;

    }



}

