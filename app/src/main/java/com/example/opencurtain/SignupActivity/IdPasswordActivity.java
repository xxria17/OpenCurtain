package com.example.opencurtain.SignupActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class IdPasswordActivity extends AppCompatActivity{

    private Button next_btn;
    private EditText univ_email, id, password;
    private String getId, getPassword, getEmail;

    private APIRequest joinRequest;
    private UserContent userContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_password);

        init();

        Intent intent = getIntent();
        userContent = new UserContent();
        userContent.email = intent.getExtras().getString("email");

        univ_email.setText(userContent.email);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validate()){
                    next_btn.setEnabled(true);
                    return;
                }

                final String _id = id.getText().toString();
                final String _password = password.getText().toString();

                Intent intent2 = new Intent(IdPasswordActivity.this, SelectActivity.class);
                intent2.putExtra("username",_id);
                intent2.putExtra("password",_password);
                intent2.putExtra("email",userContent.email);

                startActivity(intent2);
            }
        });

//        try{
//            joinRequest = new APIRequest(API.users, Method.POST);
//        } catch (MalformedURLException e){
//            e.printStackTrace();
//        }

    }



//    private void signUp(){
//
//        if(!validate()){
//            next_btn.setEnabled(true);
//            return;
//        }
//
//        Intent intent = getIntent();
//        userContent = new UserContent();
//        userContent.email = intent.getExtras().getString("email");
//
//        next_btn.setEnabled(false);
//
//        final ProgressDialog progressDialog = new ProgressDialog(IdPasswordActivity.this);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("가입 등록중...");
//        progressDialog.show();
//
//        final String _id = id.getText().toString();
//        final String _password = password.getText().toString();
//
//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        JSONObject join = new JSONObject();
//                        try {
//                            join.put("username",_id);
//                            join.put("password",_password);
//                            join.put("email",userContent.email);
//                            joinRequest.execute(new RequestHandler() {
//                                @Override
//                                public void onRequestOK(JSONObject jsonObject) {
//                                    Intent intent1 = new Intent(IdPasswordActivity.this, SelectActivity.class);
//                                    intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    startActivity(intent1);
//                                }
//
//                                @Override
//                                public void onRequestErr(int code) {
//                                    Toast.makeText(getBaseContext(), "Sign Up Failed", Toast.LENGTH_LONG).show();
//                                    next_btn.setEnabled(true);
//                                }
//                            },join);
//                        } catch (JSONException e){
//                            e.printStackTrace();
//                        }
//                        progressDialog.dismiss();
//                    }
//                } , 1000);
//    }

    private void init(){
        next_btn = (Button) findViewById(R.id.next_button2);
        univ_email = (EditText) findViewById(R.id.edit_univ_webmail2);
        id = (EditText) findViewById(R.id.edit_new_id);
        password = (EditText) findViewById(R.id.edit_new_password);
    }

    private boolean validate(){
        boolean valid = true;

        String _id = id.getText().toString();
        String _password = password.getText().toString();

        if(_id.isEmpty()){
            id.setError("이름을 입력해주세요");
            valid = false;
        } else{
            id.setError(null);
        }

        if(_password.isEmpty()){
            password.setError("비밀번호를 입력해주세요");
            valid = false;
        } else{
            password.setError(null);
        }

        return valid;
    }
}
