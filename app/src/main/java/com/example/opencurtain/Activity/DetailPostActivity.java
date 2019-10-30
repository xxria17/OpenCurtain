package com.example.opencurtain.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.opencurtain.R;

public class DetailPostActivity extends AppCompatActivity {

    private TextView username, timestamp, content;
    private EditText comment;
    private Button sendbutton;
    private ImageView backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        init();


    }

    private void init(){
        username = (TextView) findViewById(R.id.username_tv);
        timestamp = (TextView) findViewById(R.id.timestamp_tv);
        content = (TextView) findViewById(R.id.content_tv);

        comment = (EditText) findViewById(R.id.edit_comment);
        backbutton = (ImageView) findViewById(R.id.backbutton);
        sendbutton = (Button) findViewById(R.id.button);
    }
}
