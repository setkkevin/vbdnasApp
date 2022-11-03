package com.example.myapplication.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Admin;

import java.util.List;

public class AdminAdapter extends RecyclerView.Adapter<AdminHolder> {
    private static final String TAG ="RecyclerAdapter ";
    //int count = 0;
    List<Admin> adminList;
    AdminHolder adminHolder;
    public AdminAdapter(List<Admin> adminList){
        this.adminList = adminList;
    }

    @NonNull
    @Override
    public AdminHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.i(TAG,"onCreateViewHolder, + count++");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.admin_item, parent,false);
        adminHolder= new AdminHolder(view);
        return adminHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminHolder holder, int position) {
        holder.adminNameDisplay.setText(adminList.get(position).getName());
        //holder.priceDisplay.setText(Integer.toString(adminList.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {

        return adminList.size();
    }

}
