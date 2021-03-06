package com.factory.news;

//import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import android.view.KeyEvent;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;


public class MainActivity extends Activity {

    private String TAG = "INX_Factory_UI";
    private ListView listView = null;
    private TextView header = null;
    private Button search_text = null;
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
    // search result
    //List<PyObject> search_result = new ArrayList<PyObject>();
    ArrayList<Integer> search_result = new ArrayList<>();
    // web link
    private static String[] link;
    // bundle sent result code
    private static final int SENT_RESULT = 1;
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
    private static final int FIRST_ITEM = 1;

    public static Integer level_status = MAIN_INFO;
    function_interface[] call_function = new function_interface[MAX_ITEM];


    /*   function : onActivityResult
     *   describe : use intent+bundle to search_view activity
     *              return result from search_view activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch(resultCode){
            case SENT_RESULT:
                ArrayList<Integer> res = new ArrayList<>();
                res = data.getExtras().getIntegerArrayList("search_result");
                String text = data.getExtras().getString("edit_text");
                set_search_list(res);
                set_search_info(res);
                dataChanged(level_status);

                if(res.isEmpty()) {
                    header.setText("????????????");
                } else {
                    header.setText("?????? :" + text);
                }
                // clear array
                res.clear();
                break;

        }

    }
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
        // find View id
        search_text = (Button) findViewById(R.id.id_search_button);
        listView = (ListView)findViewById(R.id.factory_ui_list);
        header = (TextView)findViewById(R.id.id_tvTitle);
        // other list
        adapter = new MyAdapter(list, this);
        // item list
        finance_info = new Finance_list(list,this);
        Internationality_info = new Internationality_list(list, this);
        Politics_info = new Politics_list(list, this);
        Society_info = new Society_list(list, this);
        Technology_info = new Technology_list(list, this);
        Stock_info = new Stock_list(list, this);

        search_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(level_status == MAIN_INFO)
                {
                    Toast.makeText(MainActivity.this,"????????????????????????",Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(MainActivity.this, Search_view.class);
                    intent.putExtra("link", getLink());
                    startActivityForResult(intent, SENT_RESULT);

                    header.setText("?????????");
                }

            }
        });
        header.setText("??????");


        listView.setAdapter(adapter); //??????????????? adapter ?????? ListView
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d(TAG, "firstVisibleItem : " + firstVisibleItem + " visibleItemCount :" + visibleItemCount);
                //if(firstVisibleItem > 0){
                //    view.setVisibility(view.VISIBLE);
                //}else {
                //   view.setVisibility(View.GONE);
                //}
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                // ?????????????????????????????????????????????
                //Log.d(TAG, "index : " + index + "view :" + view.getId());
                link = getLink();
                if (level_status == MAIN_INFO) {
                    call_function[index].function(index);
                } else if (level_status == FINANCE_INFO) {
                    if (index >= FIRST_ITEM) {
                        open_url_web(link[index - FIRST_ITEM]);
                        //Toast.makeText(MainActivity.this,"Not Support !!!!",Toast.LENGTH_LONG).show();
                    } else {
                        finance_info.finance_function[index].function(index);
                    }
                } else if (level_status == INTERNATION_INFO) {
                    if (index >= FIRST_ITEM) {
                        open_url_web(link[index - FIRST_ITEM]);
                        //Toast.makeText(MainActivity.this,"Not Support !!!!",Toast.LENGTH_LONG).show();
                    } else {
                        Internationality_info.Internationality_function[index].function(index);
                    }
                } else if (level_status == POLITICAL_INFO) {
                    if (index >= FIRST_ITEM) {
                        open_url_web(link[index - FIRST_ITEM]);
                        //Toast.makeText(MainActivity.this,"Not Support !!!!",Toast.LENGTH_LONG).show();
                    } else {
                        Politics_info.Politics_function[index].function(index);
                    }
                } else if (level_status == SOCIETY_INFO) {
                    if (index >= FIRST_ITEM) {
                        open_url_web(link[index - FIRST_ITEM]);
                        //Toast.makeText(MainActivity.this,"Not Support !!!!",Toast.LENGTH_LONG).show();
                    } else {
                        Society_info.Society_function[index].function(index);
                    }
                } else if (level_status == TECH_INFO) {
                    if (index >= FIRST_ITEM) {
                        open_url_web(link[index - FIRST_ITEM]);
                        //Toast.makeText(MainActivity.this, "Not Support !!!!", Toast.LENGTH_LONG).show();
                    } else {
                        Technology_info.Technology_function[index].function(index);
                    }
                } else if (level_status == STOCK_INFO) {
                    if (index >= FIRST_ITEM) {
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

    private AbsListView.OnScrollListener onScrollChangeListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if(firstVisibleItem > 0){
                listView.setVisibility(View.VISIBLE);
            }else{
                listView.setVisibility(View.GONE);
            }
        }
    };
    @Override
    protected void onResume(){
        super.onResume();
        switch (level_status){
            case MAIN_INFO:
                header.setText("??????");
                break;
            case FINANCE_INFO:
                header.setText("??????");
                break;
            case INTERNATION_INFO:
                header.setText("??????");
                break;
            case POLITICAL_INFO:
                header.setText("??????");
                break;
            case SOCIETY_INFO:
                header.setText("??????");
                break;
            case TECH_INFO:
                header.setText("??????");
                break;
            case STOCK_INFO:
                header.setText("??????");
                break;
        }
    }
    @Override
    public void onBackPressed() {
    }

    public void factory_list() {
        list.add(new ItemBean( "??????"));
        list.add(new ItemBean("??????"));
        list.add(new ItemBean("??????"));
        list.add(new ItemBean( "??????"));
        list.add(new ItemBean("??????"));
        list.add(new ItemBean("??????"));
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
    /*   function : set_search_info
     *   describe : Set current search link information
     *   input    : List<PyObject>
     *   return   : void
     */

