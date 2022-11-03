package com.example.myapplication.adapters;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.StationDetails;

class StationHolder extends RecyclerView.ViewHolder {

    TextView stationNameDisplay;
    TextView priceDisplay;
    public StationHolder(@NonNull View itemView) {
        super(itemView);

        stationNameDisplay = itemView.findViewById(R.id.sname);
        // textView = itemView.findViewById(R.id.textView4);
        priceDisplay = itemView.findViewById(R.id.sprice);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StationDetails.class);
                intent.putExtra("stationName",stationNameDisplay.getText());
                intent.putExtra("stationName",priceDisplay.getText());
                //intent.putExtra("distance",garageNameDisplay.getText());
                v.getContext().startActivity(intent);
            }
        });
    }



}