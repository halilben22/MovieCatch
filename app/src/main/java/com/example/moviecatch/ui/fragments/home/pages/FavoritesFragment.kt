package com.example.moviecatch.ui.fragments.home.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatch.R
import com.example.moviecatch.adapter.FavoritesAdapter
import com.example.moviecatch.dao.GenreData
import com.example.moviecatch.databinding.FragmentFavoriteBinding
import com.example.moviecatch.viewmodel.FavoriteViewModel
import com.example.moviecatch.viewmodel.GenreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorite) {
   private var _binding: FragmentFavoriteBinding? = null

   private val binding get() = _binding!!
   private lateinit var favoritesAdapter: FavoritesAdapter
   private var genreList: List<GenreData>? = null
   private val favoriteViewModel by lazy {
      ViewModelProvider(this, defaultViewModelProviderFactory)[FavoriteViewModel::class.java]
   }

   private val genreViewModel by lazy {
      ViewModelProvider(this, defaultViewModelProviderFactory)[GenreViewModel::class.java]
   }

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
      val view = binding.root
      fetchFavorites()


      favoritesAdapter = FavoritesAdapter(favoriteViewModel, genreViewModel)
      binding.recyclerFav.adapter = favoritesAdapter
      binding.recyclerFav.layoutManager =
         LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)


      genreViewModel.getRecordsObserver().observe(
         viewLifecycleOwner
      ) { value ->

         genreList = value
         genreViewModel.loadRecords()

      }

      favoriteViewModel.getFavoritesObserver().observe(viewLifecycleOwner) {
         favoritesAdapter.setList(it)

      }


      return view
   }

   private fun fetchFavorites() {
      CoroutineScope(Dispatchers.IO).launch {
         val job1: Deferred<Unit> = async {
            genreViewModel.loadRecords()
         }

         val job2: Deferred<Unit> = async {
            favoriteViewModel.loadDatas()
         }

         job1.await()
         job2.await()


      }
   }


}