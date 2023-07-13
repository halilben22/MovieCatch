package com.example.moviecatch.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "genres")
data class GenreData(


   @PrimaryKey
   var genre_id: Int,
   @ColumnInfo("en_name")
   val en_name: String,
   @ColumnInfo("tr_name")
   val tr_name: String,


)
