package com.example.rezorpayment.model.statemodel;

public class StateSender {
    private String country_id;

    public StateSender(String country_id) {
        this.country_id = country_id;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }
}
