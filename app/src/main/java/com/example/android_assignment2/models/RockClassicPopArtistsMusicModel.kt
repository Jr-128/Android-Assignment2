package com.example.android_assignment2.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RockClassicPopArtistsMusicModel(
    @Json(name = "resultCount")
    val resultCount: Int,
    @Json(name = "results")
    val results: List<Result>
)