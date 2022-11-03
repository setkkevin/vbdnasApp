package com.example.myapplication.adapters;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.AdminDetails;
import com.example.myapplication.R;
import com.example.myapplication.StationDetails;

class AdminHolder extends RecyclerView.ViewHolder {

    TextView adminNameDisplay;
    //TextView priceDisplay;
    public AdminHolder(@NonNull View itemView) {
        super(itemView);

        adminNameDisplay = itemView.findViewById(R.id.adminNameView);
        // textView = itemView.findViewById(R.id.textView4);
       // priceDisplay = itemView.findViewById(R.id.sprice);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AdminDetails.class);
                intent.putExtra("adminName",adminNameDisplay.getText());
               // intent.putExtra("adminName",priceDisplay.getText());
                //intent.putExtra("distance",garageNameDisplay.getText());
                v.getContext().startActivity(intent);
            }
        });
    }



}