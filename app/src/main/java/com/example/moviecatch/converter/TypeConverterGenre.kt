package com.example.moviecatch.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdsConverter {
   @TypeConverter
   fun fromGenreIds(genreIds: List<Int>?): String? {
      if (genreIds == null) {
         return null
      }
      val gson = Gson()
      val type = object : TypeToken<List<Int>>() {}.type
      return gson.toJson(genreIds, type)
   }

   @TypeConverter
   fun toGenreIds(genreIdsString: String?): List<Int>? {
      if (genreIdsString == null) {
         return null
      }
      val gson = Gson()
      val type = object : TypeToken<List<Int>>() {}.type
      return gson.fromJson(genreIdsString, type)
   }
}