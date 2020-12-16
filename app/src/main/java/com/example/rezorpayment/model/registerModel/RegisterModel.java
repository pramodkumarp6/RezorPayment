package com.example.rezorpayment.model.registerModel;

import android.os.Parcel;
import android.os.Parcelable;


public class RegisterModel {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String mobile;
    private String gender;
    private String country;
    private String state;
    private String city;
    private String address;


    public RegisterModel(String name, String lastname, String email, String password, String mobile, String gender, String country, String state, String city, String address) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.gender = gender;
        this.country = country;
        this.state = state;
        this.city = city;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}