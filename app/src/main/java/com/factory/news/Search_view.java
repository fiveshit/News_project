package com.factory.news;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chaquo.python.PyObject;

import java.util.ArrayList;
import java.util.List;

public class Search_view extends Activity {
    private String TAG = "Search_view_UI";
    private Search_news_info search_proc = null;
    // search result
    List<PyObject> search_result = new ArrayList<PyObject>();
    // get value from bundle
    private static String[] link;
    private  Button title_button = null;
    private  Button content_button = null;
    private EditText search_edit = null;
    // bundle return result code
    private static final int RECEIVER_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        buildview();
        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // button_bottom.setText("搜尋中");
            }
        });
        search_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE)
                {
                    Intent intent = getIntent();
                    Bundle bundle = new Bundle();
                    link = intent.getStringArrayExtra("link");
                    Log.d(TAG,"key enter");
                    search_proc = new Search_news_info(link,search_edit,Search_view.this);
                    search_proc.run();
                    search_result = search_proc.getSearch_result();
                    ArrayList<Integer> res = new ArrayList<>();
                    for(int i = 0 ; i < search_result.size();i++)
                    {
                        res.add(search_result.get(i).toInt());
                    }
                    Log.d(TAG,"res"+res);
                    bundle.putIntegerArrayList("search_result",res);
                    bundle.putString("edit_text",search_edit.getText().toString());
                    intent.putExtras(bundle);
                    setResult(RECEIVER_RESULT,intent);


                    // init search_result
                    Search_view.this.finish();



                }
                return false;
            }
        });

    }

    private void buildview(){
        title_button = findViewById(R.id.id_title_button);
        content_button = findViewById(R.id.id_content_button);
        search_edit = findViewById(R.id.id_search_edit);

    }
}