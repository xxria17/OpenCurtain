package com.example.opencurtain.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.opencurtain.Adapter.PostAdapter;
import com.example.opencurtain.Model.DepartmentContent;
import com.example.opencurtain.Model.PostContent;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TotalPostFragment extends Fragment {
    private RecyclerView postList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PostAdapter postAdapter;

    private APIRequest postRequest;

    ArrayAdapter<String> postArrayAdapter;
    List<PostContent> postContentList;

    RecyclerView.LayoutManager layoutManager;

    public TotalPostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_total_post, container, false);

       init(view);

        //           postRequest = new APIRequest(API.posts, Method.GET);
        postRequest = APIRequest.getInstance();

        read_post();

       swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {
               read_post();
               swipeRefreshLayout.setRefreshing(false);
           }
       });

       return view;
    }

    private void init(View view){
        postList = (RecyclerView) view.findViewById(R.id.totalpost_list);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        layoutManager = new LinearLayoutManager(getActivity());
        postList.setLayoutManager(layoutManager);
    }

    private void read_post(){
//        postRequest.setUrl(API.posts.appendString(String.valueOf(1)));
        try {
            postRequest.execute(API.posts.getEndPoint() + 1, Method.GET, new RequestHandler() {
                @Override
                public void onRequestOK(JSONObject jsonObject) {
                    try {
                        JSONArray jsonArray = new JSONArray(jsonObject.getString("BODY"));
                        postContentList = mapPostArrayList(jsonArray);
                        List<PostContent> list = new ArrayList<>();
                        for(PostContent content : postContentList) {

                            PostContent postContent = new PostContent();
                            postContent.id = content.id;
                            postContent.board = content.board;
                            postContent.user = content.user;
                            postContent.universityname = content.universityname;
                            postContent.timestamp = content.timestamp;
                            postContent.username = content.username;
                            postContent.content = content.content;
                            postContent.title = content.title;

    //                        postContent.id = content.getId();
    //                        postContent.title = content.getTitle();
    //                        postContent.content = content.getContent();
    //                        postContent.username = content.getUsername();
    //                        postContent.timestamp = content.getTimestamp();
    //                        postContent.universityname = content.getUniversityname();
    //                        postContent.user = content.getUser();
    //                        postContent.board = content.getBoard();

                            list.add(postContent);

                        }
                        Log.d("!!!!!!!!@#$@#$", list.toString());
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
            contentList.add(content);
        }
        return contentList;
    }
}
