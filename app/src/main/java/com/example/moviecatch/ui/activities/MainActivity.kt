package com.example.moviecatch.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatch.R
import com.example.moviecatch.adapter.RecentMovieAdapter
import com.example.moviecatch.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


   private lateinit var movieAdapter: RecentMovieAdapter
   private lateinit var recentMovieAdapter: RecentMovieAdapter

   private val viewModel by lazy {
      ViewModelProvider(this, defaultViewModelProviderFactory)[HomePageViewModel::class.java]
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)




   }



}