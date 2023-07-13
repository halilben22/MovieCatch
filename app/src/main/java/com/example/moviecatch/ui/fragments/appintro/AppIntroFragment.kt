package com.example.moviecatch.ui.fragments.appintro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviecatch.adapter.ViewPagerAdapter
import com.example.moviecatch.databinding.FragmentAppintroBinding
import com.example.moviecatch.ui.fragments.appintro.pagesintro.FirstScreen
import com.example.moviecatch.ui.fragments.appintro.pagesintro.SecondScreen
import com.example.moviecatch.ui.fragments.appintro.pagesintro.ThirthScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppIntroFragment:Fragment() {
   private var _binding: FragmentAppintroBinding? = null
   // This property is only valid between onCreateView and
// onDestroyView.
   private val binding get() = _binding!!

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      _binding = FragmentAppintroBinding.inflate(inflater, container, false)
      val view = binding.root
   val fragmentList= arrayListOf<Fragment>(
      FirstScreen(),
      SecondScreen(),
      ThirthScreen()

      )

      val adapter=ViewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)
      binding.viewPager.adapter = adapter



      return view
   }
}