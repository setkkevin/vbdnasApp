package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.example.myapplication.tabs.MainActivity2;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.MyUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Login extends AppCompatActivity  {

    private SharedPreferences pPreferences;
    private SharedPreferences.Editor pEditor;
    private EditText pEditUsername,pPassword;
    private Button submit;
    private CheckBox pCheckBox;
    String username;
    MyUtils myUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_login2));

        pEditUsername = findViewById(R.id.username);

        pPassword = findViewById(R.id.password);
        submit = findViewById(R.id.submit);
        pCheckBox = findViewById(R.id.checkBox);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pCheckBox.isChecked()){
                    // Toast.makeText(Login.this, "Successfully ", Toast.LENGTH_SHORT).show();
                    String un= pEditUsername.getText().toString();
                    String ps= pPassword.getText().toString();
                    // myUtils.saveUser(un);
                    login(un,ps);
                  myUtils.saveUser(un);


                }else{
                    // Toast.makeText(Login.this, "Successfully ", Toast.LENGTH_SHORT).show();
                    String un= pEditUsername.getText().toString();
                    String ps= pPassword.getText().toString();
                    // myUtils.saveUser(un);
                    login(un,ps);

                }

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        myUtils = new MyUtils(getApplicationContext());
        String s = myUtils.getUsername();
        pEditUsername.setText(s);
        if (!s.equalsIgnoreCase("")) {
            Intent intent = new Intent(Login.this, MainActivity2.class);
            startActivity(intent);
        }
    }
    public void deleteAdminToServer(String firstname, String  lastname, String username, String password, String email, String telephone){
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
    public void login(String username,String  password){
        String url= AppConstants.BASE_URL+"login.php";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.d("RESPONSE",response);
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                if(response.equalsIgnoreCase("go")){
                    myUtils.saveUser(username);
                    Intent intent=new Intent(Login.this, MainActivity2.class);
                    startActivity(intent);
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
                params.put("username",username);
                params.put("password",password);
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
    public Login(){

    }
}