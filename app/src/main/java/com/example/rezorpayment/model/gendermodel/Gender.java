package com.example.rezorpayment.model.gendermodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gender {

    @SerializedName("gender_id")
    @Expose
    private String id;

    @SerializedName("gender_name")
    @Expose
    private String genderName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }
}
