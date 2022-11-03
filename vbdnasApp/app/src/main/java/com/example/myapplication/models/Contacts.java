package com.example.myapplication.models;

import java.util.ArrayList;
import java.util.Date;

public class Contacts
{
    public static ArrayList<Contacts> contactsArrayList= new ArrayList<>();

    private int id;
    private String name;
    private String number;
    private Date deleted;


    public Contacts(int id, String name, String number, Date deleted) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.deleted = deleted;
    }

    public Contacts(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
        deleted = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }




}
