package com.example.youthapp.DataBase;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PolicyDao {

    @Query("SELECT * FROM policy")
    List<Policy> getAll();

    @Query("DELETE FROM policy")
    void deleteAll();

    @Insert
    void insertAll(Policy... policies);

    @Delete
    void delete(Policy policy);

}
