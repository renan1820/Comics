package com.example.comics.renan.presentation.comics_screen.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comics.renan.domain.repository.ComicsRepository
import com.example.comics.renan.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicsScreenViewModel @Inject constructor(private val comicsRepository: ComicsRepository): ViewModel(){

    var state by mutableStateOf(ComicsState())

    fun onEvent(event: ComicsScreenEvent){
        when(event){
            is ComicsScreenEvent.onComicsCardClicked -> {
                state = state.copy(selectedComics = event.comic)
            }
        }
    }

    init {
        getComics()
    }

    fun getComicsSwiper(bySwipe: Boolean = true){
        state = state.copy(swipeRefresh = bySwipe)
        getComics(bySwipe = bySwipe)
    }

    private fun getComics(bySwipe: Boolean = false){
        viewModelScope.launch {
            state = state.copy(isLoading = !bySwipe, swipeRefresh = bySwipe)
            when(val result = comicsRepository.getComics()){
                is Resource.Success ->{

                    state = state.copy(
                        isLoading = false,
                        swipeRefresh = false,
                        comicsList = result.data ?: emptyList(),
                        error = null)
                }
                is Resource.Error -> {

                    state = state.copy(
                        isLoading = false,
                        swipeRefresh = false,
                        error = result.message,
                        comicsList = emptyList()
                    )
                }
            }
        }
    }
}