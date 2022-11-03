package com.example.myapplication;

import static android.content.Intent.ACTION_DIAL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.utils.MyUtils;
import com.squareup.picasso.Picasso;

public class GarageDetails extends AppCompatActivity {
    MyUtils myUtils;
    String TAG ="GarageDetails";
    String garageName,garagePhone,garageNamePhoto,garageNamePhoto1;
    ImageView  garageImageDisplayDetails ;
    long distance,latitude,longitude;
    TextView garageDisplayNameDetails,garageDisplayDistanceDetails,garageDisplayPhoneDetails;
    String output,numbers;
    Button callButton, googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 Bundle extras=getIntent().getExtras();
        if(extras!=null){
           garageName=extras.getString("garageName");
           Log.d("Gname",garageName);
           garagePhone=extras.getString("garagePhone");
           Log.d("Gphone",garagePhone);
           garageNamePhoto=extras.getString("garagePhoto");
           garageNamePhoto1=garageNamePhoto;
           garageName=extras.getString("garageName");
           distance=extras.getLong("distance");
           latitude=extras.getLong("latitude");
           longitude=extras.getLong("longitude");
           Log.d("latitude", String.valueOf(latitude));
           //latitude=extras.getLong("latitude");
          // Log.d("Gd", String.valueOf(distance));
        }
        setContentView(R.layout.garage_details);
        View views = findViewById(R.id.layout );
        myUtils = new MyUtils(getApplicationContext());
        int color = myUtils.getColor();
        views.setBackgroundColor(color);
        Log.i(TAG,"--onCreate--");
        output="tel:"+garagePhone;
        garageDisplayNameDetails = (TextView) findViewById(R.id.garageNameDetails);
        garageDisplayNameDetails.setText("Name : "+garageName);

        garageImageDisplayDetails=(ImageView) findViewById(R.id.imageViewDetails);
        try{
            Picasso.get()
                    .load(garageNamePhoto1)
                    .into(garageImageDisplayDetails);
        }catch (Exception e){
            e.printStackTrace();
        }
        garageDisplayPhoneDetails = (TextView) findViewById(R.id.phoneDeatails);
        garageDisplayPhoneDetails.setText("Tel : "+garagePhone);
        garageDisplayDistanceDetails = (TextView) findViewById(R.id.distanceDetails);
        garageDisplayDistanceDetails.setText("Distance : "+distance +" kms");
                callButton =(Button) findViewById(R.id.call);
        googleMap =(Button) findViewById(R.id.googleMap);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ACTION_DIAL);
               // intent.setData(Uri.parse("tel:4444"));
                intent.setData(Uri.parse(output));
                startActivity(intent);
            }
        });
        googleMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("latitude", String.valueOf(latitude));
                Log.d("ii","geo:"+latitude+","+longitude+"?z=9");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"+latitude+","+longitude+"?z=9"));
                startActivity(intent);
            }
        });
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
//                // Intent intent6=new Intent(getContext(), ChangeProfilePicture.class);
//                // startActivity(intent6);

            default:
                return super.onOptionsItemSelected(item);

        }
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
