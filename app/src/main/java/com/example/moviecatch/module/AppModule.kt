package com.example.moviecatch.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.moviecatch.dao.FavDao
import com.example.moviecatch.dao.FavoritesDatabase
import com.example.moviecatch.dao.GenreDao
import com.example.moviecatch.dao.GenreDatabase
import com.example.moviecatch.prefs.SessionManager
import com.example.moviecatch.retrofit.RetrofitServiceInstance
import com.example.moviecatch.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


   private var BASE_URL = "https://api.themoviedb.org/"


   @Provides
   @Singleton

   fun provideSharedPreferences(@ApplicationContext context: Context) =
      context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)


   @Provides
   @Singleton
   fun provideSessionManager(preferences: SharedPreferences) = SessionManager(preferences)

   @Singleton
   @Provides
   fun getAppDB(context: Application): GenreDatabase {
      return GenreDatabase.getAppDB(context)
   }


   @Singleton
   @Provides
   fun getDao(appDB: GenreDatabase): GenreDao {
      return appDB.getDao()
   }





   @Singleton
   @Provides
   fun getAppDBFav(context: Application): FavoritesDatabase{
      return FavoritesDatabase.getAppDB(context)
   }


   @Singleton
   @Provides
   fun getFavDao(appDB:FavoritesDatabase): FavDao {
      return appDB.getDao()
   }



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