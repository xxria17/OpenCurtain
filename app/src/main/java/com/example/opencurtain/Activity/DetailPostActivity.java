package com.example.opencurtain.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.opencurtain.Adapter.CommentAdapter;
import com.example.opencurtain.Model.CommentContent;
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
import java.util.ArrayList;
import java.util.List;

public class DetailPostActivity extends AppCompatActivity {

    private TextView username, timestamp, content, comment_Count, title;
    private EditText comment;
    private Button sendbutton;
    private ImageView backbutton;
    private RecyclerView comment_list;

    private APIRequest apiRequest;
    private CommentAdapter commentAdapter;

    private CommentContent commentContent;
    private int id;

    List<CommentContent> commentContentList;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        init();

        apiRequest = APIRequest.getInstance();

        Intent intent = getIntent();


        String contentSt, timeSt, usernameSt, titleSt;
        int commentCount;

        contentSt = intent.getStringExtra("content");
        timeSt = intent.getStringExtra("timestamp");
        usernameSt = intent.getStringExtra("username");
        commentCount = intent.getIntExtra("commentcount", 0);
        titleSt = intent.getStringExtra("title");
        id = intent.getIntExtra("id",0);


        title.setText(titleSt);
        username.setText(usernameSt);
        timestamp.setText(timeSt);
        content.setText(contentSt);
        username.setTypeface(Typeface.DEFAULT_BOLD);
        comment_Count.setText(String.valueOf(commentCount));

        read_comment();

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void read_comment(){

        try {
            apiRequest.execute(API.comments.getEndPoint() + id, Method.GET, new RequestHandler() {
                @Override
                public void onRequestOK(JSONObject jsonObject) {
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(jsonObject.getString("BODY"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        commentContentList = mapCommentArrayList(jsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    List<CommentContent> list = new ArrayList<>();
                    for(CommentContent content : commentContentList){
                        CommentContent commentContent = new CommentContent();
                        commentContent.id = content.id;
                        commentContent.comment = content.comment;
                        commentContent.posts = content.posts;
                        commentContent.timestamp = content.timestamp;
                        commentContent.username = content.username;

                        list.add(commentContent);
                    }
                    commentAdapter = new CommentAdapter(list);
                    comment_list.setAdapter(commentAdapter);
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

    private void init(){
        username = (TextView) findViewById(R.id.username_tv);
        timestamp = (TextView) findViewById(R.id.timestamp_tv);
        content = (TextView) findViewById(R.id.content_tv);
        comment_Count = (TextView) findViewById(R.id.commentcount);
        title = (TextView) findViewById(R.id.title_textView);

        comment_list = (RecyclerView) findViewById(R.id.comment_list);
        layoutManager = new LinearLayoutManager(DetailPostActivity.this);
        comment_list.setLayoutManager(layoutManager);

        comment = (EditText) findViewById(R.id.edit_comment);
        backbutton = (ImageView) findViewById(R.id.backbutton);
        sendbutton = (Button) findViewById(R.id.sendbutton);
    }

    private List<CommentContent> mapCommentArrayList(JSONArray jsonArray) throws JSONException {
        List<CommentContent> contentList = new ArrayList<>(jsonArray.length());
        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            CommentContent content = new CommentContent();
            content.setId(jsonObject.getInt("id"));
            content.setTimestamp(jsonObject.getString("timestamp"));
            content.setUsername(jsonObject.getString("username"));
            content.setComment(jsonObject.getString("comment"));
            content.setPosts(jsonObject.getInt("post"));
            contentList.add(content);
        }
        return contentList;
    }
}
