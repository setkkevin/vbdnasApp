package com.example.myapplication.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.models.Contacts;

import java.util.List;

public class ContactsAdapter extends ArrayAdapter<Contacts> {

    public ContactsAdapter(Context context, List<Contacts> cnts){
        super(context, 0,cnts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contacts contacts=getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contacts_cell,parent,false);
        TextView name = convertView.findViewById(R.id.cellName);
        TextView number = convertView.findViewById(R.id.cellNumber);
        name.setText(contacts.getName());
        number.setText(contacts.getNumber());
        return  convertView;
    }
}
