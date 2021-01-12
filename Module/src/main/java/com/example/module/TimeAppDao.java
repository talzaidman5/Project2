package com.example.module;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface  TimeAppDao {


    @Insert
    void insertToDB(TimeApp timeApp);

    @Insert
    void insertNameApp(TimeApp app);

    @Query("SELECT * FROM times WHERE app_name LIKE :app_name ")
    List<TimeApp> getAll(String app_name);

    @Query("SELECT * FROM times")
    List<TimeApp> getAll();

    @Query("DELETE FROM times")
    void deleteAll();

}
