package com.example.opencurtain.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.opencurtain.Model.CommentContent;
import com.example.opencurtain.Model.PostContent;
import com.example.opencurtain.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<CommentContent> commentContentList;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView usernameTextView, timeStampTextView, contentTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.comment_username);
            timeStampTextView = itemView.findViewById(R.id.comment_timestamp);
            contentTextView = itemView.findViewById(R.id.comment_content);
        }
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.comment_item, viewGroup, false);
        CommentAdapter.ViewHolder viewHolder = new CommentAdapter.ViewHolder(v);
        return viewHolder;
    }

    public CommentAdapter(List<CommentContent> commentContentList){
        this.commentContentList = commentContentList;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder viewHolder, int i) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy.MM.dd hh:mm");
//        String timeText = simpleDateFormat.format(postContentList.get(i).timestamp);

        viewHolder.contentTextView.setText(commentContentList.get(i).comment);
        viewHolder.timeStampTextView.setText(commentContentList.get(i).timestamp);
        viewHolder.usernameTextView.setText(commentContentList.get(i).username);
    }

    @Override
    public int getItemCount() {
        return commentContentList.size();
    }
}
