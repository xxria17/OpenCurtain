package com.example.opencurtain.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.opencurtain.Network.API;
import com.example.opencurtain.Network.APIRequest;
import com.example.opencurtain.Network.Method;
import com.example.opencurtain.Network.RequestHandler;
import com.example.opencurtain.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

public class AddPostActivity extends AppCompatActivity {

    private APIRequest apiRequest;
    private ImageView backButton, saveButton;
    private EditText editTitle, editContent;
    private String getTitle , getContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        init();
        apiRequest = APIRequest.getInstance();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPost();
            }
        });

    }

    private void uploadPost(){

        if(!validate()){
            saveButton.setEnabled(true);
            return;
        }

        getContent = editContent.getText().toString();
        getTitle = editTitle.getText().toString();

        saveButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(AddPostActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("글 게시중...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        JSONObject write = new JSONObject();
                        try{
                            write.put("board",1);
                            write.put("title",getTitle);
                            write.put("content",getContent);

                            try {
                                apiRequest.execute(API.posts.getEndPoint(), Method.POST, new RequestHandler() {
                                    @Override
                                    public void onRequestOK(JSONObject jsonObject) {
                                        Intent intent = new Intent(AddPostActivity.this, MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void onRequestErr(int code) {
                                        Toast.makeText(getBaseContext(), "Upload Failed", Toast.LENGTH_LONG).show();
                                        saveButton.setEnabled(true);
                                    }
                                },write);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                },1000
        );
    }

    private boolean validate(){
        boolean valid = true;

        String title = editTitle.getText().toString();
        String content = editContent.getText().toString();

        if(content.isEmpty()){
            editContent.setError("내용을 입력해주세요!");
            valid = false;
        } else{
            editContent.setError(null);
        }
        if(title.isEmpty()){
            editTitle.setError("제목을 입력해주세요!");
            valid = false;
        } else{
            editTitle.setError(null);
        }
        return valid;
    }

    private void init(){
        editTitle = (EditText) findViewById(R.id.edit_title);
        editContent = (EditText) findViewById(R.id.edit_content);
        backButton = (ImageView) findViewById(R.id.return_button);
        saveButton = (ImageView) findViewById(R.id.save_button);
    }
}
