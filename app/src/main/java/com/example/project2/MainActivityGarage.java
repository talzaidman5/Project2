package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.module.Garage;
import com.example.module.MainActivityModule;

public class MainActivityGarage extends MainActivityModule {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        appName="garage";
        colorApp = R.drawable.blue_background;
        imageApp =  R.drawable.garage;
        super.onCreate(savedInstanceState);

    }

}