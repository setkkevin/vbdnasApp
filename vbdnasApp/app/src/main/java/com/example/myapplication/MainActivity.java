package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.adapters.AdminAdapter;
import com.example.myapplication.adapters.GarageAdapter;
import com.example.myapplication.fragments.GarageListFragment;
import com.example.myapplication.models.Admin;
import com.example.myapplication.models.Garage;
import com.example.myapplication.progress_bar.ProgressBar1;
import com.example.myapplication.tabs.MainActivity2;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.MyUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String TAG ="MainActivity ";
    RecyclerView recyclerView;
    AdminAdapter recyclerAdapter;
   // GarageAdapter recyclerAdapter;
    List<Garage> garageList;
    List<Admin> adminList;

     Button btn,createButton;
     TextView textView;

     MyUtils myUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View view = findViewById(R.id.layout );
       // ImageView viewim= (ImageView) findViewById(R.id.imageView6 );

        myUtils = new MyUtils(getApplicationContext());
       int color=myUtils.getColor();
        //int b= color;
        view.setBackgroundColor(color);
        String username= myUtils.getUsername();
        String image = myUtils.getBgImage() ;
       // view.setBackground(getResources().getDrawable(R.drawable.image));
       // view.setBackground(image);
        Log.i(TAG,"--onCreate--");
        String l= myUtils.getBgImage();

       // Toast.makeText(MainActivity.this, "Successfully ", Toast.LENGTH_SHORT).show();
        textView =(TextView) findViewById(R.id.adminNameView);
        btn=(Button) findViewById(R.id.button2);
        createButton=(Button) findViewById(R.id.creatNewAccount);
        if(!username.equals("")) {
            loadImage(username);
         }
       // displayImage(myUtils.getBgImage(), viewim);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Successfully ", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this, Login.class);
                startActivity(intent);

            }
        }
        );
        createButton.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       Intent intent=new Intent(MainActivity.this, AccountCreation.class);
                                       startActivity(intent);
                                   }
                               }
        );

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
    public void onBackPressed() {
       super.onBackPressed();
        finish();
    }

    public void loadImage(String username){
        String url=AppConstants.BASE_URL+"getImageById.php";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //iyo byaciyemo
                Log.d("DATA",response);
                myUtils.saveBgImage(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("fail",error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params=new HashMap<String,String>();
                params.put("username",username);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 0;
            }

            @Override
            public int getCurrentRetryCount() {
                return 0;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue.add(stringRequest);
    }
public void displayImage(String image,ImageView viewim){
    Picasso.get()
            .load(image)
            .into(viewim);

}



}