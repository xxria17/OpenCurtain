package com.example.opencurtain.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.opencurtain.Adapter.SidemenuAdapter;
import com.example.opencurtain.Fragment.BoardFragment;
import com.example.opencurtain.Fragment.FacultyPostFragment;
import com.example.opencurtain.Fragment.MajorPostFragment;
import com.example.opencurtain.Fragment.MyPageFragment;
import com.example.opencurtain.Fragment.SearchFragment;
import com.example.opencurtain.Fragment.SettingFragment;
import com.example.opencurtain.Fragment.TotalPostFragment;
import com.example.opencurtain.Fragment.UniversityPostFragment;
import com.example.opencurtain.Model.SubscribeContent;
import com.example.opencurtain.Network.API;
import com.example.opencurtain.Network.APIRequest;
import com.example.opencurtain.Network.Method;
import com.example.opencurtain.Network.RequestHandler;
import com.example.opencurtain.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TotalPostFragment totalPostFragment = new TotalPostFragment();

    private SettingFragment settingFragment = new SettingFragment();
    private MyPageFragment myPageFragment = new MyPageFragment();
    private SearchFragment searchFragment = new SearchFragment();

    private BoardFragment boardFragment = new BoardFragment();

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private RecyclerView listView;
    ImageView imageView, addPost;
    TextView toolbartv;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    Toolbar toolbar;
    private APIRequest apiRequest;
    private SidemenuAdapter sidemenuAdapter;

    List<SubscribeContent> subscribeContentList;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        load_menu();


        apiRequest = APIRequest.getInstance();

        listView.addOnItemTouchListener(new TotalPostFragment.RecyclerTouchListener(this, listView, new TotalPostFragment.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                drawerLayout.closeDrawers();

//                Intent intent = new Intent(MainActivity.this, BoardFragment.class);
//                intent.putExtra("id",subscribeContentList.get(position).id);
//                startActivity(intent);

                Bundle bundle = new Bundle(1);

                int id = subscribeContentList.get(position).board;
                bundle.putInt("id",id);
                boardFragment.setArguments(bundle);

                transaction.replace(R.id.frameLayout, boardFragment).commit();
                toolbartv.setText(subscribeContentList.get(position).boardname);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPostActivity.class);
                startActivity(intent);
            }
        });


//        //바텀 네비게이션 메뉴
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, totalPostFragment).commitAllowingStateLoss();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (menuItem.getItemId()) {
                    case R.id.home: {
                        transaction.replace(R.id.frameLayout, totalPostFragment).commitAllowingStateLoss();
                        toolbartv.setText("전체");
                        break;
                    }
                    case R.id.search: {
                        transaction.replace(R.id.frameLayout, searchFragment).commitAllowingStateLoss();
                        toolbartv.setText("검색");
                        break;
                    }
                    case R.id.mypage: {
                        transaction.replace(R.id.frameLayout, myPageFragment).commitAllowingStateLoss();
                        toolbartv.setText("내 글 보기");
                        break;
                    }
                    case R.id.setting: {
                        transaction.replace(R.id.frameLayout, settingFragment).commitAllowingStateLoss();
                        toolbartv.setText("설정");
                        break;
                    }
                }

                return true;
            }
        });

    }

    private void init(){
        imageView = findViewById(R.id.drawerMenuButton);
        drawerLayout = findViewById(R.id.drawer_menu);
//        navigationView = findViewById(R.id.navigationView);
        toolbartv = findViewById(R.id.toolbar_textview);
        addPost = findViewById(R.id.postwrite_button);
        listView = findViewById(R.id.sidemenu_list);

        layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);
        listView.addItemDecoration(new DividerItemDecoration(this,1));
    }

    private void load_menu(){

        apiRequest = APIRequest.getInstance();

        try {
            apiRequest.execute(API.subscribes.getEndPoint(), Method.GET, new RequestHandler() {
                @Override
                public void onRequestOK(JSONObject jsonObject) {
                    try {
                        JSONArray jsonArray = new JSONArray(jsonObject.getString("BODY"));
                        subscribeContentList = mapSubsArrayList(jsonArray);
                        List<SubscribeContent> list = new ArrayList<>();

                        for(SubscribeContent content : subscribeContentList){
                            SubscribeContent subscribeContent = new SubscribeContent();
                            subscribeContent.id = content.id;
                            subscribeContent.board = content.board;
                            subscribeContent.boardname = content.boardname;

                            list.add(subscribeContent);
                        }
                        sidemenuAdapter = new SidemenuAdapter(list);
                        listView.setAdapter(sidemenuAdapter);
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

    private List<SubscribeContent> mapSubsArrayList(JSONArray jsonArray) throws JSONException{
        List<SubscribeContent> contentList = new ArrayList<>(jsonArray.length());
        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            SubscribeContent content = new SubscribeContent();
            content.setId(jsonObject.getInt("id"));
            content.setBoardname(jsonObject.getString("boardname"));
            content.setBoard(jsonObject.getInt("board"));
            contentList.add(content);
        }
        return contentList;
    }

}

