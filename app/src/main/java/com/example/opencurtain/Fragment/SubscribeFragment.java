package com.example.opencurtain.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.opencurtain.Adapter.SubscribeAdapter;
import com.example.opencurtain.Model.PostContent;
import com.example.opencurtain.Model.SubscribeContent;
import com.example.opencurtain.Model.UniversityContent;
import com.example.opencurtain.Network.API;
import com.example.opencurtain.Network.APIRequest;
import com.example.opencurtain.Network.Method;
import com.example.opencurtain.Network.RequestHandler;
import com.example.opencurtain.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubscribeFragment extends Fragment {

    private APIRequest apiRequest;
    private SubscribeAdapter subscribeAdapter;
    private RecyclerView recyclerView;

    List<SubscribeContent> subscribeContentList;
    RecyclerView.LayoutManager layoutManager;

    public SubscribeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_subscribe, container, false);

        init(view);

        apiRequest = APIRequest.getInstance();

        try {
            apiRequest.execute(API.subscribes.getEndPoint(), Method.GET, new RequestHandler() {
                @Override
                public void onRequestOK(JSONObject jsonObject) {
                    try {
                        JSONArray jsonArray = new JSONArray(jsonObject.getString("BODY"));
                        subscribeContentList = mapSubsArrayList(jsonArray);
                        List<SubscribeContent> lisk = new ArrayList<>();

                        for(SubscribeContent content : subscribeContentList){
                            SubscribeContent subscribeContent = new SubscribeContent();
                            subscribeContent.id = content.id;
                            subscribeContent.board = content.board;
                            subscribeContent.boardname = content.boardname;

                            lisk.add(subscribeContent);
                        }
                        Log.d("((((((((((((((((((9", lisk.toString());
                        subscribeAdapter = new SubscribeAdapter(lisk);
                        recyclerView.setAdapter(subscribeAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onRequestErr(int code) {
                    System.out.println("Error ;;;; " + code);
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return view;
    }

    private List<SubscribeContent> mapSubsArrayList(JSONArray jsonArray) throws JSONException{
        List<SubscribeContent> contentList = new ArrayList<>(jsonArray.length());
        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            SubscribeContent content = new SubscribeContent();
            content.setId(jsonObject.getInt("id"));
            content.setBoardname(jsonObject.getString("boardname"));
            content.setBoard(jsonObject.getInt("board"));
            contentList.add(content);
        }
        return contentList;
    }

    private void init(View view){

        recyclerView = view.findViewById(R.id.subscribe_list);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(),1));
    }

}
