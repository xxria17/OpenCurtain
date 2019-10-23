package com.example.opencurtain.SignupActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opencurtain.R;

public class WebmailActivity extends AppCompatActivity implements View.OnClickListener{

    private long pressedTime = 0;
    private Button next_btn;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webmail);

        next_btn = (Button) findViewById(R.id.next_button);
        email = (EditText) findViewById(R.id.edit_univ_webmail);

        next_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if(view == next_btn){
        Intent next = new Intent(this,AuthActivity.class);
        startActivity(next);
        finish();}
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
