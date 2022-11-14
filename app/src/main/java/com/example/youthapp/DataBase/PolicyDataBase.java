package com.example.youthapp.DataBase;


import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Policy.class}, version = 1)
public abstract class PolicyDataBase extends RoomDatabase {
    public abstract PolicyDao policyDao();
}
