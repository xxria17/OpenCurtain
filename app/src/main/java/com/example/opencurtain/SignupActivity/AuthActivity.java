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

public class AuthActivity extends AppCompatActivity implements View.OnClickListener{

    private Button auth_btn;
    private EditText auth_num;

    private APIRequest authRequest;
    private UserContent userContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        auth_btn = (Button) findViewById(R.id.auth_button);
        auth_num = (EditText) findViewById(R.id.edit_authNumber);

        auth_btn.setOnClickListener(this);

        Intent intent = getIntent();
        userContent = new UserContent();
        Log.d("!!!!!!!!!!!!!!!!",intent.getExtras().getString("web email"));
        userContent.email = intent.getExtras().getString("web email");

        //            authRequest = new APIRequest(API.authcheck, Method.POST);
        authRequest = APIRequest.getInstance();
    }

    @Override
    public void onClick(View view){
        if(view == auth_btn){
            authCheck();
        }
    }

    private void authCheck(){

        if(!validate()){
            auth_btn.setEnabled(true);
            return;
        }

        auth_btn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(AuthActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("인증번호 확인중...");
        progressDialog.show();

        final int number = Integer.valueOf(auth_num.getText().toString());
        userContent.authcode = number;

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        JSONObject auth = new JSONObject();
                        try{
                            auth.put("authcode",number);
                            auth.put("email",userContent.email);
                            authRequest.execute(API.authcheck.getEndPoint(), Method.POST, new RequestHandler() {
                                @Override
                                public void onRequestOK(JSONObject jsonObject) {
                                    Intent intent = new Intent(AuthActivity.this,IdPasswordActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.putExtra("email",userContent.email);
                                    intent.putExtra("authcode",userContent.authcode);
                                    startActivity(intent);
                                }

                                @Override
                                public void onRequestErr(int code) {
                                    Toast.makeText(getBaseContext(), "Auth Code Failed", Toast.LENGTH_LONG).show();
                                    auth_btn.setEnabled(true);
                                }
                            },auth);
                        } catch (JSONException e){
                            e.printStackTrace();
                        } catch (MalformedURLException me) {
                            me.printStackTrace();
                        }

                        progressDialog.dismiss();
                    }
                },1000);
    }

    private boolean validate(){
        boolean valid = true;

        String id = auth_num.getText().toString();

        if(id.isEmpty()){
            auth_num.setError("인증번호를 입력해주세요");
            valid = false;
        } else{
            auth_num.setError(null);
        }
        return valid;
    }

}
