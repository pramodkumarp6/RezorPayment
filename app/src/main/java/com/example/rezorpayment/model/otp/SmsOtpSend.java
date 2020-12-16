package com.example.rezorpayment.model.otp;

public class SmsOtpSend {
    private String otp;

    public SmsOtpSend(String otp) {
        this.otp = otp;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

}
