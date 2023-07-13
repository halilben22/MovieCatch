package com.example.moviecatch.dao

import javax.inject.Inject

class FavoritesRepository @Inject constructor(
  private val dao: FavDao
) {
   val readAllFav:List<com.example.moviecatch.models.Result>
      get() {
         return dao.readFavorites()
      }




   fun addFavorites(favoritesData:com.example.moviecatch.models.Result){
      dao.addFavorites(favoritesData)
   }

   fun deleteFav(favDataResult:com.example.moviecatch.models.Result){
      dao.deleteFavorites(favDataResult)

   }

   fun updateFavorites(isPopular:Boolean,id:Int){
      dao.updateFavorite(isPopular,id)
   }


}