package com.example.moviecatch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatch.models.Movie
import com.example.moviecatch.retrofit.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(val repository: RetrofitRepository):ViewModel(){

private var popular_movie_list:MutableLiveData<Movie> = MutableLiveData()


   fun getObserveLiveData():MutableLiveData<Movie>{
      return popular_movie_list
   }
fun loadPopularData(page:String){
   repository.getPopularMovies(page,popular_movie_list)
}

}