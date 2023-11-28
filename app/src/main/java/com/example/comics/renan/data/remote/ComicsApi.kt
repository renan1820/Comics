package com.example.comics.renan.data.remote

import com.example.comics.renan.domain.model.ComicsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicsApi {
//    https://gateway.marvel.com/v1/public/comics?ts=1682982412&apikey=b7e14bab409c70a5c4e7c2b319c09d7b&hash=3482f01e9bf207a437a4b621c91339ad
    companion object{
        const val BASE_URL = "https://gateway.marvel.com/v1/public/"
        const val API_KEY = "b7e14bab409c70a5c4e7c2b319c09d7b"
        const val TS = "1682982412"
        const val HASH = "3482f01e9bf207a437a4b621c91339ad"
    }

    @GET("comics")
    suspend fun getComics(
        @Query("ts") ts: String = TS,
        @Query("apikey") apikey: String = API_KEY,
        @Query("hash") hash: String = HASH
    ) : ComicsResponse
}