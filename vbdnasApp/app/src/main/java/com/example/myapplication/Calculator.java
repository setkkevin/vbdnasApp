package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.adapters.AdminAdapter;
import com.example.myapplication.models.Admin;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Calculator extends AppCompatActivity {

    String TAG = "Calculator ";
    Button btn;
    EditText editText1;
    EditText editText2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);


        editText1 = (EditText) findViewById(R.id.n1);
        editText2 = (EditText) findViewById(R.id.n2);
        textView= (TextView) findViewById(R.id.sum);
        btn = (Button) findViewById(R.id.add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n1=Integer.parseInt(editText1.getText().toString());
                int n2=Integer.parseInt(editText2.getText().toString());
                int sum= getSum(n1,n2);
                textView.setText(String.valueOf(sum));
            }
        });
    }
    public int getSum(int a, int b){
        int sum= a+b;
        return  sum;
    }
}