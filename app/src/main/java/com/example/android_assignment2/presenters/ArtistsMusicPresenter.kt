package com.example.android_assignment2.presenters

import android.util.Log

class ArtistsMusicPresenter : IArtistMusicPresenter {
    override fun getArtistMusicPresenter(){
        Log.d("TAG", "message")
    }
}

interface IArtistMusicPresenter{
    fun getArtistMusicPresenter()
}

interface IArtistMusicView{

}

