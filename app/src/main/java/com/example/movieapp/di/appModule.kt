package com.example.movieapp.di

import androidx.room.Room
import com.example.movieapp.data.api.Api
import com.example.movieapp.data.api.MovieRepository
import com.example.movieapp.data.api.MovieRepositoryImpl
import com.example.movieapp.data.api.baseUrl
import com.example.movieapp.data.local.FavouritesRepository
import com.example.movieapp.data.local.FavouritesRepositoryImpl
import com.example.movieapp.data.local.MovieDao
import com.example.movieapp.data.local.MovieDatabase
import com.example.movieapp.domain.home.GetRecommendationsUseCase
import com.example.movieapp.domain.moviedetails.GetMovieDetailsUseCase
import com.example.movieapp.domain.search.GetMoviesSearchUseCase
import com.example.movieapp.ui.favourites.FavouritesViewModel
import com.example.movieapp.ui.home.HomeViewModel
import com.example.movieapp.ui.moviedetails.MovieDetailsViewModel
import com.example.movieapp.ui.search.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<Api> {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    single<MovieDatabase> {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "movie.db"
        ).build()
    }

    single<MovieDao> {
        get<MovieDatabase>().movieDao()
    }

    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single<FavouritesRepository> { FavouritesRepositoryImpl(get()) }

    single<GetRecommendationsUseCase> { GetRecommendationsUseCase(get(),get()) }
    single<GetMoviesSearchUseCase> { GetMoviesSearchUseCase(get(),get()) }
    single<GetMovieDetailsUseCase> { GetMovieDetailsUseCase(get(),get()) }

    viewModel<HomeViewModel> { HomeViewModel(get(),get()) }
    viewModel<SearchViewModel> { SearchViewModel(get(),get()) }
    viewModel<FavouritesViewModel> { FavouritesViewModel(get()) }
    viewModel<MovieDetailsViewModel> { MovieDetailsViewModel(get(),get()) }
}