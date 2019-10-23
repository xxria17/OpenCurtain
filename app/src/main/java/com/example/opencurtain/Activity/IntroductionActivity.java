package com.example.opencurtain.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.opencurtain.R;
import com.example.opencurtain.SignupActivity.AuthActivity;
import com.example.opencurtain.SignupActivity.WebmailActivity;

public class IntroductionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signUp_btn, signIn_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        signUp_btn = (Button) findViewById(R.id.signup_button);
        signIn_btn = (Button) findViewById(R.id.signin_button);

        signUp_btn.setOnClickListener(this);
        signIn_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if(view == signUp_btn){
            Intent signUp = new Intent(this, WebmailActivity.class);
            startActivity(signUp);
            finish();
        }

        if(view == signIn_btn){
            Intent signIn = new Intent(this, SignInActivity.class);
            startActivity(signIn);
            finish();
        }
    }

}
