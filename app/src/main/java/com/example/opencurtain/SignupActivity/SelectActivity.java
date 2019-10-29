package com.example.opencurtain.SignupActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.opencurtain.Fragment.UniversityPostFragment;
import com.example.opencurtain.Model.DepartmentContent;
import com.example.opencurtain.Model.FacultyContent;
import com.example.opencurtain.Model.UniversityContent;
import com.example.opencurtain.Model.UserContent;
import com.example.opencurtain.Network.API;
import com.example.opencurtain.Network.APIRequest;
import com.example.opencurtain.Network.Method;
import com.example.opencurtain.Network.RequestHandler;
import com.example.opencurtain.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private APIRequest select_univ_Request, select_depart_Request, select_facul_Request;
    private UserContent userContent;
    private UniversityContent universityContent;
    private DepartmentContent departmentContent;
    private FacultyContent facultyContent;
    private Spinner univ_spin, facu_spin, depart_spin;
    private Button nextbutton;
    String[] item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Intent intent = getIntent();
        userContent = new UserContent();
        userContent.email = intent.getExtras().getString("email");
        userContent.user_name = intent.getExtras().getString("username");
        userContent.password = intent.getExtras().getString("password");

        init();

        try{
            select_univ_Request = new APIRequest(API.universitys, Method.GET);
            select_facul_Request = new APIRequest(API.facultys, Method.GET);
            select_depart_Request = new APIRequest(API.departments, Method.GET);

        } catch (MalformedURLException e){
            e.printStackTrace();
        }


        item = new String[]{"선택하세요","제주대학교","한라대학교","관광대학교","폴리텍"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        univ_spin.setAdapter(adapter);

        univ_spin.setOnItemSelectedListener(this);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(SelectActivity.this,""+ item[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView){

    }

    private void getUniversity(){

        final ArrayList<String> list = new ArrayList<>();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        final JSONObject selectObj = new JSONObject();
                        select_univ_Request.execute(new RequestHandler() {
                            @Override
                            public void onRequestOK(JSONObject jsonObject) {
                                try{
                                list.add(selectObj.getString("result"));

                            } catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }


                            @Override
                            public void onRequestErr(int code) {
                                Toast.makeText(getBaseContext(), "Request Failed", Toast.LENGTH_LONG).show();
                            }
                        },selectObj);
                    }
                },1000
        );
    }

    private void selectUniversity(){
        univ_spin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                universityContent = new UniversityContent();
//                universityContent.university_name =
            }
        });
    }

    private void init(){
        univ_spin = (Spinner) findViewById(R.id.spinner);
        facu_spin = (Spinner) findViewById(R.id.select_major_spinner);
        depart_spin = (Spinner) findViewById(R.id.select_sub_spinner);

        nextbutton = (Button) findViewById(R.id.next_button5);
    }

    public void universityContentRequest(){
        try{
            select_univ_Request.execute(new RequestHandler() {
                @Override
                public void onRequestOK(JSONObject jsonObject) {
                    try {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        List<UniversityContent> contentList = mapArrayList(jsonArray);
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onRequestErr(int code) {

                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private List<UniversityContent> mapArrayList(JSONArray jsonArray) throws JSONException{
        List<UniversityContent> contentList = new ArrayList<>(jsonArray.length());
        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            UniversityContent content = new UniversityContent();
            content.setId(jsonObject.getInt("id"));
            content.setUniversity_name(jsonObject.getString("universityname"));
            content.setBoard(jsonObject.getInt("board"));
            contentList.add(content);
        }
        return contentList;
    }

}
