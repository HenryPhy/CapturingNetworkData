package com.example.a4_day1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
public class ListRvAdapter extends RecyclerView.Adapter<ListRvAdapter.ListViewHolder>{
    private Context context;
    private List<InfoBean.DataBean>mDatas;
    public ListRvAdapter(Context context, List<InfoBean.DataBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv,parent,false);
        ListViewHolder holder = new ListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        InfoBean.DataBean bean = mDatas.get(position);
        holder.titleTv.setText(bean.getId());
        Picasso.with(context).load(bean.getPic()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{
        TextView titleTv;
        ImageView iv;
        public ListViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.item_list_tv);
            iv = (ImageView) itemView.findViewById(R.id.item_list_iv);
        }
    }
}
