package com.example.comics.renan.domain.model

import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("extension")
    val extension: String,

    @SerializedName("path")
    val path: String
)