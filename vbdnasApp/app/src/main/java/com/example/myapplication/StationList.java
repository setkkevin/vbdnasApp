package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.adapters.GarageAdapter;
import com.example.myapplication.adapters.StationAdapter;
import com.example.myapplication.models.Garage;
import com.example.myapplication.models.Station;

import java.util.ArrayList;
import java.util.List;

public class StationList extends AppCompatActivity {
    RecyclerView recyclerView;
    StationAdapter recyclerAdapter;
    List<Station> stationList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.station_list);
        //  Log.i(TAG,"--onCreate--");
        stationList = new ArrayList<Station>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerAdapter = new StationAdapter(stationList);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);
        // DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        // recyclerView.addItemDecoration(dividerItemDecoration);

        setStations();
    }
    public void setStations(){
        stationList.add(new Station("SP",676));
        stationList.add(new Station("SP",676));
        stationList.add(new Station("SP",676));
        stationList.add(new Station("SP",676));
        stationList.add(new Station("SP",676));

    }

}