package com.example.movieapp.ui.favourites

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.databinding.FragmentFavouritesBinding
import com.example.movieapp.ui.adapter.MovieCardAdapter
import com.example.movieapp.ui.adapter.SpacingItemDecorator
import com.example.movieapp.ui.moviedetails.ARG_MOVIE_ID_EXTRA
import com.example.movieapp.ui.moviedetails.MovieDetailsActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavouritesFragment : Fragment() {

    private val viewModel: FavouritesViewModel by viewModel<FavouritesViewModel>()
    private var _binding: FragmentFavouritesBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private lateinit var movieCardAdapter: MovieCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        collectFavouritesState()
        viewModel.favouritesFlow
    }

    private fun collectFavouritesState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.favouritesFlow.collect{
                    movieCardAdapter.movieList = it
                    movieCardAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun setupAdapter(){
        binding.favsMovieRv.layoutManager = GridLayoutManager(
            context,
            2,
            GridLayoutManager.VERTICAL,
            false
        )
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
        binding.favsMovieRv.addItemDecoration(SpacingItemDecorator(x))
        binding.favsMovieRv.adapter = movieCardAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}