package com.example.comics.renan.data.repository

import com.example.comics.renan.data.remote.ComicsApi
import com.example.comics.renan.domain.model.Comic
import com.example.comics.renan.domain.repository.ComicsRepository
import com.example.comics.renan.util.Resource

class ComicsRepositoryImpl (
    private val comicsApi: ComicsApi
): ComicsRepository {
    override suspend fun getComics(): Resource<List<Comic>> {
        return try {
            val response = comicsApi.getComics()
            Resource.Success(response.data.results)
        }catch (e: Exception){
            Resource.Error(message = "Fail: ${e.message}")
        }
    }
}