package com.example.opencurtain.SignupActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

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

public class SelectActivity extends AppCompatActivity {


    private static final String TAG = SelectActivity.class.getSimpleName();

    private APIRequest apiRequest;
    private UserContent userContent;
    private UniversityContent universityContent;
    private DepartmentContent departmentContent;
    private FacultyContent facultyContent;
    private Spinner univ_spin, facu_spin, depart_spin;
    private Button nextbutton;

    private int a , b, c;
    ArrayList<String> item;

    ArrayList<UserContent> arrayList;

    ArrayAdapter<String> universityArrayAdapter;
    List<UniversityContent> universityContents;

    ArrayAdapter<String> facultyArrayAdapter;
    List<FacultyContent> facultyContents;

    ArrayAdapter<String> departmentArrayAdapter;
    List<DepartmentContent> departmentContents;

    SpinnerAdapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Intent intent = getIntent();
        userContent = new UserContent();
        userContent.email = intent.getExtras().getString("email");
        userContent.user_name = intent.getExtras().getString("username");
        userContent.password = intent.getExtras().getString("password");

        arrayList = new ArrayList<>();

        init();
        apiRequest = APIRequest.getInstance();


//            select_univ_Request = new APIRequest(API.universitys, Method.GET);
//            select_facul_Request = new APIRequest(API.facultys, Method.GET);
//            select_depart_Request = new APIRequest(API.departments, Method.GET);

//            joinRequest = new APIRequest(API.users, Method.POST);

