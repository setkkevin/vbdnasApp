package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class StationDetails extends AppCompatActivity {
    String TAG ="StationDetail ";
    String stationName;
    int price;
    TextView stationViewName;
    TextView stationViewPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            stationName=extras.getString("stationName");
            Log.d("Sname",stationName);
            price=extras.getInt("price");
            Log.d("Sprice", String.valueOf(price));
        }
        setContentView(R.layout.station_details);
        Log.i(TAG,"--onCreate--");
        stationViewName = (TextView) findViewById(R.id.sdetailname);
        stationViewPrice = (TextView) findViewById(R.id.sdetailprice);
        stationViewName.setText(stationName);
        stationViewPrice.setText(price+" FRW");
       // stationViewPrice.setText(Integer.toString(price));



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
