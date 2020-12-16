package com.example.rezorpayment.navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rezorpayment.R;

public class Notifications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
         getSupportActionBar().setTitle("Notifications");

    }
}