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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.opencurtain.Fragment.FacultyPostFragment;
import com.example.opencurtain.Fragment.MajorPostFragment;
import com.example.opencurtain.Fragment.MyPageFragment;
import com.example.opencurtain.Fragment.SearchFragment;
import com.example.opencurtain.Fragment.SettingFragment;
import com.example.opencurtain.Fragment.TotalPostFragment;
import com.example.opencurtain.Fragment.UniversityPostFragment;
import com.example.opencurtain.R;


import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TotalPostFragment totalPostFragment = new TotalPostFragment();
    private FacultyPostFragment facultyPostFragment = new FacultyPostFragment();
    private MajorPostFragment majorPostFragment = new MajorPostFragment();
    private UniversityPostFragment universityPostFragment = new UniversityPostFragment();

    private SettingFragment settingFragment = new SettingFragment();
    private MyPageFragment myPageFragment = new MyPageFragment();
    private SearchFragment searchFragment = new SearchFragment();

    private FragmentManager fragmentManager = getSupportFragmentManager();

    ImageView imageView, addPost;
    TextView toolbartv;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.drawerMenuButton);
        drawerLayout = findViewById(R.id.drawer_menu);
        navigationView = findViewById(R.id.navigationView);
        toolbartv = findViewById(R.id.toolbar_textview);
        addPost = findViewById(R.id.postwrite_button);

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

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                drawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.total_item:
                        transaction.replace(R.id.frameLayout, totalPostFragment).commit();
                        toolbartv.setText("전체 글 보기");
                        break;

                    case R.id.univ_item:
                        transaction.replace(R.id.frameLayout, universityPostFragment).commit();
                        toolbartv.setText("제주대학교");
                        break;

                    case R.id.major_item:
                        transaction.replace(R.id.frameLayout, facultyPostFragment).commit();
                        toolbartv.setText("공과 대학");
                        break;

                    case R.id.sub_item:
                        transaction.replace(R.id.frameLayout, majorPostFragment).commit();
                        toolbartv.setText("컴퓨터공학전공");
                        break;
                }
                return true;
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
                        toolbartv.setText("전체 글 보기");
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
}

