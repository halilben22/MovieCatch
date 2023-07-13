package com.example.moviecatch.ui.fragments.appintro.pagesintro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.moviecatch.R
import com.example.moviecatch.dao.GenreData
import com.example.moviecatch.databinding.FragmentThirdBinding
import com.example.moviecatch.viewmodel.GenreViewModel
import com.example.moviecatch.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirthScreen : Fragment() {


   private var _binding: FragmentThirdBinding? = null

   private val binding get() = _binding!!

   private lateinit var genreViewModel: GenreViewModel
   private lateinit var homeViewModel: HomePageViewModel
   private var genreList: MutableList<GenreData>?=null

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentThirdBinding.inflate(inflater, container, false)
      genreList= mutableListOf()

      genreViewModel = ViewModelProvider(this)[GenreViewModel::class.java]
      homeViewModel = ViewModelProvider(this)[HomePageViewModel::class.java]






      homeViewModel.getObserveGenre().observe(viewLifecycleOwner) {

         if (it != null) {
            for (item in it.genres) {
               val tr_name = "Aksiyon"
               val genre = GenreData(item.id, item.name, tr_name)
               genreList!!.add(genre)
            }

try {
   genreViewModel.addAllGenre(genreList!!)
   findNavController().navigate(R.id.action_appIntroFragment_to_mainFragment)
}

catch (e:Exception){
   Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
}

         }


      }

      binding.nextImage.setOnClickListener {


         try {
            homeViewModel.loadGenreData()
         }

         catch (e:Exception){
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
         }

      }

      return binding.root
   }


   override fun onResume() {
      val viewPager = activity?.findViewById<ViewPager2>(com.example.moviecatch.R.id.viewPager)
      val prevButton =
         activity?.findViewById<RelativeLayout>(com.example.moviecatch.R.id.prevButton)
      val nextButton =
         activity?.findViewById<RelativeLayout>(com.example.moviecatch.R.id.nextButton)


      prevButton?.alpha = 1f
      prevButton?.setOnClickListener {

         viewPager?.currentItem = 1
      }
      nextButton?.alpha = 0f
      nextButton?.isClickable = false

      super.onResume()
   }
}