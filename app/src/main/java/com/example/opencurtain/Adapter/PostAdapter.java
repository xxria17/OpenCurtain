package com.example.opencurtain.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.opencurtain.Model.PostContent;
import com.example.opencurtain.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private List<PostContent> postContentList;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView univTextView, usernameTextView, timeStampTextView, contentTextView, comment_count;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            univTextView = itemView.findViewById(R.id.university);
            usernameTextView = itemView.findViewById(R.id.username);
            timeStampTextView = itemView.findViewById(R.id.timestamp);
            contentTextView = itemView.findViewById(R.id.post_content);
            comment_count = itemView.findViewById(R.id.comment_count);
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

    public PostAdapter(List<PostContent> postContentList){
        this.postContentList = postContentList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy.MM.dd hh:mm");
//        String timeText = simpleDateFormat.format(postContentList.get(i).timestamp);

        viewHolder.univTextView.setText(postContentList.get(i).boardname);
        viewHolder.contentTextView.setText(postContentList.get(i).content);
        viewHolder.timeStampTextView.setText(postContentList.get(i).timestamp);
        viewHolder.usernameTextView.setText(postContentList.get(i).username);
        viewHolder.comment_count.setText(String.valueOf(postContentList.get(i).commentcount));
    }

    @Override
    public int getItemCount() {
        return postContentList.size();
    }


}
