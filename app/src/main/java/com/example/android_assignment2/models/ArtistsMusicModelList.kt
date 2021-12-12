package com.example.android_assignment2.models


import com.google.gson.annotations.SerializedName

data class ArtistsMusicModelList(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val results: List<ArtistsMusicModel>
)