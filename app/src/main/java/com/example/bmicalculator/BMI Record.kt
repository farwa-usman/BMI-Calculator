package com.example.bmicalculator


import androidx.room.Entity
import androidx.room.PrimaryKey


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
