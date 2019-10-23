package com.example.opencurtain.SignupActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.opencurtain.R;

public class AuthActivity extends AppCompatActivity implements View.OnClickListener{

    private Button auth_btn;
    private EditText auth_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        auth_btn = (Button) findViewById(R.id.auth_button);
        auth_num = (EditText) findViewById(R.id.edit_authNumber);

        auth_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if(view == auth_btn){
            Intent auth_intent = new Intent(this,IdPasswordActivity.class);
            startActivity(auth_intent);
            finish();
        }
    }

}
