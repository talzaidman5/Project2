package com.example.module;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TimeApp.class}, version = 1)
    abstract class AppDatabase extends RoomDatabase {
        public abstract TimeAppDao timeAppDao();

}
