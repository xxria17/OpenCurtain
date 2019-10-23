package com.example.opencurtain.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.example.opencurtain.Fragment.TotalPostFragment;
import com.example.opencurtain.R;

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


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (item.getItemId()){
            case R.id.univ_item:
                transaction.replace(R.id.frame_layout,totalPostFragment).commit();
                break;
        }
        return true;
    }
}
