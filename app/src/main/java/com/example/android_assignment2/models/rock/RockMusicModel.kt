package com.example.android_assignment2.models.rock


import com.google.gson.annotations.SerializedName

data class RockMusicModel(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val rockMusicList: List<RockMusic>
)