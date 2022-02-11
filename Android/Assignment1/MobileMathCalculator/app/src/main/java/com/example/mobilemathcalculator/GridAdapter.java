package com.example.mobilemathcalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    LayoutInflater inflater;
    ArrayList<String> btnList = new ArrayList<>();

    public GridAdapter(Context context, ArrayList objects) {
        btnList = objects;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return btnList.size();
    }

    @Override
    public String getItem(int position) {
        return (String) btnList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean isEnabled(int position) {
        // Return true for clickable, false for not
        if (position == 0) {
            return false;
        }
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.grid_view_items, null);
        TextView tvItem = (TextView) v.findViewById(R.id.tvItem);
        tvItem.setText((String) btnList.get(position));
        return v;

    }

}