package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.myapplication.utils.MyUtils;

import yuku.ambilwarna.AmbilWarnaDialog;

public class BackgroundColor extends AppCompatActivity {

    MyUtils myUtils;
    ConstraintLayout originalLayout;
    int originalColor;
    Button originalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_color);
        originalLayout = (ConstraintLayout) findViewById(R.id.layout);
        originalButton = (Button) findViewById(R.id.sendImageName);
        originalColor = ContextCompat.getColor(BackgroundColor.this, com.google.android.material.R.color.design_default_color_primary);
         myUtils = new MyUtils(getApplicationContext());
        View views = findViewById(R.id.layout );
        int color=myUtils.getColor();
        views.setBackgroundColor(color);

        originalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openColorPicker();

            }
        });
    }

    public void openColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, originalColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
               originalColor= color;
               //originalLayout.setBackgroundColor(originalColor);
                myUtils.saveColor(originalColor);
                //int s= myUtils.getColor();
               // Log.i(TAG, String.valueOf(s));
                recreate();
            }
        });
        colorPicker.show();
    }


//    }

        //By texting
//        myUtils = new MyUtils(getApplicationContext());
//        View views = findViewById(R.id.layout );
//        int color=myUtils.getColor();
//        views.setBackgroundColor(color);
//        //int b= color;
//        Button z = (Button) findViewById(R.id.sendImageName);
//        EditText ff = (EditText) findViewById(R.id.color);
//        Toast.makeText(BackgroundColor.this, "Successfully ", Toast.LENGTH_SHORT).show();
//
//        z.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Intent intent=new Intent(BackgroundColor.this, MainActivity.class);
//                int un = Integer.parseInt(ff.getText().toString());
//                myUtils.saveColor(un);
//                int s= myUtils.getColor();
//                Log.i(TAG, String.valueOf(s));
//                recreate();
//
//            }
//        });
//    }
    }

