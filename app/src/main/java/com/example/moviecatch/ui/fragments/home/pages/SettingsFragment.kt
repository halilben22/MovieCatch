package com.example.moviecatch.ui.fragments.home.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviecatch.R
import com.example.moviecatch.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment :Fragment(R.layout.fragment_settings){


   private var _binding:FragmentSettingsBinding? = null
   // This property is only valid between onCreateView and
// onDestroyView.
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      _binding = FragmentSettingsBinding.inflate(inflater, container, false)
      val view = binding.root
      return view
   }

   override fun onDestroyView() {
      super.onDestroyView()
   }
}