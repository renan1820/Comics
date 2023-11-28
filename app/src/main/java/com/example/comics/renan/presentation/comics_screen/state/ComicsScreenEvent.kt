package com.example.comics.renan.presentation.comics_screen.state

import com.example.comics.renan.domain.model.Comic

sealed class ComicsScreenEvent{
    data class onComicsCardClicked(val comic: Comic) : ComicsScreenEvent()
}