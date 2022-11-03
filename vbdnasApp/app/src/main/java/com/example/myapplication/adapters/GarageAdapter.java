package com.example.myapplication.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.GarageDetails;
import com.example.myapplication.R;
import com.example.myapplication.models.Garage;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.MyUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GarageAdapter extends RecyclerView.Adapter<GarageHolder> {
   private static final String TAG ="RecyclerAdapter ";
   //int count = 0;
    List<Garage> garageList;
    GarageHolder garageHolder;
    MyUtils myUtils;

    public GarageAdapter(List<Garage> garageList){
        this.garageList = garageList;
    }

    @NonNull
    @Override
    public GarageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.i(TAG,"onCreateViewHolder, + count++");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.garage_item, parent,false);
        garageHolder= new GarageHolder(view);
        return garageHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GarageHolder holder, int position) {

        String s=garageList.get(position).getName();
        String im=garageList.get(position).getPhoto();
        String imm= AppConstants.IMAGE_BASE_URL+im;
        String phone=garageList.get(position).getPhone();
        long dis=garageList.get(position).getDistance();
        long lat=garageList.get(position).getLatitude();
        long log=garageList.get(position).getLongitude();
        String d="head.jpg";
        String dh=AppConstants.IMAGE_BASE_URL+d;
        holder.garageNameDisplay.setText(s);
        holder.distanceDisplay.setText(Long.toString(garageList.get(position).getDistance())+" KM");
        try{
        Picasso.get()
                .load(imm)
                .into(holder.imageViewNew);
        }catch (Exception e){
           e.printStackTrace();
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), GarageDetails.class);
                intent.putExtra("garagePhone",phone);
                intent.putExtra("garagePhoto",imm);
                intent.putExtra("garageName",holder.garageNameDisplay.getText());
                intent.putExtra("distance",dis);
                intent.putExtra("latitude",lat);
                intent.putExtra("longitude",log);

                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return garageList.size();

    }
 public void filterList(List<Garage> filteredList){
        garageList = filteredList;
        notifyDataSetChanged();

 }
}
