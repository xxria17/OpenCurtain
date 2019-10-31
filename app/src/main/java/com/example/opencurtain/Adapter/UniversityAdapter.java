package com.example.opencurtain.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.opencurtain.Model.SubscribeContent;
import com.example.opencurtain.Model.UniversityContent;
import com.example.opencurtain.R;

import java.util.List;

public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.ViewHolder> {
    private List<UniversityContent> universityContentList;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.list_textview);
        }
    }

    @NonNull
    @Override
    public UniversityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.alllist_item, viewGroup, false);
        UniversityAdapter.ViewHolder viewHolder = new UniversityAdapter.ViewHolder(v);
        return viewHolder;
    }

    public UniversityAdapter(List<UniversityContent> universityContentList){
        this.universityContentList = universityContentList ;
    }

    @Override
    public void onBindViewHolder(@NonNull UniversityAdapter.ViewHolder viewHolder, int i) {

        viewHolder.textView.setText(universityContentList.get(i).university_name);
    }

    @Override
    public int getItemCount() {
        return universityContentList.size();
    }


}
