package com.example.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.data.api.baseImageUrl
import com.example.movieapp.databinding.ItemMovieCardBinding
import com.example.movieapp.ui.models.MovieUiModel

class MovieCardAdapter(
    var movieList: List<MovieUiModel>,
    private val movieClickListener: (Int) -> Unit,
    private val toggleFavouritesListener: (MovieUiModel) -> Unit,
): RecyclerView.Adapter<MovieCardAdapter.MovieCardViewHolder>() {

    inner class MovieCardViewHolder(
        private val binding: ItemMovieCardBinding,
        private val movieClickListener: (Int) -> Unit,
        private val toggleFavouritesListener: (MovieUiModel) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movieUiModel: MovieUiModel) {
            with(binding) {
                movieRatingTv.text = movieUiModel.rating.toString()
                movieYearTv.text = movieUiModel.releaseYear
                movieFavBtn.isSelected = movieUiModel.isFavourite
                Glide.with(binding.root.context)
                    .load(baseImageUrl +movieUiModel.posterUrl)
                    .placeholder(R.drawable.palceholder)
                    .into(moviePosterIv)
                root.setOnClickListener{
                    movieClickListener.invoke(movieUiModel.movieId)
                }

                movieFavBtn.setOnClickListener{
                    movieUiModel.isFavourite = !movieUiModel.isFavourite
                    movieFavBtn.isSelected = movieUiModel.isFavourite
                    toggleFavouritesListener.invoke(movieUiModel)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardViewHolder {
        return MovieCardViewHolder(
            ItemMovieCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            movieClickListener,
            toggleFavouritesListener
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieCardViewHolder, position: Int) {
        holder.bind(movieList[position])
    }
}