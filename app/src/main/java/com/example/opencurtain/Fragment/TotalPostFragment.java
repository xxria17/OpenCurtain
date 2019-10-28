package com.example.opencurtain.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.opencurtain.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TotalPostFragment extends Fragment {
    private RecyclerView postList;

    public TotalPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_total_post, container, false);

       return view;
    }

    private void init(View view){
        postList = (RecyclerView) view.findViewById(R.id.totalpost_list);
    }
}
