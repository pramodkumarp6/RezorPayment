package com.example.rezorpayment.model.statemodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StateResponse {
    private Integer status;
    @SerializedName("states")
    private List<States> data = null;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        status = status;
    }

    public List<States> getData() {
        return data;
    }

    public void setData(List<States> data) {
        this.data = data;
    }
}
