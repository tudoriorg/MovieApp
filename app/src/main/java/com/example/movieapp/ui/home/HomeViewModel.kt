package com.example.movieapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.home.GetRecommendationsUseCase
import com.example.movieapp.ui.models.HomeUiState
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getRecommendationsUseCase: GetRecommendationsUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<HomeUiState>(HomeUiState.Loading)
    val uiState : LiveData<HomeUiState> = _uiState

    fun getRecommendations(recommendationType: String){
        viewModelScope.launch {
            _uiState.value = getRecommendationsUseCase.execute(recommendationType)
        }
    }
}