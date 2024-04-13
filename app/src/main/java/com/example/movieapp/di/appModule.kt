package com.example.movieapp.di

import com.example.movieapp.data.Api
import com.example.movieapp.data.MovieRepository
import com.example.movieapp.data.MovieRepositoryImpl
import com.example.movieapp.data.baseUrl
import com.example.movieapp.domain.home.GetRecommendationsUseCase
import com.example.movieapp.ui.home.HomeViewModel
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

    single<MovieRepository> { MovieRepositoryImpl(get())}

    single<GetRecommendationsUseCase> { GetRecommendationsUseCase(get()) }

    viewModel<HomeViewModel> { HomeViewModel(get()) }
}