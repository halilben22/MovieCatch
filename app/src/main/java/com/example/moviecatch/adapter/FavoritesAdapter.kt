package com.example.moviecatch.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatch.R
import com.example.moviecatch.viewmodel.FavoriteViewModel
import com.example.moviecatch.viewmodel.GenreViewModel

class FavoritesAdapter(val favoriteViewModel: FavoriteViewModel,val genreViewModel: GenreViewModel) :
   RecyclerView.Adapter<FavoritesAdapter.MyFavHolder>() {
   class MyFavHolder(view: View) : RecyclerView.ViewHolder(view) {
      val textTitle = view.findViewById<TextView>(R.id.txtTitle)
      val txtGenre = view.findViewById<TextView>(R.id.txtGenre)
      val posterView = view.findViewById<ImageView>(R.id.posterView)
      val txtReleaseDate = view.findViewById<TextView>(R.id.txtReleaseDate)
      fun bindData(data: com.example.moviecatch.models.Result) {
         textTitle.text = data.title
         txtReleaseDate.text=data.release_date
         var genres = ""

         Glide.with(posterView).load("https://image.tmdb.org/t/p/w342" + data.poster_path)
            .into(posterView)


      }
   }

   private var favList: List<com.example.moviecatch.models.Result>? = null

   @SuppressLint("NotifyDataSetChanged")
   fun setList(liveData: List<com.example.moviecatch.models.Result>) {

      this.favList = liveData
      notifyDataSetChanged()
   }

   @SuppressLint("SuspiciousIndentation")
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFavHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.fav_list_item, parent, false)
      return MyFavHolder(view)
   }

   override fun getItemCount(): Int {
      return favList?.size ?: 0
   }

   override fun onBindViewHolder(holder: MyFavHolder, position: Int) {


   holder.bindData(favList!![position])
   val deleteButton = holder.itemView.findViewById<ImageView>(R.id.imageDelete)
   deleteButton.setOnClickListener {
      println("buraya girdi")
      favoriteViewModel.updateFavorite(false,favList!![position].id)
      favoriteViewModel.deleteFav(favList!![position])
      favoriteViewModel.loadDatas()



   }


}

   }

