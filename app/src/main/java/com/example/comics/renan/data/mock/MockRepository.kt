package com.example.comics.renan.data.mock

import com.example.comics.renan.data.mock.comic.comicMock1
import com.example.comics.renan.data.mock.comic.listComic
import com.example.comics.renan.domain.model.Comic

class MockRepository {

    fun getComics(): List<Comic>{
        return listComic
    }

    fun getComic(): Comic{
        return comicMock1
    }
}