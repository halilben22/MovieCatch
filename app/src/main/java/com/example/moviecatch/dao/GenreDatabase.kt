package com.example.moviecatch.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [GenreData::class], version = 2)
abstract class GenreDatabase : RoomDatabase() {


   abstract fun getDao(): GenreDao


   companion object {

      private var dbInstance: GenreDatabase? = null
      fun getAppDB(context: Context): GenreDatabase {
         if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(
               context.applicationContext,
               GenreDatabase::class.java,
               "genre_database"
            ).allowMainThreadQueries().build()
         }

         return dbInstance!!
      }

   }


}