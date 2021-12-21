package com.example.android_assignment2.presenters

import android.util.Log

class ClassicMusicPresenter : IArtistMusicPresenter {
    override fun getArtistMusicPresenter(){
        //TODO need to get the artist and log response
        Log.d("TAG", "message")
    }
}

interface IArtistMusicPresenter{
    fun getArtistMusicPresenter()
}

interface IArtistMusicView{
    fun showArtistMusic()
}

