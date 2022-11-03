package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.utils.MyUtils;
import com.squareup.picasso.Picasso;


public class Logout extends AppCompatActivity {
    Login login;
    ImageView imageView;
    MyUtils myUtils;
    String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            MyUtils myUtils=new MyUtils(getApplicationContext());
            username=myUtils.getUsername();
            setContentView(R.layout.activity_main);


    }
}