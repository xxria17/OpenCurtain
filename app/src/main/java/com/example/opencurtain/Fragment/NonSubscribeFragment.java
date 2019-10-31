package com.example.opencurtain.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.opencurtain.Adapter.DepartAdapter;
import com.example.opencurtain.Adapter.FacultyAdapter;
import com.example.opencurtain.Adapter.UniversityAdapter;
import com.example.opencurtain.Model.DepartmentContent;
import com.example.opencurtain.Model.FacultyContent;
import com.example.opencurtain.Model.SubscribeContent;
import com.example.opencurtain.Model.UniversityContent;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class NonSubscribeFragment extends Fragment {
    private RecyclerView univ_list, facul_list, depart_list;
    List<UniversityContent> universityContentList;
    List<DepartmentContent> departmentContentList;
    List<FacultyContent> facultyContentList;

    RecyclerView.LayoutManager layoutManager1;
    RecyclerView.LayoutManager layoutManager2;
    RecyclerView.LayoutManager layoutManager3;

    private APIRequest apiRequest;
    private UniversityAdapter universityAdapter;
    private FacultyAdapter facultyAdapter;
    private DepartAdapter departAdapter;

    public NonSubscribeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_non_subscribe, container, false);

        apiRequest = APIRequest.getInstance();

        init(view);
        getUniversityList();
        getFaculty();
        getDepartment();
        return view;
    }

    private void getUniversityList(){
        try {
            apiRequest.execute(API.universitys.getEndPoint(), Method.GET, new RequestHandler() {
                @Override
                public void onRequestOK(JSONObject jsonObject) {
                    try {
                        JSONArray jsonArray = new JSONArray(jsonObject.getString("BODY"));
                        universityContentList = mapUnivArrayList(jsonArray);

                        List<UniversityContent> list = new ArrayList<>();
                        for(UniversityContent content : universityContentList){
                            UniversityContent universityContent = new UniversityContent();
                            universityContent.id = content.id;
                            universityContent.board = content.board;
                            universityContent.university_name = content.university_name;

                            list.add(universityContent);
                        }

                        universityAdapter = new UniversityAdapter(list);
                        univ_list.setAdapter(universityAdapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onRequestErr(int code) {
                    System.out.println("Error ;;;; " + code);
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void getFaculty(){
        try {
            apiRequest.execute(API.facultys.getEndPoint(), Method.GET, new RequestHandler() {
                @Override
                public void onRequestOK(JSONObject jsonObject) {
                    try {
                        JSONArray jsonArray = new JSONArray(jsonObject.getString("BODY"));
                        facultyContentList = mapFaculArrayList(jsonArray);

                        List<FacultyContent> list = new ArrayList<>();
                        for(FacultyContent content : facultyContentList){
                            FacultyContent facultyContent = new FacultyContent();
                            facultyContent.id = content.id;
                            facultyContent.board = content.board;
                            facultyContent.faculty_name = content.faculty_name;

                            list.add(facultyContent);
                        }

                        facultyAdapter = new FacultyAdapter(list);
                        facul_list.setAdapter(facultyAdapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onRequestErr(int code) {
                    System.out.println("Error ;;;; " + code);
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void getDepartment(){
        try {
            apiRequest.execute(API.departments.getEndPoint(), Method.GET, new RequestHandler() {
                @Override
                public void onRequestOK(JSONObject jsonObject) {
                    try {
                        JSONArray jsonArray = new JSONArray(jsonObject.getString("BODY"));
                        departmentContentList = mapDepartArrayList(jsonArray);

                        List<DepartmentContent> list = new ArrayList<>();
                        for(DepartmentContent content : departmentContentList){
                            DepartmentContent departmentContent = new DepartmentContent();
                            departmentContent.id = content.id;
                            departmentContent.board = content.board;
                            departmentContent.faculty = content.faculty;
                            departmentContent.department_name = content.department_name;

                            list.add(departmentContent);
                        }

                        departAdapter = new DepartAdapter(list);
                        depart_list.setAdapter(departAdapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onRequestErr(int code) {
                    System.out.println("Error ;;;; " + code);
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    private void init(View view) {
        univ_list = (RecyclerView) view.findViewById(R.id.univer_list);
        facul_list = (RecyclerView) view.findViewById(R.id.faculty_list);
        depart_list = (RecyclerView) view.findViewById(R.id.depart_list);

        layoutManager1 = new LinearLayoutManager(getActivity());
        layoutManager2 = new LinearLayoutManager(getActivity());
        layoutManager3 = new LinearLayoutManager(getActivity());
        univ_list.setLayoutManager(layoutManager1);
        facul_list.setLayoutManager(layoutManager2);
        depart_list.setLayoutManager(layoutManager3);

        univ_list.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
        facul_list.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
        depart_list.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
    }

    private List<FacultyContent> mapFaculArrayList(JSONArray jsonArray) throws JSONException {
        List<FacultyContent> contentList = new ArrayList<>(jsonArray.length());
        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            FacultyContent content = new FacultyContent();
            content.setId(jsonObject.getInt("id"));
            content.setFaculty_name(jsonObject.getString("facultyname"));
            content.setBoard(jsonObject.getInt("board"));
            content.setUniversity(jsonObject.getInt("university"));
            contentList.add(content);
        }
        return contentList;
    }

    private List<DepartmentContent> mapDepartArrayList(JSONArray jsonArray) throws JSONException {
        List<DepartmentContent> contentList = new ArrayList<>(jsonArray.length());
        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            DepartmentContent content = new DepartmentContent();
            content.setId(jsonObject.getInt("id"));
            content.setDepartment_name(jsonObject.getString("departmentname"));
            content.setBoard(jsonObject.getInt("board"));
            content.setFaculty(jsonObject.getInt("faculty"));
            content.setUniversity(jsonObject.getInt("university"));
            contentList.add(content);
        }
        return contentList;
    }

    private List<UniversityContent> mapUnivArrayList(JSONArray jsonArray) throws JSONException {
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