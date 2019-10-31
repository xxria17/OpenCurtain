package com.example.opencurtain.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.opencurtain.Activity.DetailPostActivity;
import com.example.opencurtain.Adapter.PostAdapter;
import com.example.opencurtain.Model.PostContent;
import com.example.opencurtain.Network.API;
import com.example.opencurtain.Network.APIRequest;
import com.example.opencurtain.Network.Method;
import com.example.opencurtain.Network.RequestHandler;
import com.example.opencurtain.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPageFragment extends Fragment {

    private APIRequest apiRequest;
    List<PostContent> postContentList;
    private RecyclerView postList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PostAdapter postAdapter;

    RecyclerView.LayoutManager layoutManager;

    public MyPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_my_page, container, false);
        init(view);
        read_myPost();
        select_post();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                read_myPost();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    private void init(View view){
        postList = (RecyclerView) view.findViewById(R.id.myList);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout3);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        layoutManager = new LinearLayoutManager(getActivity());
        postList.setLayoutManager(layoutManager);
    }

    private void read_myPost(){
        apiRequest = APIRequest.getInstance();

        try {
            apiRequest.execute(API.users.getEndPoint()+"/post", Method.GET, new RequestHandler() {
                @Override
                public void onRequestOK(JSONObject jsonObject) {
                    try {
                        JSONArray jsonArray = new JSONArray(jsonObject.getString("BODY"));
                        postContentList = mapPostArrayList(jsonArray);
                        List<PostContent> list = new ArrayList<>();
                        for(PostContent content : postContentList){

                            PostContent postContent = new PostContent();
                            postContent.id = content.id;
                            postContent.board = content.board;
                            postContent.user = content.user;
                            postContent.universityname = content.universityname;
                            postContent.timestamp = content.timestamp;
                            postContent.username = content.username;
                            postContent.content = content.content;
                            postContent.title = content.title;
                            postContent.boardname = content.boardname;

                            list.add(postContent);
                        }
                        postAdapter = new PostAdapter(list);
                        postList.setAdapter(postAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onRequestErr(int code) {
                    System.out.println("Error ; " + code);
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //* 게시글 선택하기
    private void select_post(){

        postList.addOnItemTouchListener(new TotalPostFragment.RecyclerTouchListener(getActivity().getApplicationContext(), postList, new TotalPostFragment.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent privIntent = new Intent(getActivity().getBaseContext(), DetailPostActivity.class);
                privIntent.putExtra("username",postContentList.get(position).username);
                privIntent.putExtra("timestamp",postContentList.get(position).timestamp);
                privIntent.putExtra("id",postContentList.get(position).id);
                privIntent.putExtra("board",postContentList.get(position).board);
                privIntent.putExtra("content", postContentList.get(position).content);
                startActivity(privIntent);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private List<PostContent> mapPostArrayList(JSONArray jsonArray) throws JSONException{
        List<PostContent> contentList = new ArrayList<>(jsonArray.length());
        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            PostContent content = new PostContent();
            content.setId(jsonObject.getInt("id"));
            content.setUniversityname(jsonObject.getString("universityname"));
            content.setBoard(jsonObject.getInt("board"));
            content.setTitle(jsonObject.getString("title"));
            content.setContent(jsonObject.getString("content"));
            content.setTimestamp(jsonObject.getString("timestamp"));
            content.setUser(jsonObject.getInt("user"));
            content.setUsername(jsonObject.getString("username"));
            content.setBoardname(jsonObject.getString("boardname"));
            contentList.add(content);
        }
        return contentList;
    }
}
