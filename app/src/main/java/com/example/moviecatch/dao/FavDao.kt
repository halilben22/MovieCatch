package com.example.moviecatch.dao

import androidx.room.*


@Dao
interface FavDao {


   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun addFavorites(objects: com.example.moviecatch.models.Result)




   @Query("SELECT * FROM favorites")
   fun readFavorites():List<com.example.moviecatch.models.Result>


   @Delete
   fun deleteFavorites(favData:com.example.moviecatch.models.Result)

   @Query("UPDATE favorites SET isFavorite=:isPopular WHERE id=:id")
   fun updateFavorite(isPopular: Boolean,id: Int)

}