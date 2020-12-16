package com.example.rezorpayment.app;

import com.example.rezorpayment.model.LoginModel;
import com.example.rezorpayment.model.LoginResult;
import com.example.rezorpayment.model.User;
import com.example.rezorpayment.model.citymodel.CityResponse;
import com.example.rezorpayment.model.countrymodel.CountryResponse;
import com.example.rezorpayment.model.gendermodel.GenderResponse;
import com.example.rezorpayment.model.googlecapcha.GoogleResultCapcha;
import com.example.rezorpayment.model.otp.RegisterResopnse;
import com.example.rezorpayment.model.otp.SmsOtpSend;
import com.example.rezorpayment.model.password.UserPasswordResponse;
import com.example.rezorpayment.model.password.UserPasswordSender;
import com.example.rezorpayment.model.registerModel.RegisterModel;
import com.example.rezorpayment.model.registerModel.Result;
import com.example.rezorpayment.model.statemodel.StateResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {


    @POST("api/public/login")
    Call<LoginResult>userLogin(@Body LoginModel loginModel);

    @POST("api/public/register")
    Call<Result>createUser(@Body RegisterModel register);


    @POST("api/public/forget")
    Call<UserPasswordResponse>userforget(@Body UserPasswordSender userPasswordSender);




    @FormUrlEncoded
    @POST("https://www.google.com/recaptcha/api/siteverify")
    Call<GoogleResultCapcha> capchaTest(@Field("SITE_SECRET_KEY")String SITE_SECRET_KEY,
                                        @Field("userResponseToken") String userResponseToken);



    @POST("")
    Call<RegisterResopnse>smsOtp_verify(@Body SmsOtpSend smsOtpSend);


    @GET("api/public/gender")
    Call<GenderResponse>gender();


    @GET("api/public/country")
    Call<CountryResponse>country();
     @FormUrlEncoded
    @POST("api/public/state")
    Call<StateResponse>state(
            @Field("country_id") String country_id);

    @FormUrlEncoded
    @POST("api/public/city")
    Call<CityResponse>city(@Field("city_id")  String city_id);








    @GET("api/public/users")
    Call<User>getUser();

}
