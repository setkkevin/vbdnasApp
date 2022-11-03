package com.example.myapplication;

import static android.content.Intent.ACTION_DIAL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Call extends AppCompatActivity {

     Button callButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
         callButton =(Button) findViewById(R.id.call);
         callButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(ACTION_DIAL);
                 intent.setData(Uri.parse("tel:4444"));
                 startActivity(intent);
             }
         });

    }
}