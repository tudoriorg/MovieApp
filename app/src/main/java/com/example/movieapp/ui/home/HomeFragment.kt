package com.example.movieapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.ui.models.HomeUiState
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel<HomeViewModel>()

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
        viewModel.getRecommendations("now_playing")
        observeUiState()
    }

    private fun observeUiState() {
        viewModel.uiState.observe(viewLifecycleOwner){ uiState ->
            when(uiState){
                is HomeUiState.Loading -> {
                    binding.tv.text = "Loading"
                }
                is HomeUiState.ShowMovieRecommendations -> {
                   binding.tv.text = "Retrieved ${uiState.movieList.size} movies"
                }
                is Error -> {
                    binding.tv.text = "Error"
                }
                else -> {}
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}