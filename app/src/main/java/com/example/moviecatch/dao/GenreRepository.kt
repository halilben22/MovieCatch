package com.example.moviecatch.dao

import javax.inject.Inject

class GenreRepository @Inject constructor(

   private val genreDao: GenreDao
){

   val readAllData:List<GenreData>
      get() {
      return  genreDao.readAllData()
      }








  fun addAllGenres(genreList: List<GenreData>){

      genreDao.addAllGenres(genreList)
   }
}