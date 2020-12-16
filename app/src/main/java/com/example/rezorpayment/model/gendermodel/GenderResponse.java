package com.example.rezorpayment.model.gendermodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GenderResponse {
    @SerializedName("status")
    @Expose
    private Integer status;


    @SerializedName("gender")
    @Expose
    private List<Gender> data =null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Gender> getData() {
        return data;
    }

    public void setData(List<Gender> data) {
        this.data = data;
    }
}