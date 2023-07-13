package com.example.moviecatch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatch.dao.GenreRepository
import com.example.moviecatch.models.Genre
import com.example.moviecatch.models.Movie
import com.example.moviecatch.retrofit.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
   private val repository: RetrofitRepository,
   private val genreRepository: GenreRepository
) : ViewModel() {

   private var popular_movie_list: MutableLiveData<Movie> = MutableLiveData()
   private var genreList: MutableLiveData<Genre> = MutableLiveData()
   private var recentMovieList: MutableLiveData<Movie> = MutableLiveData()



   fun getObserveGenre(): MutableLiveData<Genre> {


   return genreList
   }





   fun getObserveLiveData(isPopular: Boolean): MutableLiveData<Movie> {


      return if (isPopular) {
         popular_movie_list
      } else {
         recentMovieList
      }
   }


   fun loadGenreData() {
     repository.getAllGenres(genreList)
   }

   fun loadPopularData(page: String, isPopular: Boolean) {

      if (isPopular) {
         repository.getPopularMovies(page, popular_movie_list)
      } else {
         repository.getRecentMovies(page, recentMovieList)
      }
   }

}