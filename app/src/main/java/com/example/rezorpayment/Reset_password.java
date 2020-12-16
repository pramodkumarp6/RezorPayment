package com.example.rezorpayment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rezorpayment.app.RetrofitClient;
import com.example.rezorpayment.model.LoginResult;
import com.example.rezorpayment.model.password.UserPasswordResponse;
import com.example.rezorpayment.model.password.UserPasswordSender;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Reset_password extends AppCompatActivity {
    private EditText passwordResset;
    private Button userreset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        getSupportActionBar().setTitle("UserForget");

        passwordResset = findViewById(R.id.editTextEmail);
        userreset = findViewById(R.id.buttonReset);
        userreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reset();
            }
        });



    }
    public void reset(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        String email = passwordResset.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            passwordResset.setError("Enter a valid email");
            passwordResset.requestFocus();
            return;
        }



        UserPasswordSender userPasswordSender = new UserPasswordSender(email);


        progressDialog.setMessage("ForgetPassword...");
        progressDialog.show();

        Call<UserPasswordResponse> call = RetrofitClient.getInstance().getApi().userforget(userPasswordSender);

       call.enqueue(new Callback<UserPasswordResponse>() {
           @Override
           public void onResponse(Call<UserPasswordResponse> call, Response<UserPasswordResponse> response) {
               progressDialog.dismiss();
               UserPasswordResponse userPasswordResponse = response.body();
               try {
                   if (userPasswordResponse.getStatus() == 200) {
                       Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                   }else{
                       Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                   }
               }catch (Exception e){
                   e.printStackTrace();
               }

           }

           @Override
           public void onFailure(Call<UserPasswordResponse> call, Throwable t) {

           }
       });
    }
}