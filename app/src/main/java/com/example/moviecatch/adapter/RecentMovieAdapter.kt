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


class RecentMovieAdapter(
   private val isFirstScreen: Boolean = true,
   val favoriteViewModel: FavoriteViewModel,
   val context: Context,


   ) :
   RecyclerView.Adapter<RecentMovieAdapter.MyCustomHolder2>() {


   class MyCustomHolder2(view: View) : RecyclerView.ViewHolder(view) {


      val textTitle = view.findViewById<TextView>(R.id.txtTitle)
      val txtGenre = view.findViewById<TextView>(R.id.txtGenre)
      val posterView = view.findViewById<ImageView>(R.id.posterView)
      val txtVoteAverage = view.findViewById<TextView>(R.id.txtNoteAverage)
      val txtReleaseDate = view.findViewById<TextView>(R.id.txtReleaseDate)
      var heartButton = view.findViewById<ImageView>(R.id.imageHeart)


      fun bindData(data: com.example.moviecatch.models.Result, list: List<GenreData>) {

         textTitle.text = data.title
         txtReleaseDate.text = data.release_date
         txtVoteAverage.text = data.vote_average.toString()
         var genres = ""

         for (id in data.genre_ids) {

            val result = list.find { x -> x.genre_id == id }

            if (result != null) {
               genres += result.en_name + ","
            }
         }


         genres = genres.substring(0, genres.length - 2)
         txtGenre.text = genres



         Glide.with(posterView).load("https://image.tmdb.org/t/p/w342" + data.poster_path)
            .into(posterView)

      }
   }


   private var liveData: List<com.example.moviecatch.models.Result>? = null

   private var genreList: List<GenreData>? = null

   @SuppressLint("NotifyDataSetChanged")
   fun setList(liveData: List<com.example.moviecatch.models.Result>, genreList: List<GenreData>) {

      this.liveData = liveData
      this.genreList = genreList
      notifyDataSetChanged()
   }

   override fun onCreateViewHolder(
      parent: ViewGroup,
      viewType: Int
   ): MyCustomHolder2 {
      val view =
         LayoutInflater.from(parent.context).inflate(R.layout.recent_movie_item, parent, false)
      return MyCustomHolder2(view)
   }

   override fun onBindViewHolder(
      holder: MyCustomHolder2,
      @SuppressLint("RecyclerView") position: Int
   ) {
      holder.bindData(liveData!![position], genreList!!)




      makeFav(holder, position)
      makeUnFav(holder, position)
      favoriteViewModel.loadDatas()

   }


   override fun getItemCount(): Int {
      if (liveData == null) {
         return 0
      } else if (isFirstScreen) {

         return 15
      } else {
         return liveData!!.size
      }

   }

   private fun makeFav(holder: MyCustomHolder2, position: Int) {

      holder.heartButton.setOnClickListener {
         println("girdi")
         holder.heartButton.setImageResource(R.drawable.baseline_favorite_24)
         favoriteViewModel.addFav(liveData!![position])

         Toast.makeText(context, "Added to favorites!", Toast.LENGTH_SHORT).show()

      }


   }

   private fun makeUnFav(holder: MyCustomHolder2, position: Int) {

      if (liveData!![position].isFavorite == true) {
         holder.heartButton.setOnClickListener {

            println("Favori yapmadı")
            holder.heartButton.setImageResource(R.drawable.baseline_favorite_border_24)
            favoriteViewModel.updateFavorite(false, liveData!![position].id)
            favoriteViewModel.addFav(liveData!![position])
            favoriteViewModel.loadDatas()

            Toast.makeText(context, "deleted!", Toast.LENGTH_SHORT).show()

         }
      }

   }


}