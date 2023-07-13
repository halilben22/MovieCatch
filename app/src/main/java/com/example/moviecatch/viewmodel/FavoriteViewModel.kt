package com.example.moviecatch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatch.dao.FavoritesRepository
import com.example.moviecatch.models.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
   private val favoritesRepository: FavoritesRepository,

) :ViewModel(){

 var allFavData: MutableLiveData<List<Result>> = MutableLiveData()


   fun getFavoritesObserver(): MutableLiveData<List<Result>> {
      return allFavData
   }


   fun readFavs():List<com.example.moviecatch.models.Result>{
   return   favoritesRepository.readAllFav
   }
   fun addFav(favDataResult:Result){
      favoritesRepository.addFavorites(favDataResult)

   }


   fun deleteFav(favListData: Result){
      favoritesRepository.deleteFav(favListData)
   }



   fun loadDatas(){
      val list=favoritesRepository.readAllFav
      allFavData.postValue(list)
   }


   fun updateFavorite(isPopular:Boolean,id:Int){
      favoritesRepository.updateFavorites(isPopular,id)
   }
}