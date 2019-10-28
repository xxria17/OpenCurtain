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
    private String getEmail;

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
        if(view == next_btn) {
            getEmail = email.getText().toString();

            if (getEmail.isEmpty()) {
                Toast.makeText(WebmailActivity.this, "이메일을 적어주세요!", Toast.LENGTH_SHORT).show();
                return;
            } else {

                // 인증 이메일 전송
                Intent sendEmail = new Intent(Intent.ACTION_SEND);
                sendEmail.setType("plain/text");
                sendEmail.putExtra(Intent.EXTRA_EMAIL, getEmail);
                sendEmail.putExtra(Intent.EXTRA_SUBJECT, "[Open Curtain] 본인 인증 확인 메일"); //보내질 이메일 제목
                sendEmail.putExtra(Intent.EXTRA_TEXT, ""); //보내질 이메일 내용
                startActivity(sendEmail);

                Intent next = new Intent(this, AuthActivity.class);
                startActivity(next);
                finish();
            }
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
}
