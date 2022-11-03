package com.example.myapplication.tabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.myapplication.R;

public class PrimaryBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_bar);
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
           switch (item.getItemId()){

               case R.id.backgroundColor:
                   Intent intent=new Intent(PrimaryBar.this, PrimaryBar.class);
                   startActivity(intent);
                   return true;
               default: return super.onOptionsItemSelected(item);
    }

    }
}