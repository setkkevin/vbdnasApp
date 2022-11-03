package com.example.myapplication.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;

import com.example.myapplication.MainActivity;

import java.io.ByteArrayOutputStream;

public class MyUtils{
    Context context;
    public MyUtils(){
    }
    public MyUtils(Context context){

        this.context=context;
    }
    public void saveUser(String username){
      SharedPreferences sharedpreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedpreferences.edit();
      editor.putString("username", username);
      editor.commit();

    }
    public void saveBgImage(String bImage){
        SharedPreferences sharedpreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("bImage", AppConstants.IMAGE_BASE_URL+bImage);
        editor.commit();

    }
    public String getBgImage(){
        SharedPreferences pref = context.getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        String username=pref.getString("bImage",AppConstants.IMAGE_BASE_URL+"head.jpg");
        return username;
    }
    public void saveColor(int backImage){
        SharedPreferences sharedpreferences = context.getSharedPreferences("bMyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("color", backImage);
        editor.commit();
    }

    public String getUsername(){
        SharedPreferences pref = context.getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        String username=pref.getString("username","");
        return username;
    }

    public int getColor(){
        SharedPreferences bpref = context.getSharedPreferences("bMyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = bpref.edit();
        int backImage = bpref.getInt("color",Color.TRANSPARENT);
        return backImage;
    }
    public void saveImage(Bitmap image){
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        SharedPreferences sharedpreferences = context.getSharedPreferences("iMyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("image", imageEncoded);
        editor.commit();
    }
    public String getImage1(){
        SharedPreferences ipref = context.getSharedPreferences("iMyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = ipref.edit();
        String uploadImage = ipref.getString("image","");
        return uploadImage;
    }

    public  Bitmap getImage2(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }
    public  void logout(){
        SharedPreferences sharedpreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("username", "");
        editor.commit();
        Intent intent=new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public  int getColor(int colorNumber){

       return colorNumber;
    }

}
