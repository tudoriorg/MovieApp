package com.example.movieapp.ui.search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.databinding.FragmentSearchBinding
import com.example.movieapp.ui.adapter.MovieCardAdapter
import com.example.movieapp.ui.adapter.SpacingItemDecorator
import com.example.movieapp.ui.models.MovieListUiState
import com.example.movieapp.ui.moviedetails.ARG_MOVIE_ID_EXTRA
import com.example.movieapp.ui.moviedetails.MovieDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModel<SearchViewModel>()
    private var _binding: FragmentSearchBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var movieCardAdapter: MovieCardAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observeUiState()
        binding.searchTil.editText?.setOnEditorActionListener { textView, i, keyEvent ->
            return@setOnEditorActionListener when (i) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    viewModel.searchMovie(textView.text.toString())
                    true
                }
                else -> false
            }

        }
    }

    private fun observeUiState() {
        viewModel.uiState.observe(viewLifecycleOwner){ uiState ->
            when(uiState){
                is MovieListUiState.Loading -> {

                }
                is MovieListUiState.ShowMovieList -> {
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
        binding.searchMovieRv.layoutManager = GridLayoutManager(
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
        binding.searchMovieRv.addItemDecoration(SpacingItemDecorator(x))
        binding.searchMovieRv.adapter = movieCardAdapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}