package com.example.moviecatch.ui.fragments.appintro.pagesintro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.moviecatch.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstScreen: Fragment() {


   private var _binding: FragmentFirstBinding? = null
   // This property is only valid between onCreateView and
// onDestroyView.
   private val binding get() = _binding!!

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      _binding = FragmentFirstBinding.inflate(inflater, container, false)
      val view = binding.root
      return view
   }

   override fun onResume() {
      val viewPager= activity?.findViewById<ViewPager2>(com.example.moviecatch.R.id.viewPager)
      val prevButton=activity?.findViewById<RelativeLayout>(com.example.moviecatch.R.id.prevButton)
      val nextButton=activity?.findViewById<RelativeLayout>(com.example.moviecatch.R.id.nextButton)

prevButton?.alpha=0f
      prevButton?.isClickable=false
nextButton?.setOnClickListener {
   viewPager?.currentItem=1
}
      super.onResume()
   }

}