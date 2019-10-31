package com.example.opencurtain.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.opencurtain.Model.PostContent;
import com.example.opencurtain.Model.SettingListViewItem;
import com.example.opencurtain.Model.SubscribeContent;
import com.example.opencurtain.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SidemenuAdapter extends RecyclerView.Adapter<SidemenuAdapter.ViewHolder> {
    private List<SubscribeContent> subscribeContentList;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.menu_textView);
        }
    }

    @NonNull
    @Override
    public SidemenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.sidemenu_item, viewGroup, false);
        SidemenuAdapter.ViewHolder viewHolder = new SidemenuAdapter.ViewHolder(v);
        return viewHolder;
    }

    public SidemenuAdapter(List<SubscribeContent> subscribeContentList){
        this.subscribeContentList = subscribeContentList ;
    }

    @Override
    public void onBindViewHolder(@NonNull SidemenuAdapter.ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(subscribeContentList.get(i).boardname);
    }

    @Override
    public int getItemCount() {
        return subscribeContentList.size();
    }


}