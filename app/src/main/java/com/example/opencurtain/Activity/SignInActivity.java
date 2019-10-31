package com.example.opencurtain.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opencurtain.Network.API;
import com.example.opencurtain.Network.APIRequest;
import com.example.opencurtain.Network.Method;
import com.example.opencurtain.Network.RequestHandler;
import com.example.opencurtain.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

public class SignInActivity extends AppCompatActivity{

    private long pressedTime = 0;
    private Button next_btn;
    private EditText edit_id, edit_password;
    private static final int REQUEST_SIGNUP = 0;
    private APIRequest loginRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        try{
//            loginRequest = new APIRequest(API.login, Method.POST);
            loginRequest = APIRequest.getInstance();
            loginRequest.execute(API.login.getEndPoint(), Method.POST, new RequestHandler() {
                @Override
                public void onRequestOK(JSONObject jsonObject) {
                    Log.i("APIRequest", jsonObject.toString());
                }

                @Override
                public void onRequestErr(int code) {
                    Log.e("APIRequest","error occur "+ code);
                }
            });
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

    }


    private void init(){
        next_btn = (Button) findViewById(R.id.next_button4);
        edit_id = (EditText) findViewById(R.id.edit_id);
        edit_password = (EditText) findViewById(R.id.edit_password);
    }

    private void login(){

        if(!validate()){
            Toast.makeText(getBaseContext(), "Login Failed", Toast.LENGTH_LONG).show();
            next_btn.setEnabled(true);
            return;
        }

        next_btn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignInActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("로그인 중..");
        progressDialog.show();

        final String id = edit_id.getText().toString();
        final String password = edit_password.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        JSONObject loginInfo = new JSONObject();
                        try{
                            loginInfo.put("email",id);
                            loginInfo.put("password",password);
                            loginRequest.execute(API.login.getEndPoint(), Method.POST, new RequestHandler() {
                                @Override
                                public void onRequestOK(JSONObject jsonObject) {
                                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);

                                    Handler handler = new Handler();
                                    Message message = handler.obtainMessage();
                                    Bundle bundle = new Bundle(2);
                                    bundle.putString("id",id);
                                    bundle.putString("password",password);
                                    message.setData(bundle);
                                    handler.sendMessage(message);

                                }
                                @Override
                                public void onRequestErr(int code) {
                                    Toast.makeText(getBaseContext(), "Login Failed", Toast.LENGTH_LONG).show();
                                    next_btn.setEnabled(true);
                                }
                            }, loginInfo);
                        } catch (JSONException e){
                            e.printStackTrace();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                },3000);

    }

    protected void onAcitivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_SIGNUP){
            if (resultCode == RESULT_OK){
                this.finish();
            }
        }
    }

    private boolean validate(){
        boolean valid = true;

        String id = edit_id.getText().toString();
        String password = edit_password.getText().toString();

        if(id.isEmpty()){
            edit_id.setError("이메일을 입력해주세요");
            valid = false;
        } else{
            edit_id.setError(null);
        }

        if(password.isEmpty() || password.length() < 4){
            edit_password.setError("최소 4글자 이상 입력해주세요");
            valid = false;
        } else{
            edit_password.setError(null);
        }

        return valid;
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
}
