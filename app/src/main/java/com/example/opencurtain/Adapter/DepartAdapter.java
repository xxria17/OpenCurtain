package com.example.opencurtain.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.opencurtain.Model.DepartmentContent;
import com.example.opencurtain.Model.FacultyContent;
import com.example.opencurtain.R;

import java.util.List;

public class DepartAdapter extends RecyclerView.Adapter<DepartAdapter.ViewHolder> {
    private List<DepartmentContent> departmentContentList;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.list_textview);
        }
    }

    @NonNull
    @Override
    public DepartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.alllist_item, viewGroup, false);
        DepartAdapter.ViewHolder viewHolder = new DepartAdapter.ViewHolder(v);
        return viewHolder;
    }

    public DepartAdapter(List<DepartmentContent> departmentContentList){
        this.departmentContentList = departmentContentList ;
    }

    @Override
    public void onBindViewHolder(@NonNull DepartAdapter.ViewHolder viewHolder, int i) {

        viewHolder.textView.setText(departmentContentList.get(i).department_name);
    }

    @Override
    public int getItemCount() {
        return departmentContentList.size();
    }


}
