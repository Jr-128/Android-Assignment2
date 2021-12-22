package com.example.android_assignment2.models.classic


import com.google.gson.annotations.SerializedName

data class ClassicMusicModel(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val classicMusicModelResults: List<ClassicMusicModelResult>
)