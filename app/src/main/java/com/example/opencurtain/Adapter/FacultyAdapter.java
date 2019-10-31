package com.example.opencurtain.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.opencurtain.Model.FacultyContent;
import com.example.opencurtain.Model.UniversityContent;
import com.example.opencurtain.R;

import java.util.List;

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.ViewHolder> {
    private List<FacultyContent> facultyContentList;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.list_textview);
        }
    }

    @NonNull
    @Override
    public FacultyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.alllist_item, viewGroup, false);
        FacultyAdapter.ViewHolder viewHolder = new FacultyAdapter.ViewHolder(v);
        return viewHolder;
    }

    public FacultyAdapter(List<FacultyContent> facultyContentList){
        this.facultyContentList = facultyContentList ;
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyAdapter.ViewHolder viewHolder, int i) {

        viewHolder.textView.setText(facultyContentList.get(i).faculty_name);
    }

    @Override
    public int getItemCount() {
        return facultyContentList.size();
    }


}