    public void set_search_info(ArrayList<Integer> search_result)
    {

        switch (level_status)
        {
            case FINANCE_INFO:
                finance_info.Set_Finance_search_function(search_result);
                break;
            case INTERNATION_INFO:
                Internationality_info.Set_Internationality_search_function(search_result);
                break;
            case POLITICAL_INFO:
                Politics_info.Set_Politics_search_function(search_result);
                break;
            case SOCIETY_INFO:1
                Society_info.Set_Society_search_function(search_result);
                break;
            case TECH_INFO:
                Technology_info.Set_Technology_search_function(search_result);
                break;
            case STOCK_INFO:
                Stock_info.Set_Stock_search_function(search_result);
                break;
            default:
                break;


        }
        return;
    }
    /*   function : getLink
     *   describe : Get current web link information
     *   input    : void
     *   return   : string[]
     */
    public String[] getLink()
    {
        switch (level_status)
        {
            case FINANCE_INFO:
                link = finance_info.Get_Finance_link_info();
                break;
            case INTERNATION_INFO:
                link = Internationality_info.Get_internationality_link_info();
                break;
            case POLITICAL_INFO:
                link = Politics_info.Get_Politics_link_info();
                break;
            case SOCIETY_INFO:
                link = Society_info.Get_Society_link_info();
                break;
            case TECH_INFO:
                link = Technology_info.Get_Technology_link_info();
                break;
            case STOCK_INFO:
                link = Stock_info.Get_Stock_link_info();
                break;
            default:
                break;
        }
        return link;
    }
    public void set_search_list(ArrayList<Integer> int_list)
    {
        search_result =  int_list;
    }

    public void dataChanged(Integer level_status) {
        int position = listView.getSelectedItemPosition();
        list.clear();
        if (level_status == MAIN_INFO) {
            header.setText("??????");
            factory_list();
        } else if (level_status == FINANCE_INFO) {
            header.setText("??????");
            finance_info.Set_Finance_function(search_result);
            finance_info.finance_list(search_result);
        } else if (level_status == INTERNATION_INFO) {
            header.setText("??????");
            Internationality_info.Set_Internationality_function(search_result);
            Internationality_info.internationality_list(search_result);
        } else if (level_status == POLITICAL_INFO) {
            header.setText("??????");
            Politics_info.Set_Politics_function(search_result);
            Politics_info.politics_list(search_result);
        } else if (level_status == SOCIETY_INFO) {
            header.setText("??????");
            Society_info.Set_Society_function(search_result);
            Society_info.society_list(search_result);
        } else if (level_status == TECH_INFO) {
            header.setText("??????");
            Technology_info.Set_Technology_function(search_result);
            Technology_info.technology_list(search_result);
        } else if (level_status == STOCK_INFO) {
            header.setText("??????");
            Stock_info.Set_Stock_function(search_result);
            Stock_info.stock_list(search_result);
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
        Log.d(TAG,"path : " + uri.getPort());
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

