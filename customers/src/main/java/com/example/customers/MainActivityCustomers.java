package com.example.customers;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.module.MainActivityModule;

public class MainActivityCustomers extends MainActivityModule {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        appName="customers";
        colorApp =  R.drawable.images;
        imageApp = R.drawable.customers;
        super.onCreate(savedInstanceState);
    }
}