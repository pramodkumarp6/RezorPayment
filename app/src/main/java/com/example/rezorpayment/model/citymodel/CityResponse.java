package com.example.rezorpayment.model.citymodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("cties")
    @Expose
    private List<Cities> data = null;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Cities> getData() {
        return data;
    }

    public void setData(List<Cities> data) {
        this.data = data;
    }
}
