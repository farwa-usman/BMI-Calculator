package com.example.bmicalculator

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update

@Entity(tableName = "bmi-record")
data class BmiRecordEntity(
    @PrimaryKey(autoGenerate = true)
     val id: Int = 0,
    val weight:String,
    val height: String,
    val bmi: Float,
    val result: String,
    val emoji: String,
    val time: String
)
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