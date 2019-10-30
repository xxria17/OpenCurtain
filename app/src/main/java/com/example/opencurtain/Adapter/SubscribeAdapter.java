package com.example.opencurtain.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.opencurtain.Model.PostContent;
import com.example.opencurtain.Model.SubscribeContent;
import com.example.opencurtain.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class SubscribeAdapter extends RecyclerView.Adapter<SubscribeAdapter.ViewHolder> {
    private List<SubscribeContent> subscribeContentList;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.subs_textview);
        }
    }

    @NonNull
    @Override
    public SubscribeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.subscribe_item, viewGroup, false);
        SubscribeAdapter.ViewHolder viewHolder = new SubscribeAdapter.ViewHolder(v);
        return viewHolder;
    }

    public SubscribeAdapter(List<SubscribeContent> subscribeContentList){
        this.subscribeContentList = subscribeContentList ;
    }

    @Override
    public void onBindViewHolder(@NonNull SubscribeAdapter.ViewHolder viewHolder, int i) {

        viewHolder.textView.setText(subscribeContentList.get(i).boardname);
    }

    @Override
    public int getItemCount() {
        return subscribeContentList.size();
    }


}

