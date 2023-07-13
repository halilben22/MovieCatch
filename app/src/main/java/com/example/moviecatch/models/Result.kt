package com.example.moviecatch.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviecatch.converter.GenreIdsConverter


@Entity(tableName = "favorites")
@TypeConverters(*[GenreIdsConverter::class])
data class Result(

    val backdrop_path: String,
    val genre_ids: List<Int>,

    @PrimaryKey
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,

    @ColumnInfo("isFavorite")
    val isFavorite:Boolean?=false,



)