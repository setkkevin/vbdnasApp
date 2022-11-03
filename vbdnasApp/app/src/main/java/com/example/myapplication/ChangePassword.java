package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.tabs.MainActivity2;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.MyUtils;

import java.util.HashMap;
import java.util.Map;

public class ChangePassword extends AppCompatActivity {
    MyUtils myUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        EditText op=(EditText)findViewById(R.id.oldPassword);
        EditText np=(EditText)findViewById(R.id.newPassword);
        Button sb =(Button) findViewById(R.id.submitPassword);

        sb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                myUtils = new MyUtils(getApplicationContext());
                String s = myUtils.getUsername();
                String oldPin =(op.getText().toString()) ;
                String newPin =(np.getText().toString());
         changePassword(s,oldPin,newPin);
          }
        });

    }

    public void changePassword(String username,String  oldPassword,String newPassword){
        String url= AppConstants.BASE_URL+"changePassword.php";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RESPONSE",response);
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                if(response.equalsIgnoreCase("Successfully")){
                    Toast.makeText(ChangePassword.this, "Successfully ", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getApplicationContext(), "Username was taken", Toast.LENGTH_LONG).show();
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
                params.put("oldPassword",oldPassword);
                params.put("newPassword",newPassword);
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
    public ChangePassword(){

    }
}