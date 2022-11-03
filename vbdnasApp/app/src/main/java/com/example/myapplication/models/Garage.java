package com.example.myapplication.models;

public class Garage {
    String name;
    String phone;
    String photo;
    long latitude;
    long longitude;
    long distance;


    public Garage(){

    }
    public Garage(String photo){
        this.photo = photo;
    }

    public Garage(String name, String phone, long distance,String photo,long latitude, long longitude) {
        this.name = name;
        this.phone = phone;
        this.photo = photo;
        this.distance = distance;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }
}
