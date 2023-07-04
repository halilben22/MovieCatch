package com.example.moviecatch.module

import com.example.moviecatch.retrofit.RetrofitRepository
import com.example.moviecatch.retrofit.RetrofitServiceInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
 object  AppModule {


   private var BASE_URL = "https://api.themoviedb.org/"




   @Singleton
   @Provides
   fun getRetrofitServiceInstance(retrofit: Retrofit): RetrofitServiceInstance {

      return retrofit.create(RetrofitServiceInstance::class.java)
   }


   @Singleton
   @Provides
   fun getRetrofitInstance(): Retrofit {
      return Retrofit.Builder().baseUrl(BASE_URL)
         .addConverterFactory(GsonConverterFactory.create())
         .build()
   }
}