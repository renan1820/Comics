package com.example.comics.renan.domain.repository

import com.example.comics.renan.domain.model.Comic
import com.example.comics.renan.util.Resource

interface ComicsRepository {
    suspend fun getComics(): Resource<List<Comic>>
}