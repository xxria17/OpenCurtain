package com.example.opencurtain.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opencurtain.R;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{

    private long pressedTime = 0;
    private Button next_btn;
    private EditText id, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();

        next_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if(view == next_btn){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void init(){
        next_btn = (Button) findViewById(R.id.next_button4);
        id = (EditText) findViewById(R.id.edit_id);
        password = (EditText) findViewById(R.id.edit_password);
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
