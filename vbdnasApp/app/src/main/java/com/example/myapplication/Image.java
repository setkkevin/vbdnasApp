package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.myapplication.utils.MyUtils;
import com.squareup.picasso.Picasso;

public class Image extends AppCompatActivity {
    MyUtils myUtils;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        myUtils = new MyUtils(getApplicationContext());
        //int color=myUtils.getColor();
        imageView = (ImageView) findViewById(R.id.imageView);
        String g="bigreakdow";
        //Bitmap bt =bigreakdow;
        imageView.setImageResource(R.drawable.bigreakdow);

//String s="https://lokercirebon.com/wp-content/uploads/2017/04/Grage-grop-cirebon.png";
//        //imageView.setImageResource(R.drawable.bigreakdow);
//        Picasso.get()
//                .load(s)
//                .into(imageView);


    }
}