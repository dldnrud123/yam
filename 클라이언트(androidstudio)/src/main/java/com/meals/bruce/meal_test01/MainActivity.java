package com.meals.bruce.meal_test01;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.tsengvn.typekit.TypekitContextWrapper;



public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    SharedPreferences pref;
    BackPressQuit quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startActivity(new Intent(this, Loading.class));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Activity activity = new Activity();
        //loading Activity




        Log.d("!!!!!!!!!!!!!app:", "start!!!!!!!!!!!");

//        // Adding Toolbar to the activity
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.getDisplayOptions();
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setIcon(R.drawable.infinity);

        // Initializing the TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab1_selector));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab2_selector));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab3_selector));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Initializing ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        // Creating TabPagerAdapter adapter
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        // load ahead
        viewPager.setOffscreenPageLimit(3);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        quit = new BackPressQuit(this);


    }
    public void onBackPressed() {
        quit.onBackPressed();
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

}



