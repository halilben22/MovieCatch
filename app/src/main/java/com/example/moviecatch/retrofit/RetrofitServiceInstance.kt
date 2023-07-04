package com.example.moviecatch.retrofit

import com.example.moviecatch.models.Genre
import com.example.moviecatch.models.Movie
import com.example.moviecatch.models.Trailer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServiceInstance {

   @GET("3/movie/popular?api_key=e5fce941b1dd677b5007b9efe2db0550")
   fun getPopularVideos(@Query("page") query: String): Call<Movie>

   @GET("3/movie/now_playing?api_key=e5fce941b1dd677b5007b9efe2db0550")
   fun getRecentVideos(@Query("page") query: String): Call<Movie>


   @GET("3/genre/movie/list?api_key=e5fce941b1dd677b5007b9efe2db0550")
   fun getGenres(): Call<Genre>

   @GET("3/genre/movie/{id}/videos?api_key=e5fce941b1dd677b5007b9efe2db0550")
   fun getTrailerTeasers(@Path("id") id: Int): Call<Trailer>

   @GET("3/search/movie?api_key=e5fce941b1dd677b5007b9efe2db0550")
   fun getSuggestions(@Query("query") query: String): Call<Movie>
}