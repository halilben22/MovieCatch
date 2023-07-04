package com.example.moviecatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatch.adapter.MovieAdapter
import com.example.moviecatch.models.Movie
import com.example.moviecatch.viewmodel.HomePageViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity  : AppCompatActivity() {


 lateinit var movieAdapter: MovieAdapter

   private val viewModel by lazy {
      ViewModelProvider(this,defaultViewModelProviderFactory)[HomePageViewModel::class.java]
   }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        movieAdapter=MovieAdapter()
        recyclerView.adapter=movieAdapter
viewModel.getObserveLiveData().observe(this
) { value ->
   if (value != null) {
      movieAdapter.setList(value.results)
   }
}
       viewModel.loadPopularData("1")

    }
}