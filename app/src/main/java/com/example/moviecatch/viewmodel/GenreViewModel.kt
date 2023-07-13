package com.example.moviecatch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatch.dao.GenreData
import com.example.moviecatch.dao.GenreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class GenreViewModel @Inject constructor(

   private val genreRepository: GenreRepository
): ViewModel() {

   var allData:MutableLiveData<List<GenreData>> = MutableLiveData()



   init {
      allData=MutableLiveData()

      loadRecords()
   }


   fun getRecordsObserver():MutableLiveData<List<GenreData>>{
      return allData
   }





 fun addAllGenre(genreList:List<GenreData>){
      genreRepository.addAllGenres(genreList)
      loadRecords()
   }


 fun loadRecords(){

      val list=genreRepository.readAllData

      allData.postValue(list)

   }
}