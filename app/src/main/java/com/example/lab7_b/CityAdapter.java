package com.example.lab7_b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends BaseAdapter {
    private final View.OnClickListener onClickListener;
    Context ctx;
    int layoutItem;
    ArrayList<City> arrayList;

    public CityAdapter(Context ctx, int layoutItem, ArrayList<City> arrayList) {
        this.ctx = ctx;
        this.layoutItem = layoutItem;
        this.arrayList = arrayList;
        onClickListener = null;
    }

    public CityAdapter(View.OnClickListener onClickListener, int item_layout, ArrayList<City> arrList) {
        this.onClickListener= onClickListener;
        this.layoutItem = layoutItem;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(ctx).inflate(layoutItem,parent,false);
        TextView txtName =view.findViewById(R.id.textView2);
        txtName.setText(arrayList.get(position).getId()+". " + arrayList.get(position).getNameCity());
        return view;
    }
}
