package com.example.moviecatch.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [com.example.moviecatch.models.Result::class], version = 2)
abstract class FavoritesDatabase : RoomDatabase() {


   abstract fun getDao(): FavDao


   companion object {

      private var dbInstance: FavoritesDatabase? = null
      fun getAppDB(context: Context): FavoritesDatabase{
         if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(
               context.applicationContext,
               FavoritesDatabase::class.java,
               "favorites_database"
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
         }

         return dbInstance!!
      }

   }


}