package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.models.Contacts;

public class ContactsDetails extends AppCompatActivity {

    Button saveContact;
    private EditText nameEditText,numberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_details);


            nameEditText = findViewById(R.id. nameEditText);
            numberEditText = findViewById(R.id.numberEditText);


        saveContact = (Button) findViewById(R.id.saveContact);

        saveContact.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                String name= String.valueOf(nameEditText.getText());
                                                String number= String.valueOf(numberEditText.getText());

                                                int id = Contacts.contactsArrayList.size();
                                                Contacts newContacts = new Contacts(id,name,number);
                                                Contacts.contactsArrayList.add(newContacts);
                                                finish();
                                            }
                                        }
        );
    }



//
//    public void saveContact(View view){
//        String name= String.valueOf(nameEditText.getText());
//        String number= String.valueOf(numberEditText.getText());
//
//        int id = Contacts.contactsArrayList.size();
//        Contacts newContacts = new Contacts(id,name,number);
//        Contacts.contactsArrayList.add(newContacts);
//        finish();
//    }
}