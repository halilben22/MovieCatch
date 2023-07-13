package com.example.moviecatch.dao

import androidx.room.*


@Dao
interface GenreDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
 fun addGenre(genre: GenreData)


@Insert(onConflict = OnConflictStrategy.REPLACE)
fun addAllGenres(objects: List<GenreData>)


@Query("SELECT*FROM genres")
fun readAllData():List<GenreData>






}