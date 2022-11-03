package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.adapters.GarageAdapter;
import com.example.myapplication.models.Garage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AddAdmin extends AppCompatActivity {
    EditText firstname , secondname, username, password, email, telephone;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
    setContentView((R.layout.activity_add_admin));

         firstname = findViewById(R.id.adminFirstName);
        secondname = findViewById(R.id.adminSecondName);
        username = findViewById(R.id.adminUserName);
        password = findViewById(R.id.adminPassword);
        email = findViewById(R.id.adminEmail);
        telephone=findViewById(R.id.adminTelephone);
        add = findViewById(R.id.adminEnterButton);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             String fname = firstname.getText().toString();
             String sname = secondname.getText().toString();
             String uname = username.getText().toString();
             String pin = password.getText().toString();
             String emails = email.getText().toString();
             String tel = telephone.getText().toString();

             sendAdminToServer(fname, sname,uname,pin,emails,tel);
            }
        });
    }
    public void deleteAdminToServer(String firstname,String  lastname,String username,String password,String email,String telephone){
        String url="http://192.168.0.172/vbdnas/deleteAdmin.php";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                 Log.d("RESPONSE",response);
                 Toast.makeText(getApplicationContext(),"Save",Toast.LENGTH_LONG).show();
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
    public void sendAdminToServer(String firstname,String  lastname,String username,String password,String email,String telephone){
        String url="http://192.168.43.159/vbdnas/addAdmin.php";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RESPONSE",response);
                Toast.makeText(getApplicationContext(),"Save",Toast.LENGTH_LONG).show();
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
                params.put("firstname",firstname);
                params.put("secondname",lastname);
                params.put("username",username);
                params.put("password",password);
                params.put("email",email);
                params.put("telephone",telephone);
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