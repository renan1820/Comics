package com.example.comics.renan.data.mock.comic

import com.example.comics.renan.domain.model.Comic
import com.example.comics.renan.domain.model.Thumbnail

val listComic : List<Comic>
    get() = listOf(
        comicMock1,
        comicMock2,
        comicMock3
    )

val comicMock1 = Comic(
    title = "Marvel Previews (2017)",
    description = "Aqui vai uma Descriçãozinha",
    thumbnail = Thumbnail(extension = "jpg", path = "http://i.annihil.us/u/prod/marvel/i/mg/c/c0/5a6648efbc46c")
)
val comicMock2 = Comic(
    title = "Marvel Previews (2018)",
    description = "Miranha",
    thumbnail = Thumbnail(extension = "jpg", path = "http://i.annihil.us/u/prod/marvel/i/mg/c/c0/5a6648efbc46c")
)
val comicMock3 = Comic(
    title = "Marvel Previews (2019)",
    description = "Muito mais Miranha",
    thumbnail = Thumbnail(extension = "jpg", path = "http://i.annihil.us/u/prod/marvel/i/mg/c/c0/5a6648efbc46c")
)