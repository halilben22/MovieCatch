package com.example.moviecatch.ui.fragments.home.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatch.R
import com.example.moviecatch.adapter.MovieAdapter
import com.example.moviecatch.adapter.RecentMovieAdapter
import com.example.moviecatch.dao.GenreData
import com.example.moviecatch.databinding.FragmentHomeBinding
import com.example.moviecatch.viewmodel.FavoriteViewModel
import com.example.moviecatch.viewmodel.GenreViewModel
import com.example.moviecatch.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

   private var _binding: FragmentHomeBinding? = null

   // This property is only valid between onCreateView and
// onDestroyView.
   private val binding get() = _binding!!


   private var genreList: List<GenreData>? = null


   private lateinit var movieAdapter: MovieAdapter
   private lateinit var recentMovieAdapter: RecentMovieAdapter

   private val viewModel by lazy {
      ViewModelProvider(this, defaultViewModelProviderFactory)[HomePageViewModel::class.java]
   }


   private val genreViewModel by lazy {
      ViewModelProvider(this, defaultViewModelProviderFactory)[GenreViewModel::class.java]
   }


   private val favoriteViewModel by lazy {
      ViewModelProvider(this, defaultViewModelProviderFactory)[FavoriteViewModel::class.java]
   }


   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentHomeBinding.inflate(inflater, container, false)
      val view = binding.root


      fetchMovies()

      initRecyclerViews()


      genreViewModel.getRecordsObserver().observe(
         viewLifecycleOwner
      ) { value ->
         genreList = value
fetchMovies()

      }


      viewModel.getObserveLiveData(true).observe(this) {
         movieAdapter.setList(it.results, genreList!!)


      }




      viewModel.getObserveLiveData(false).observe(this) {

         recentMovieAdapter.setList(it.results, genreList!!)


      }


      return view

   }


   private fun initRecyclerViews() {


      val lmHorizontalLayout = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
      val lmVerticalLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

      val recyclerView = binding.recyclerview
      val recentRecyclerView = binding.recyclerviewRecent

      recentRecyclerView.layoutManager = lmVerticalLayout
      recyclerView.layoutManager = lmHorizontalLayout
      movieAdapter = MovieAdapter(
         favoriteViewModel = favoriteViewModel,
         context = activity!!.applicationContext
      )
      recentMovieAdapter = RecentMovieAdapter(
         favoriteViewModel = favoriteViewModel,
         context = activity!!.applicationContext,

      )

      recyclerView.adapter = movieAdapter
      recentRecyclerView.adapter = recentMovieAdapter
   }

   private fun fetchMovies() {
      CoroutineScope(Dispatchers.IO).launch {
         val job1: Deferred<Unit> = async {
            viewModel.loadPopularData("1", true)
         }

         val job2: Deferred<Unit> = async {
            viewModel.loadPopularData("1", false)
         }

         job1.await()
         job2.await()


      }


   }
}


