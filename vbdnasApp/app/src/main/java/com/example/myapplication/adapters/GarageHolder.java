package com.example.myapplication.adapters;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.GarageDetails;
import com.example.myapplication.R;

class GarageHolder extends RecyclerView.ViewHolder {
    ImageView imageViewNew;
    ConstraintLayout container;

    TextView garageNameDisplay, distanceDisplay,garagePhoneDisplay;
    public GarageHolder(@NonNull View itemView) {
        super(itemView);

        container=itemView.findViewById(R.id.container);
        imageViewNew = itemView.findViewById(R.id.imageView2);
        garageNameDisplay = itemView.findViewById(R.id.garageNamey);
        distanceDisplay = itemView.findViewById(R.id.distance);
        //garagePhoneDisplay

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), GarageDetails.class);
//                intent.putExtra("garageName",garageNameDisplay.getText());
//                //intent.putExtra("garagePhone",garagePhoneDisplay.getText());
//                v.getContext().startActivity(intent);
//               // String s= garageNameDisplay.getText();
//            }
//        });
    }



}