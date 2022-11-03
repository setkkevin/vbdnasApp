package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.myapplication.models.Admin;
import com.example.myapplication.models.Garage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminList extends AppCompatActivity {
    RecyclerView recyclerView;
    AdminAdapter recyclerAdapter;
    List<Admin> adminList;
    String dis,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);
        adminList = new ArrayList<Admin>();
        recyclerView = findViewById(R.id.recyclerView);
    }
    @Override
    protected void onStart() {
        super.onStart();
        //getAdminsFromServer();
        checkAdminsFromServer();

    }

    public void getAdminsFromServer(){
        String url="http://192.168.0.172/vbdnas/getAdmins.php";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //iyo byaciyemo
                Log.d("DATA",response);
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("admins");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject garageObject=jsonArray.getJSONObject(i);
                        String id=garageObject.getString("id");
                        String adminName=garageObject.getString("firstname");
                        //String telephone=garageObject.getString("telephone");
                        // int distance=garageObject.getInt("distance");
                        adminList.add(new Admin(adminName));
                    }
                    recyclerAdapter = new AdminAdapter(adminList);
                    recyclerView.setAdapter(recyclerAdapter);
                }catch (Exception e){
                    e.printStackTrace();
                }
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
                //params.put("id",id);
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
    //
    public void checkAdminsFromServer(){
        String url="http://192.168.43.42/vbdnas/getAdmins.php";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //iyo byaciyemo
                Log.d("DATA",response);
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("admins");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject garageObject=jsonArray.getJSONObject(i);
                         username=garageObject.getString("username");

                        if (username.equalsIgnoreCase("seth")) {

                            dis = "yyyy";
                        }
                    }
                    Toast.makeText(getApplicationContext(), "==" + dis, Toast.LENGTH_LONG).show();
                    if (dis.equalsIgnoreCase("yyyy")) {
                        Intent intent = new Intent(AdminList.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(AdminList.this, MainActivity.class);
                        startActivity(intent);
                    }
                   // Log.i(TAG, dis);
                    recyclerAdapter = new AdminAdapter(adminList);
                    recyclerView.setAdapter(recyclerAdapter);
                }catch (Exception e){
                    e.printStackTrace();
                }
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
                //params.put("id",id);
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
}