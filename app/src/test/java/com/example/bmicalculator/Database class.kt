package com.example.bmicalculator

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BmiRecordEntity::class], version = 1)
abstract class MyDatabase: RoomDatabase(){
    abstract fun MyDao(): BmiDao
    companion object{
        @Volatile var INSTANCE: MyDatabase?=null
        fun getDataBase(context: Context): MyDatabase{
            return INSTANCE ?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,"User_DataBase").build()
                INSTANCE=instance
                instance
            }
        }
    }
}


