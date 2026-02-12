package com.example.bmicalculator

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BmiDao{
    @Insert
    suspend fun insert(bmiRecord:BmiRecordEntity)
    @Delete
    suspend fun delete(bmiRecord:BmiRecordEntity)
    @Update
    suspend fun update(bmiRecord:BmiRecordEntity)
    @Query("SELECT*FROM `bmi-record`")
    suspend fun getAllRecord():List<BmiRecordEntity>
}