package com.example.opencurtain.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import java.sql.SQLOutput;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPageFragment extends Fragment {


    public MyPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        APIRequest apiRequest = null;
        try {
            apiRequest = new APIRequest(API.authcheck, Method.GET);
            apiRequest.execute(new RequestHandler() {
                @Override
                public void onRequestOK(JSONObject jsonObject) {
                    System.out.println("잘될때");
                }

                @Override
                public void onRequestErr(int code) {
                    System.out.println("오류날때" + code);
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return inflater.inflate(R.layout.fragment_my_page, container, false);
    }

}
