package com.example.module;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public abstract class MainActivityModule extends AppCompatActivity {
    private long startTimeStamp = 0;
    private Manager manager;
    private TextView main_LBL_info,main_LBL_time;
    public static String appName;
    public static int colorApp;
    public static int imageApp;
    private ImageView main_IMG_photo;
    private RelativeLayout activity_main_module_RelativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_module);

        main_LBL_time = findViewById(R.id.main_LBL_time);
        main_LBL_info = findViewById(R.id.main_LBL_info);
        main_IMG_photo = findViewById(R.id.main_IMG_photo);
        activity_main_module_RelativeLayout = findViewById(R.id.activity_main_module_RelativeLayout);
        activity_main_module_RelativeLayout.setBackgroundResource(colorApp);
        main_IMG_photo.setBackgroundResource(imageApp);
        manager = Manager.initHelper(this.getApplicationContext());
        downloadGarage();

    }

    public interface CallBack_GarageDownload {
        void dataReady(Garage garage);
    }

    private void downloadGarage() {

        new GarageController().fetchAllGarage(new GarageController.CallBack_Garage() {
            @Override
            public void garage(Garage garage) {
                if (garage != null)
                    main_LBL_info.setText("Garage Name: " + garage.getName()+"\n \n"+"Address: " +garage.getAddress()+"\n \n"+
                            "Is open: " + garage.getOpen()+"\n \n"+"Cars: " + Arrays.toString(garage.getCars()));
            }
    });
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimeStamp = System.currentTimeMillis();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    manager.getInstance().getAllLogsByTag(appName, time -> runOnUiThread(() -> {
                        float total = calculateTime(time);
                        main_LBL_time.setText( "Total Screen time "+total);

                    }));
                }
            }).start();
    }

    private float calculateTime(List<TimeApp> time){
        float total = 0;
        for (TimeApp timeApp:time)
            total+=(timeApp.timeInApp)/1000;
        return  total;
    }

    @Override
    protected void onPause() {
        super.onPause();
        long timeInApp = System.currentTimeMillis() - startTimeStamp;
        Manager.getInstance().insertToDB(appName, timeInApp);
    }

}
