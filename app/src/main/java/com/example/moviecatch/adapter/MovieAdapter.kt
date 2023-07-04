package com.example.moviecatch.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatch.R

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MyCustomHolder>() {
   class MyCustomHolder(private val view: View) : RecyclerView.ViewHolder(view) {
      val textTitle = view.findViewById<TextView>(R.id.title)

      fun bindData(data: com.example.moviecatch.models.Result) {
         textTitle.text = data.title

      }
   }

   var liveData: List<com.example.moviecatch.models.Result>? = null

   @SuppressLint("NotifyDataSetChanged")
   fun setList(liveData: List<com.example.moviecatch.models.Result>) {
      this.liveData = liveData
      notifyDataSetChanged()
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MyCustomHolder {
      val view =
         LayoutInflater.from(parent.context).inflate(R.layout.popular_movie_item, parent, false)
      return MovieAdapter.MyCustomHolder(view)
   }

   override fun onBindViewHolder(holder: MovieAdapter.MyCustomHolder, position: Int) {
      holder.bindData(liveData!![position])
   }

   override fun getItemCount(): Int {
      return if (liveData == null) {
         0
      } else {
         liveData?.size ?: 0
      }

   }
}