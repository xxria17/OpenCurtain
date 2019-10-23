package com.example.opencurtain.SignupActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.opencurtain.Activity.MainActivity;
import com.example.opencurtain.R;

public class SignupDoneActivity extends AppCompatActivity implements View.OnClickListener{

    private Button continue_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_done);

        continue_btn = (Button) findViewById(R.id.next_button3);
        continue_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if(view == continue_btn){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
