package com.example.opencurtain.SignupActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.opencurtain.R;

public class IdPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private Button next_btn;
    private EditText univ_email, id, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_password);

        init();
        next_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if(view == next_btn){
            Intent next = new Intent(this, SignupDoneActivity.class);
            startActivity(next);
            finish();
        }
    }

    private void init(){
        next_btn = (Button) findViewById(R.id.next_button2);
        univ_email = (EditText) findViewById(R.id.edit_univ_webmail2);
        id = (EditText) findViewById(R.id.edit_new_id);
        password = (EditText) findViewById(R.id.edit_new_password);
    }
}
