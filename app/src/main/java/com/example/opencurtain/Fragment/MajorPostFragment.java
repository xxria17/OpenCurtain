package com.example.opencurtain.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.opencurtain.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MajorPostFragment extends Fragment {


    public MajorPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_major_post, container, false);
    }

}