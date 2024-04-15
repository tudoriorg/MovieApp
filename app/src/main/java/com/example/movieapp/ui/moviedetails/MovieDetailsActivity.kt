package com.example.movieapp.ui.moviedetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.data.api.baseImageUrl
import com.example.movieapp.databinding.ActivityMovieDetailsBinding
import com.example.movieapp.ui.models.MovieDetailsUiState
import org.koin.androidx.viewmodel.ext.android.viewModel

const val ARG_MOVIE_ID_EXTRA = "arg_movie_id"
class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private val viewModel: MovieDetailsViewModel by viewModel<MovieDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movieID = intent.getIntExtra(ARG_MOVIE_ID_EXTRA,0)
        observeUiState()
        viewModel.getDetails(movieID)
        binding.movieFavBtn.setOnClickListener {
            binding.movieFavBtn.isSelected = !binding.movieFavBtn.isSelected
            viewModel.toggleFavourite()
        }
        binding.detailsCloseIv.setOnClickListener { finish() }
    }

    private fun observeUiState() {
        viewModel.uiState.observe(this){ uiState ->
            when(uiState){
                is MovieDetailsUiState.Loading -> {
                }
                is MovieDetailsUiState.ShowDetails -> {
                    setupViews(uiState)
                }
                is MovieDetailsUiState.Error -> {

                }
                else -> {}
            }
        }
    }

    private fun setupViews(uiState: MovieDetailsUiState.ShowDetails) {
        with(uiState.movieDetails){
            Glide.with(this@MovieDetailsActivity)
                .load(baseImageUrl +backdropPath)
                .placeholder(R.drawable.palceholder)
                .into(binding.detailsBackdropIv)
            Glide.with(this@MovieDetailsActivity)
                .load(baseImageUrl +posterPath)
                .placeholder(R.drawable.palceholder)
                .into(binding.detailsPosterIv)
            binding.detailsTitleTv.text = title
            binding.detailsTaglineTv.text = tagline
            binding.detailsReleaseTv.text = "Released in $releaseDate"
            binding.detailsRatingTv.text = "$voteAverage/10"
            binding.detailsRatingCountTv.text = voteCount
            binding.detailsOverviewTv.text = overview
            binding.movieFavBtn.isSelected = isFavourite
            binding.detailsFirstGenrePill.isVisible= genres.isNotEmpty()
            binding.detailsFirstGenrePill.text= genres[0]
            binding.detailsSecondGenrePill.isVisible= genres.size == 2
            binding.detailsSecondGenrePill.text= genres[1]
        }
    }
}