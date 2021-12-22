package com.example.android_assignment2.models.pop


import com.google.gson.annotations.SerializedName

data class PopMusicModel(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val popMusicList: List<PopMusic>
)