package com.example.android_assignment2.presenters

import android.net.ConnectivityManager
import com.example.android_assignment2.models.rock.RockMusic
import com.example.android_assignment2.rest.MusicApi
import javax.inject.Inject

class RockMusicPresenter @Inject constructor(
    var musicApi: MusicApi,
    var connectivityManager: ConnectivityManager
) : IRockMusicPresenter {
    override fun initRockPresenter(viewContract: IRockMusicView) {
        TODO("Not yet implemented")
    }

    override fun getRockMusicFromServer() {
        TODO("Not yet implemented")
    }

    override fun checkNetworkState() {
        TODO("Not yet implemented")
    }

    override fun destroyPresenter() {
        TODO("Not yet implemented")
    }

}

interface IRockMusicPresenter{
    //This method will initialize the presenter
    fun initRockPresenter(viewContract: IRockMusicView)

    //This method gets the data from the server
    fun getRockMusicFromServer()

    //This method checks the network state to allow calls to it
    fun checkNetworkState()

    //This method destroys the presenter
    fun destroyPresenter()
}


interface IRockMusicView{
    //Method returns the success response to the view
    fun onSuccessMusicData(rockMusicList: List<RockMusic>)

    //Method returns the error response to the view
    fun onErrorMusicData(error: Throwable)
}