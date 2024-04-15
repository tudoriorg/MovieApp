package com.example.movieapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentHomeTabBinding
import com.example.movieapp.ui.adapter.MovieCardAdapter
import com.example.movieapp.ui.adapter.SpacingItemDecorator
import com.example.movieapp.ui.models.MovieListUiState
import com.example.movieapp.ui.moviedetails.ARG_MOVIE_ID_EXTRA
import com.example.movieapp.ui.moviedetails.MovieDetailsActivity
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

enum class RecommendationType{
    now_playing,
    popular,
    top_rated,
    upcoming
}
class HomeTabFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModel<HomeViewModel>()


    private var _binding: FragmentHomeTabBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private lateinit var recommendationType: String
    private lateinit var movieCardAdapter: MovieCardAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(ARG_RECOMMENDATION_TYPE) }?.apply {
            recommendationType  = getString(ARG_RECOMMENDATION_TYPE).toString()
        }
        viewModel.getRecommendations(recommendationType)
        setupAdapter()
        observeUiState()
    }

    private fun observeUiState() {
        viewModel.uiState.observe(viewLifecycleOwner){ uiState ->
            when(uiState){
                is MovieListUiState.Loading -> {
                    binding.homePb.isVisible = true
                    binding.homeMoviesRv.isVisible = false
                }
                is MovieListUiState.ShowMovieList -> {
                    binding.homePb.isVisible = false
                    binding.homeMoviesRv.isVisible = true
                    movieCardAdapter.movieList = uiState.movieList
                    movieCardAdapter.notifyDataSetChanged()
                }
                is MovieListUiState.Error -> {

                }
                else -> {}
            }
        }
    }
    private fun setupAdapter(){
        binding.homeMoviesRv.layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        movieCardAdapter = MovieCardAdapter(
            emptyList(),
            {   movieId ->
                val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
                    .putExtra(ARG_MOVIE_ID_EXTRA,movieId)
                startActivity(intent)
            },
            {   movieUiModel ->
                viewModel.toggleFavourite(movieUiModel)
            }
        )
        val x = (resources.displayMetrics.density * 8).toInt()
        binding.homeMoviesRv.addItemDecoration(SpacingItemDecorator(x))
        binding.homeMoviesRv.adapter = movieCardAdapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

const val ARG_RECOMMENDATION_TYPE = "rec_type"
