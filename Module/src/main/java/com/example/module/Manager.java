package com.example.module;

import android.content.Context;
import android.util.Log;


import androidx.room.Room;

import java.util.List;

public class Manager {

    private static Manager instance;
    private static AppDatabase appDatabase;

    private Manager(Context context) {
        appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, MainActivityModule.appName).build();
    }

    public static Manager getInstance() {
        return instance;
    }

    static Manager initHelper(Context context) {
        if (instance == null) {
            instance = new Manager(context);
        }

        return instance;
    }

        public void getAllLogsByTag(String tag,CallBack_Logs callBack_logs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<TimeApp> tLogs = appDatabase.timeAppDao().getAll(tag);
                if (callBack_logs != null) {
                    callBack_logs.dataReady(tLogs);
                }
            }
        }).start();
    }
//    public void getAllFromDB(String tag, float durationSec) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                List<TimeApp> tLogs = appDatabase.timeAppDao().getAll();
//                if (callBack_logs != null) {
//                    callBack_logs.dataReady(tLogs);
//                }
//            }
//        }).start();
//    }


    public interface CallBack_Logs {
        void dataReady(List<TimeApp> tLogs);
    }

    public interface CallBack_Time {
        void dataReady(float time);
    }


    void insertToDB(String appName, float time) { new Thread((new Runnable() {
            @Override
            public void run() {
                appDatabase.timeAppDao().insertToDB(new TimeApp(time, appName));
            }
        })).start();
    }

    void getDataFromDB(String appName, CallBack_Time callBack_time) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                float currentTime = 0;
                List<TimeApp> timeAppList = appDatabase.timeAppDao().getAll(appName);
                for (TimeApp timeApp : timeAppList)
                    currentTime = timeApp.timeInApp;

                if (callBack_time != null)
                    callBack_time.dataReady(currentTime);
            }
        }).start();
    }
}
