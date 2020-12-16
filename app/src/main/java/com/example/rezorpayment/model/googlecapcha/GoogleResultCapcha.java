package com.example.rezorpayment.model.googlecapcha;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GoogleResultCapcha {

    private boolean sucess;

    private String challenge_ts;
    @SerializedName("error-codes")
    private ArrayList<String> errorCodes ;


    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public String getChallenge_ts() {
        return challenge_ts;
    }

    public void setChallenge_ts(String challenge_ts) {
        this.challenge_ts = challenge_ts;
    }

    public ArrayList<String> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(ArrayList<String> errorCodes) {
        this.errorCodes = errorCodes;
    }
}
