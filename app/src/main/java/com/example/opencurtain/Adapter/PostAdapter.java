package com.example.opencurtain.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.opencurtain.R;

import java.text.SimpleDateFormat;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView univTextView, usernameTextView, timeStampTextView, contentTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            univTextView = itemView.findViewById(R.id.university);
            usernameTextView = itemView.findViewById(R.id.username);
            timeStampTextView = itemView.findViewById(R.id.timestamp);
            contentTextView = itemView.findViewById(R.id.post_content);
        }
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.post_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy.MM.dd hh:mm");
//        String timeText = simpleDateFormat.format();
//
//        viewHolder.univTextView.setText();
//        viewHolder.contentTextView.setText();
//        viewHolder.timeStampTextView.setText(timeText);
//        viewHolder.usernameTextView.setText();
    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
