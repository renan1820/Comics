package com.example.comics.renan.domain.model

import com.google.gson.annotations.SerializedName

data class ComicsResponse(
    @SerializedName("data")
    val data: DataResponse,

)
data class DataResponse(
    @SerializedName("results")
    val results: List<Comic>,

)

data class Comic(
    @SerializedName("description")
    val description: String?,

    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,

    @SerializedName("title")
    val title: String?,
)