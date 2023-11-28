package com.example.comics.renan.presentation.comics_screen.state

import com.example.comics.renan.domain.model.Comic


data class ComicsState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val comicsList: List<Comic> = emptyList(),
    val swipeRefresh: Boolean = false,
    val selectedComics: Comic? = null

)
