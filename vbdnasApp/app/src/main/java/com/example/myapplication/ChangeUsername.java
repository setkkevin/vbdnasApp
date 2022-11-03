package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.MyUtils;

import java.util.HashMap;
import java.util.Map;

public class ChangeUsername extends AppCompatActivity {
    MyUtils myUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_username);

        EditText uc = (EditText) findViewById(R.id.uChange);
        Button usb = (Button) findViewById(R.id.uSubmit);

        usb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                myUtils = new MyUtils(getApplicationContext());
                String s = myUtils.getUsername();
                String newUsername= (uc.getText().toString());
                changeUsername(s,newUsername);
            }
        });

    }

    public void changeUsername(String username, String newUsername) {
        String url = AppConstants.BASE_URL + "changeUsername.php";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RESPONSE", response);
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                if (response.equalsIgnoreCase("Successfully")) {
                   // Toast.makeText(ChangePassword.this, "Successfully ", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Try another", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("fail", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("newUsername",newUsername);
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

    public ChangeUsername() {

    }
}
