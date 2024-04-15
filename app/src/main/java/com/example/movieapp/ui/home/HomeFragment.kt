package com.example.movieapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var moviePageAdapter: MoviePageAdapter
    private lateinit var viewPager: ViewPager2
    private val viewModel: HomeViewModel by activityViewModel<HomeViewModel>()

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviePageAdapter = MoviePageAdapter(this)
        viewPager = view.findViewById(binding.pager.id)
        viewPager.adapter = moviePageAdapter
        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            tab.text = RecommendationType.entries[position].toString()
                .replace("_"," ")
                .capitalize()
        }.attach()
        binding.homeToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.date_ascending -> {
                    viewModel.orderDateAscending()
                    true
                }
                R.id.date_descending -> {
                    viewModel.orderDateDescending()
                    true
                }
                R.id.rating_ascending -> {
                    viewModel.orderRatingAscending()
                    true
                }
                R.id.rating_descending -> {
                    viewModel.orderRatingDescending()
                    true
                }
                else -> false
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}