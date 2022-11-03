package com.example.myapplication.tabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.AccountCreation;
import com.example.myapplication.BackgroundColor;
import com.example.myapplication.BackgroundImage;
import com.example.myapplication.ChangePassword;
import com.example.myapplication.ChangeUsername;
import com.example.myapplication.Login;
import com.example.myapplication.MainActivity;
import com.example.myapplication.ProfileCreation;
import com.example.myapplication.R;
import com.example.myapplication.fragments.GarageListFragment;
import com.example.myapplication.fragments.Home;
import com.example.myapplication.utils.MyUtils;
import com.google.android.material.tabs.TabLayout;

public class MainActivity2 extends AppCompatActivity {
       private TabLayout tabLayout;
       private ViewPager  viewPager;
       MyUtils myUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myUtils = new MyUtils(getApplicationContext());
        View views = findViewById(R.id.layout );
        int color=myUtils.getColor();
        views.setBackgroundColor(color);


        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("HOME"));
        tabLayout.addTab(tabLayout.newTab().setText("GARAGES"));
    //    tabLayout.addTab(tabLayout.newTab().setText("GOOGLE Map"));


        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT ) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                      Home home = new Home();
                      Log.d("Tab1","shown");
                      return home;
                    case 1:
                        GarageListFragment garages = new GarageListFragment();
                        return garages;

                        default:
                        return null;
                }

            }

            @Override
            public int getCount() {
                return tabLayout.getTabCount();
            }
        });
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

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.creatProfilePicture:
                Intent intent = new Intent(getApplicationContext(), ProfileCreation.class);
                startActivity(intent);
                super.onBackPressed();
                return true;
            case R.id.newAccount:
                Intent intent2 = new Intent(getApplicationContext(), AccountCreation.class);
                startActivity(intent2);
                super.onBackPressed();
                return true;
            case R.id.logout:
                myUtils = new MyUtils(getApplicationContext());
                //myUtils = new MyUtils(getContext());
                myUtils.logout();
                super.onBackPressed();
                return true;
            case R.id.backgroundColor:
                Toast.makeText(getApplicationContext(), "Here", Toast.LENGTH_LONG).show();
                Log.d("CLICK", "CLICKED");
                Intent intent3 = new Intent(getApplicationContext(), BackgroundColor.class);
                startActivity(intent3);
                super.onBackPressed();
                return true;
            case R.id.changePassword:
                Intent intent4 = new Intent(getApplicationContext(), ChangePassword.class);
                startActivity(intent4);
                super.onBackPressed();
                return true;
            case R.id.changeUsername:
                Intent intent5 = new Intent(getApplicationContext(), ChangeUsername.class);
                startActivity(intent5);
                super.onBackPressed();
                return true;
            case R.id.backgroundImage:
                Intent intent6 = new Intent(getApplicationContext(), BackgroundImage.class);
                startActivity(intent6);
                super.onBackPressed();
                return true;

//            case R.id.changeProfilePicture:
//                 Intent intent6=new Intent(getContext(), ChangeProfilePicture.class);
//                 startActivity(intent6);

            default:
                return super.onOptionsItemSelected(item);

        }
    }


}
