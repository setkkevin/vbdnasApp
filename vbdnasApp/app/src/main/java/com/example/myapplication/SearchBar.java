package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class SearchBar extends AppCompatActivity {

    ListView listView;
    String[] name = {"seth","gashujyi","kevin","ryaguranya","skantazah"};

      ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);
        listView = findViewById(R.id.searchList);

        arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,name);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)  menuItem.getActionView();
        searchView.setQueryHint("Type here");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {


                arrayAdapter.getFilter().filter(s);
                return false;

            }
        });



        return super.onCreateOptionsMenu(menu);
    }
}