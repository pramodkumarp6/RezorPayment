package com.example.rezorpayment.services;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.example.rezorpayment.app.RetrofitClient;
import com.example.rezorpayment.model.otp.RegisterResopnse;
import com.example.rezorpayment.model.otp.SmsOtpSend;
import com.example.rezorpayment.model.registerModel.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HttpService extends IntentService {
    private    SmsOtpSend smsOtpSend ;
    private static String TAG = HttpService.class.getSimpleName();

    public HttpService() {
        super(HttpService.class.getSimpleName());
    
    
    }
    
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null) {
            String otp = intent.getStringExtra("otp");
             smsOtpSend = new SmsOtpSend(otp);
            verifyOtp();
        }

    }



    private void verifyOtp() {
       Call<RegisterResopnse> call= RetrofitClient.getInstance().getApi().smsOtp_verify(smsOtpSend);

       call.enqueue(new Callback<RegisterResopnse>() {
           @Override
           public void onResponse(Call<RegisterResopnse> call, Response<RegisterResopnse> response) {

           }

           @Override
           public void onFailure(Call<RegisterResopnse> call, Throwable t) {

           }
       });

    }
}
