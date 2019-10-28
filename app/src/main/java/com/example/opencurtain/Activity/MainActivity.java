package com.example.opencurtain.Activity;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.example.opencurtain.Fragment.TotalPostFragment;
import com.example.opencurtain.R;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import Network.Request;
import Network.RequestHandler;
import Network.RequestHttpURLConnection;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TotalPostFragment totalPostFragment = new TotalPostFragment();
    private FragmentManager fragmentManager = getSupportFragmentManager();


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //바텀 네비게이션 메뉴
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, totalPostFragment).commitAllowingStateLoss();

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (menuItem.getItemId()){
                    case R.id.home:{
                        transaction.replace(R.id.frame_layout, totalPostFragment).commit();
                        break;
                    }
//                    case R.id.search:{
//                        transaction.replace(R.id.frame_layout, diaryFragment).commit();
//                        break;
//                    }
//                    case R.id.mypage:{
//                        transaction.replace(R.id.frame_layout, feelingFragment).commit();
//                        break;
//                    }
//                    case R.id.setting:{
//                        transaction.replace(R.id.frame_layout, settingFragment).commit();
//                        break;
//                    }

                }
//                return true;
            }
        });

        try{
            Request request = new Request(new URL("http","http://opencurtain.run.goorm.io",200,""),"GET");
            request.execute(new RequestHandler() {
                @Override
                public void onRequestOK(JSONObject jsonObject) {
                    Log.i("APIRequest", jsonObject.toString());
                }

                @Override
                public void onRequestErr(int code) {
                    Log.e("APIRequest","error occur "+ code);
                }
            });
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (item.getItemId()) {
            case R.id.univ_item:
                transaction.replace(R.id.frame_layout, totalPostFragment).commit();
                break;
        }
        return true;
    }

}

