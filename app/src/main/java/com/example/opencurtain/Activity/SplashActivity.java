package com.example.opencurtain.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.opencurtain.Network.API;
import com.example.opencurtain.Network.APIRequest;
import com.example.opencurtain.Network.Method;
import com.example.opencurtain.Network.RequestHandler;
import com.example.opencurtain.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

public class SplashActivity extends AppCompatActivity {

    private APIRequest apiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        apiRequest = APIRequest.getInstance();

        Handler handler = new Handler();
        Message message = handler.obtainMessage();
        Bundle bundle = message.getData();

        String id = bundle.getString("id");
        String password = bundle.getString("password");


//        try {
//            apiRequest.execute(API.users.getEndPoint(), Method.GET, new RequestHandler() {
//                @Override
//                public void onRequestOK(JSONObject jsonObject) {
//                    try {
//                        if(jsonObject.getInt("result") == 200){
//                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();}
//                        else{
//                            Intent intent = new Intent(SplashActivity.this, IntroductionActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onRequestErr(int code) {
//                    System.out.println("Error  :::  "+code);
//                }
//            });
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

        Intent intent = new Intent(SplashActivity.this, IntroductionActivity.class);
        startActivity(intent);
        finish();

    }
}
