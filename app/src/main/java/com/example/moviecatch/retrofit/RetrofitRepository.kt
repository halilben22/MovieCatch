package com.example.moviecatch.retrofit


import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.moviecatch.models.Movie
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject


class RetrofitRepository @Inject constructor(
   private val retrofitServiceInstance: RetrofitServiceInstance
) {

   fun getPopularMovies(page: String, liveData: MutableLiveData<Movie>) {

      retrofitServiceInstance.getPopularVideos(page).enqueue(object : Callback<Movie> {
         override fun onResponse(call: Call<Movie>, response: Response<Movie>) {

               liveData.postValue(response.body())

         }

         override fun onFailure(call: Call<Movie>, t: Throwable) {
            println("Başarısız")
            liveData.postValue(null)
         }
      })
   }

}