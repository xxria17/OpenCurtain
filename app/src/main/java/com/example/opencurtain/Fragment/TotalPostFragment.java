package com.example.opencurtain.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.opencurtain.Network.API;
import com.example.opencurtain.Network.APIRequest;
import com.example.opencurtain.Network.Method;
import com.example.opencurtain.Network.RequestHandler;
import com.example.opencurtain.R;

import org.json.JSONObject;

import java.net.MalformedURLException;

/**
 * A simple {@link Fragment} subclass.
 */
public class TotalPostFragment extends Fragment {
    private RecyclerView postList;
    private SwipeRefreshLayout swipeRefreshLayout;

    private APIRequest postRequest;

    public TotalPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_total_post, container, false);

       init(view);

       try{
           postRequest = new APIRequest(API.posts, Method.GET);
       } catch (MalformedURLException e){
           e.printStackTrace();
       }


       return view;
    }

    private void init(View view){
        postList = (RecyclerView) view.findViewById(R.id.totalpost_list);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
    }

    private void read_post(){
        postRequest.execute(new RequestHandler() {
            @Override
            public void onRequestOK(JSONObject jsonObject) {

            }

            @Override
            public void onRequestErr(int code) {

            }
        });
    }
}
