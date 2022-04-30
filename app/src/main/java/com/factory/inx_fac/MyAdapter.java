package com.factory.inx_fac;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import android.widget.CheckBox;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private static final String TAG = "MyAdapter";
    //List Data Val
    private List<ItemBean> list = null;

    // Select CheckBox Status
    private static HashMap<Integer,Boolean> isSelected;

    // Title
    private Context context = null;

    //Setting Layout
    private LayoutInflater inflater = null;

    public MyAdapter(List<ItemBean> list, Context context) {
        this.list = list;
        this.context = context;

        //isSelected = new HashMap<Integer, Boolean>();

        //Layout loader object  
        inflater = LayoutInflater.from(context);

        // init data
        //initDate();
    }

    private void initDate(){
        for(int i=0; i<list.size();i++) {
            getIsSelected().put(i,false);
        }
    }

    public void refresh(List<ItemBean> mlist) {
        list = mlist;
        notifyDataSetChanged();
    }

    // The number of data in the data set in the adapter
    //@Override
    public int getCount() {
        //Log.d(TAG , " GetCount : ");
        return list.size();
    }

    // Get the data item corresponding to the specified index in the data set
    //@Override
    public Object getItem(int position) {
        //Log.d(TAG , " getItem : ");
        return list.get(position);
    }

    // Get the ID corresponding to the specified row
    public static HashMap<Integer,Boolean> getIsSelected() {
        //Log.d(TAG , " HasMap : ");
        return isSelected;
    }

    public void setIsSelected(HashMap<Integer,Boolean> isSelected) {
        //Log.d(TAG , " setIsSelected : ");
        MyAdapter.isSelected = isSelected;
    }

	// Get mapping item ID
    //@Override
    public long getItemId(int position) {
        return position;
    }

    // Get the content displayed by each Item
    //@Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemBean itemBean = list.get(position);

        View view = inflater.inflate(R.layout.item, null);

        //ImageView imageView = (ImageView) view.findViewById(R.id.id_iv);
        TextView tvTitle = (TextView) view.findViewById(R.id.id_tvTitle);


        //imageView.setImageResource(itemBean.ItemImageResid);
        tvTitle.setText(itemBean.ItemTitle);


        Log.d(TAG , " GetView : " +"Position : " + position);
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }




}
