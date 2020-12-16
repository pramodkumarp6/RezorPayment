package com.example.rezorpayment.model.countrymodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CountryResponse {
    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("countries")
    @Expose
    private List<Countries> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Countries> getData() {
        return data;
    }

    public void setData(List<Countries> data) {
        this.data = data;
    }
}
