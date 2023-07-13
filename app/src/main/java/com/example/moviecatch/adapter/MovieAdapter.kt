package com.example.moviecatch.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatch.R
import com.example.moviecatch.dao.GenreData
import com.example.moviecatch.viewmodel.FavoriteViewModel

class MovieAdapter(
   private val isFirstScreen: Boolean = true,
   val favoriteViewModel: FavoriteViewModel,
   val context: Context
) :
   RecyclerView.Adapter<MovieAdapter.MyCustomHolder>() {
   class MyCustomHolder(view: View) : RecyclerView.ViewHolder(view) {
      val textTitle = view.findViewById<TextView>(R.id.txtTitle)
      val txtGenre = view.findViewById<TextView>(R.id.txtGenre)
      val posterView = view.findViewById<ImageView>(R.id.posterView)
      var heartButton2 = view.findViewById<ImageView>(R.id.imageHeartPopular)
      fun bindData(data: com.example.moviecatch.models.Result, list: List<GenreData>) {
         textTitle.text = data.title
         var genres = ""

         Glide.with(posterView).load("https://image.tmdb.org/t/p/w342" + data.poster_path)
            .into(posterView)


         for (id in data.genre_ids) {

            val result = list.find { x -> x.genre_id == id }

            if (result != null) {
               genres += result.en_name + ","
            }
         }
         genres = genres.substring(0, genres.length - 2)
         txtGenre.text = genres
      }
   }

   private var liveData: List<com.example.moviecatch.models.Result>? = null
   private var genreList: List<GenreData>? = null

   @SuppressLint("NotifyDataSetChanged")
   fun setList(liveData: List<com.example.moviecatch.models.Result>, list: List<GenreData>) {
      this.genreList = list
      this.liveData = liveData
      notifyDataSetChanged()
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomHolder {
      val view =
         LayoutInflater.from(parent.context).inflate(R.layout.popular_movie_item, parent, false)
      return MyCustomHolder(view)
   }

   override fun onBindViewHolder(holder: MyCustomHolder, position: Int) {
      holder.bindData(liveData!![position], genreList!!)
      makeFav(holder,position)
   }

   override fun getItemCount(): Int {
      if (liveData == null) {
         return 0
      } else if (isFirstScreen) {

         return 15
      } else {
         return 15
      }
   }



   fun makeFav(holder: MovieAdapter.MyCustomHolder, position: Int) {
      holder.heartButton2.setOnClickListener {
         holder.heartButton2.setImageResource(R.drawable.baseline_favorite_24)
         favoriteViewModel.addFav(liveData!![position])
         Toast.makeText(context,"Added to favorites!", Toast.LENGTH_SHORT).show()

      }
   }
}