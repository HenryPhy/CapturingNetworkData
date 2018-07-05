package com.example.a4_day1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
public class LeftAdapter extends BaseAdapter {
    private Context context;
    private List<String>mDatas;

    public LeftAdapter(Context context, List<String> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LeftViewHolder holder=null;
        if (convertView==null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.item_lv,null);
            holder = new LeftViewHolder();
            holder.tv=convertView.findViewById(R.id.item_lv_tv);
            convertView.setTag(holder);
        }
        else {
            holder= (LeftViewHolder) convertView.getTag();
        }
        holder.tv.setText(mDatas.get(position));
        if (position==2) {
            holder.tv.setTextColor(Color.RED);
        }
        return convertView;
    }

    class LeftViewHolder{
        TextView tv;
    }
}
