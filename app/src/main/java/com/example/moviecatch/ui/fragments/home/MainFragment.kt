   package com.example.moviecatch.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.moviecatch.R
import com.example.moviecatch.databinding.FragmentMainBinding
import com.example.moviecatch.prefs.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : Fragment() {
   private var _binding: FragmentMainBinding? = null

   @Inject
   lateinit var sessionManager: SessionManager
   private val binding get() = _binding!!

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentMainBinding.inflate(inflater, container, false)
      val view = binding.root

      if (sessionManager.getIsFirstRun()){
         sessionManager.setIsFirstRun(false)
      }




      setupTabBar()

      return view
   }

   private fun setupTabBar() {

      binding.menuBottom.setItemSelected(R.id.home_nav, true)
      binding.menuBottom.setOnItemSelectedListener {
         when (it) {
            R.id.home -> childFragmentManager.primaryNavigationFragment?.findNavController()
               ?.navigate(R.id.homeFragment)

            R.id.favorite -> childFragmentManager.primaryNavigationFragment?.findNavController()
               ?.navigate(R.id.favoritesFragment)

            R.id.settings -> childFragmentManager.primaryNavigationFragment?.findNavController()
               ?.navigate(R.id.settingsFragment)
         }
      }
   }
}