        getUniversity();

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                join();
            }
        });
    }

    private void getUniversity(){
        universityContentRequest();
    }

    private void init(){
        univ_spin = (Spinner) findViewById(R.id.spinner);
        facu_spin = (Spinner) findViewById(R.id.select_major_spinner);
        depart_spin = (Spinner) findViewById(R.id.select_sub_spinner);

        nextbutton = (Button) findViewById(R.id.next_button5);
    }

    public void universityContentRequest(){
        try{
            // 대학 선택
            apiRequest.execute(API.universitys.getEndPoint(), Method.GET, new RequestHandler() {
                @Override
                public void onRequestOK(JSONObject jsonObject) {
                    try {
                        JSONArray jsonArray = new JSONArray(jsonObject.getString("BODY"));
                        universityContents = mapUniversityArrayList(jsonArray);
                        //* 출력을 위한 문자열 리스트 생성 완료
                        List<String> item = new ArrayList<>(universityContents.size());
                        for(UniversityContent content : universityContents){
                            item.add(content.university_name);
                        }
                        //* 스피너 등록하기
                        universityArrayAdapter = new ArrayAdapter<String>(SelectActivity.this, android.R.layout.simple_spinner_item, item);
                        universityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        univ_spin.setAdapter(universityArrayAdapter);
                        //* 선택시 이벤트 핸들러 등록하기.
                        univ_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Log.i("SelectActivity", String.format("select pos = %d, id = %d", position, id));
                                UniversityContent content = universityContents.get(position);
//                                userContent.university = content.getId();
                                b = content.getId();
//                                arrayList.add(content);

                                //*단과대 선택
//                               select_facul_Request.setUrl(API.facultys.appendString(String.valueOf(b)));
                                try {
                                    apiRequest.execute(API.facultys.getEndPoint() + b, Method.GET, new RequestHandler() {
                                        @Override
                                        public void onRequestOK(JSONObject jsonObject) {
                                            try {
                                                JSONArray jsonArray = new JSONArray(jsonObject.getString("BODY"));
                                                facultyContents = mapFacultyArrayList(jsonArray);
                                                //* 출력을 위한 문자열 리스트 생성 완료
                                                List<String> item = new ArrayList<>(facultyContents.size());
                                                for(FacultyContent content : facultyContents){
                                                    item.add(content.faculty_name);
                                                }
                                                //* 스피너 등록하기
                                                facultyArrayAdapter = new ArrayAdapter<String>(SelectActivity.this, android.R.layout.simple_spinner_item, item);
                                                facultyArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                facu_spin.setAdapter(facultyArrayAdapter);
                                                //* 선택시 이벤트 핸들러 등록하기.
                                                facu_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Log.i("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", String.format("select pos = %d, id = %d", position, id));
                                                        FacultyContent facultyContent = facultyContents.get(position);
    //                                                    userContent.faculty = facultyContent.getId();
                                                        c = facultyContent.getId();
                                                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + userContent.faculty);

                                                        //*학과 선택
    //                                                    apiRequest.setUrl(API.departments.appendString(String.valueOf(c)));
    //                                                    select_depart_Request.setUrl(API.departments.appendString(String.valueOf(c)));
                                                        try {
                                                            apiRequest.execute(API.departments.getEndPoint() + c, Method.GET, new RequestHandler() {
                                                                @Override
                                                                public void onRequestOK(JSONObject jsonObject) {
                                                                    try {
                                                                        JSONArray jsonArray = new JSONArray(jsonObject.getString("BODY"));
                                                                        departmentContents = mapDepartmentArrayList(jsonArray);

                                                                        //* 출력을 위한 문자열 리스트 생성 완료
                                                                        List<String> item = new ArrayList<>(departmentContents.size());
                                                                        for(DepartmentContent content : departmentContents){
                                                                            item.add(content.department_name);
                                                                        }
                                                                        //* 스피너 등록하기
                                                                        departmentArrayAdapter = new ArrayAdapter<String>(SelectActivity.this, android.R.layout.simple_spinner_item, item);
                                                                        departmentArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                                        depart_spin.setAdapter(departmentArrayAdapter);
                                                                        //* 선택시 이벤트 핸들러 등록하기.
                                                                        depart_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                            @Override
                                                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                                DepartmentContent departmentContent = departmentContents.get(position);
        //                                                                        userContent.department = departmentContent.getId();
                                                                                    a= departmentContent.getId();
                                                                            }

                                                                            @Override
                                                                            public void onNothingSelected(AdapterView<?> parent) {

                                                                            }
                                                                        });


                                                                    } catch (JSONException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                }

                                                                @Override
                                                                public void onRequestErr(int code) {

                                                                }
                                                            });
                                                        } catch (MalformedURLException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });


                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onRequestErr(int code) {

                                        }
                                    });
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
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

    private void join(){

        Intent intent = getIntent();
        userContent = new UserContent();
        userContent.email = intent.getExtras().getString("email");
        userContent.password = intent.getExtras().getString("password");
        userContent.authcode = intent.getExtras().getInt("authcode");
        userContent.user_name = intent.getExtras().getString("username");


        nextbutton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SelectActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("가입 등록중...");
        progressDialog.show();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        JSONObject join = new JSONObject();
                        try {
                            join.put("username",userContent.user_name);
                            join.put("password",userContent.password);
                            join.put("email",userContent.email);
//                            join.put("university",userContent.university);
                            join.put("university",b);
//                            join.put("faculty",userContent.faculty);
                            join.put("faculty",c);
//                            join.put("department",userContent.department);
                            join.put("department",a);
                            join.put("authcode",userContent.authcode);
                            Log.d(TAG, "run: " + a + "/" + b + "/" + c);
                            Log.d(TAG, join.toString());
                            apiRequest.execute(API.users.getEndPoint(), Method.GET, new RequestHandler() {
                                @Override
                                public void onRequestOK(JSONObject jsonObject) {
                                    Intent intent1 = new Intent(SelectActivity.this, SignupDoneActivity.class);
                                    intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent1);
                                }

                                @Override
                                public void onRequestErr(int code) {
                                    Toast.makeText(getBaseContext(), "Sign Up Failed", Toast.LENGTH_LONG).show();
                                    Log.e("!!!!!!!!!!!!!!!!!!", String.format("HTTP Response code: %d", code));
                                    nextbutton.setEnabled(true);
                                }
                            },join);
                        } catch (JSONException e){
                            e.printStackTrace();
                        } catch (MalformedURLException me) {
                            me.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                } , 1000);
    }

    private List<UniversityContent> mapUniversityArrayList(JSONArray jsonArray) throws JSONException{
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

    private List<FacultyContent> mapFacultyArrayList(JSONArray jsonArray) throws JSONException{
        List<FacultyContent> contentList = new ArrayList<>(jsonArray.length());
        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            FacultyContent content = new FacultyContent();
            content.setId(jsonObject.getInt("id"));
            content.setFaculty_name(jsonObject.getString("facultyname"));
            content.setUniversity(jsonObject.getInt("university"));
            content.setBoard(jsonObject.getInt("board"));
            contentList.add(content);
        }
        return contentList;
    }

    private List<DepartmentContent> mapDepartmentArrayList(JSONArray jsonArray) throws JSONException{
        List<DepartmentContent> contentList = new ArrayList<>(jsonArray.length());
        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            DepartmentContent departmentContent = new DepartmentContent();
            departmentContent.setId(jsonObject.getInt("id"));
            departmentContent.setUniversity(jsonObject.getInt("university"));
            departmentContent.setBoard(jsonObject.getInt("board"));
            departmentContent.setFaculty(jsonObject.getInt("faculty"));
            departmentContent.setDepartment_name(jsonObject.getString("departmentname"));
            contentList.add(departmentContent);
        }
        return contentList;
    }
}
