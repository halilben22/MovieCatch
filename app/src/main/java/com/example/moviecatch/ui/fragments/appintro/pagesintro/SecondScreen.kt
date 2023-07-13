package com.example.moviecatch.ui.fragments.appintro.pagesintro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.moviecatch.databinding.FragmentSecondBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondScreen : Fragment() {

   private var _binding: FragmentSecondBinding? = null

   private val binding get() = _binding!!

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      _binding = FragmentSecondBinding.inflate(inflater, container, false)
      val view = binding.root
      return view
   }


   override fun onResume() {
      val viewPager= activity?.findViewById<ViewPager2>(com.example.moviecatch.R.id.viewPager)
      val prevButton=activity?.findViewById<RelativeLayout>(com.example.moviecatch.R.id.prevButton)
      val nextButton=activity?.findViewById<RelativeLayout>(com.example.moviecatch.R.id.nextButton)
nextButton?.alpha=1f
      nextButton?.isClickable=true
      prevButton?.alpha=1f
     prevButton?.setOnClickListener {

        viewPager?.currentItem=0
     }
      nextButton?.setOnClickListener {
         viewPager?.currentItem=2
      }
      super.onResume()
   }

}