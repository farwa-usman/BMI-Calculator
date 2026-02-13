package com.example.bmicalculator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BMIViewModel(application: Application): AndroidViewModel(application){
    private val db= MyDatabase.getDataBase(application)
    private val bmidao=db.MyDao()
     fun getDao(): LiveData<List<BmiRecordEntity>>{
         return bmidao.getAllRecord()}
   fun addrecord(record: BmiRecordEntity){
       viewModelScope.launch(Dispatchers.IO) {
           bmidao.insert(record) }}

}





