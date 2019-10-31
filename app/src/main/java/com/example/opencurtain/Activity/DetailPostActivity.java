package com.example.opencurtain.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.opencurtain.Model.PostContent;
import com.example.opencurtain.R;

public class DetailPostActivity extends AppCompatActivity {

    private TextView username, timestamp, content, comment_Count;
    private EditText comment;
    private Button sendbutton;
    private ImageView backbutton;

    private PostContent postContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        init();

        Intent intent = getIntent();

        String contentSt, timeSt, usernameSt;
        int commentCount;

        contentSt = intent.getStringExtra("content");
        timeSt = intent.getStringExtra("timestamp");
        usernameSt = intent.getStringExtra("username");
        commentCount = intent.getIntExtra("commentcount", 0);

//        contentSt = postContent.content;
//        timeSt = postContent.timestamp;
//        usernameSt = postContent.username;

        username.setText(usernameSt);
        timestamp.setText(timeSt);
        content.setText(contentSt);
        username.setTypeface(Typeface.DEFAULT_BOLD);
        comment_Count.setText(String.valueOf(commentCount));


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init(){
        username = (TextView) findViewById(R.id.username_tv);
        timestamp = (TextView) findViewById(R.id.timestamp_tv);
        content = (TextView) findViewById(R.id.content_tv);
        comment_Count = (TextView) findViewById(R.id.commentcount);

        comment = (EditText) findViewById(R.id.edit_comment);
        backbutton = (ImageView) findViewById(R.id.backbutton);
        sendbutton = (Button) findViewById(R.id.sendbutton);
    }
}
