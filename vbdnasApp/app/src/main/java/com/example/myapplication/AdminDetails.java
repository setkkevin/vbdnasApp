package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class AdminDetails extends AppCompatActivity {
    String TAG ="AdminDetails ";
    String adminName;
   // long distance;
    TextView adminViewName;
    String output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            adminName=extras.getString("adminName");
            Log.d("Aname",adminName);
           // distance=extras.getLong("distance");
           // Log.d("Gname",garageName);
        }
        setContentView(R.layout.activity_admin_details);
        Log.i(TAG,"--onCreate--");
        output=adminName;
        adminViewName = (TextView) findViewById(R.id.adminNameDisplayView);
        adminViewName.setText(output);

    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG,"--onStart--");
    }
    @Override
    protected void  onRestart(){
        super.onRestart();
        Log.i(TAG,"--onRestart--");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG,"--onResume--");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG,"--onStop--");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG,"--onDestroy--");
    }

}
