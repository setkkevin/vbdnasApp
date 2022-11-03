package com.example.myapplication.models;

public class Station {
    String name;
    String phone;
    int price;


    public Station(){

    }
    public Station(String name, int price) {
        this.name = name;
        this.price = price;
    }
    public Station(String name, String phone, int price) {
        this.name = name;
        this.phone = phone;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
