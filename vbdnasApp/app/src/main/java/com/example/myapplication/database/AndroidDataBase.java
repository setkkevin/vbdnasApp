package com.example.myapplication.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.ContactsDetails;
import com.example.myapplication.R;
import com.example.myapplication.adapters.ContactsAdapter;
import com.example.myapplication.models.Contacts;

public class AndroidDataBase extends AppCompatActivity {

    Button newContact;
    private ListView contactsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_data_base);
        initWidgets();
        setContactsAdapter();
        newContact = (Button) findViewById(R.id.newContact);

        newContact.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Intent newContactsIntent = new Intent(AndroidDataBase.this, ContactsDetails.class);
                                               startActivity(newContactsIntent);
                                           }
                                       }
        );
    }


    private void initWidgets() {
        contactsListView=findViewById(R.id.contactslistView);
    }
    private void setContactsAdapter() {
        ContactsAdapter contactsAdapter= new ContactsAdapter(getApplicationContext(), Contacts.contactsArrayList);
        contactsListView.setAdapter(contactsAdapter);
    }

//    public void newContacts(View view){
//        Intent newContactsIntent = new Intent(this,ContactsDetails.class);
//        startActivity(newContactsIntent);
//
//    }
}