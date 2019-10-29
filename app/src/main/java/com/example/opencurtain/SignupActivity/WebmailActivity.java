package com.example.opencurtain.SignupActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opencurtain.Model.UserContent;
import com.example.opencurtain.Network.API;
import com.example.opencurtain.Network.APIRequest;
import com.example.opencurtain.Network.Method;
import com.example.opencurtain.Network.RequestHandler;
import com.example.opencurtain.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

public class WebmailActivity extends AppCompatActivity implements View.OnClickListener{

    private long pressedTime = 0;
    private Button next_btn;
    private EditText email;
    private String getEmail;

    private APIRequest webmailRequest;

    private UserContent userContent = new UserContent() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webmail);

        next_btn = (Button) findViewById(R.id.next_button);
        email = (EditText) findViewById(R.id.edit_univ_webmail);

        next_btn.setOnClickListener(this);

        try{
            webmailRequest = new APIRequest(API.authcode, Method.POST);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

    }


    @Override
    public void onClick(View view){
        if(view == next_btn) {
           postUserEmail();
        }
    }


    @Override
    public void onBackPressed(){
        if( System.currentTimeMillis() > pressedTime + 2000){
            pressedTime = System.currentTimeMillis();
            finish();
        }
        else {
            Toast.makeText(this,"'뒤로' 버튼을 한번 더 누르시면 종료됩니다.",Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    private void postUserEmail(){


        if(!validate()){
            next_btn.setEnabled(true);
            return;
        }

        next_btn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(WebmailActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("인증 메일 보내는 중...");
        progressDialog.show();


        final String getEmail = email.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        JSONObject webmail_auth = new JSONObject();
                        try{
                            webmail_auth.put("email",getEmail);
                            webmailRequest.execute(new RequestHandler() {
                                @Override
                                public void onRequestOK(JSONObject jsonObject) {
                                    Intent intent = new Intent(WebmailActivity.this,AuthActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.putExtra("web email",getEmail);
                                    userContent.email = getEmail;
                                    startActivity(intent);
                                }
                                @Override
                                public void onRequestErr(int code) {
                                    Toast.makeText(getBaseContext(), "Send web mail Failed", Toast.LENGTH_LONG).show();
                                    next_btn.setEnabled(true);
                                }
                            }, webmail_auth);
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                },1000);
    }

    private boolean validate(){
        boolean valid = true;

        String id = email.getText().toString();

        if(id.isEmpty()){
            email.setError("이메일을 입력해주세요");
            valid = false;
        } else{
            email.setError(null);
        }
        return valid;
    }
}
