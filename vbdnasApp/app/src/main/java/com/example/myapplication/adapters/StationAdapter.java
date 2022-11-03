package com.example.myapplication.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Garage;
import com.example.myapplication.models.Station;

import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<StationHolder> {
    private static final String TAG ="RecyclerAdapter ";
    //int count = 0;
    List<Station> stationList;
    StationHolder stationHolder;
    public StationAdapter(List<Station> stationList){
        this.stationList = stationList;
    }

    @NonNull
    @Override
    public StationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.i(TAG,"onCreateViewHolder, + count++");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.station_item, parent,false);
        stationHolder= new StationHolder(view);
        return stationHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StationHolder holder, int position) {
        holder.stationNameDisplay.setText(stationList.get(position).getName());
        holder.priceDisplay.setText(Integer.toString(stationList.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {

        return stationList.size();
    }

}
