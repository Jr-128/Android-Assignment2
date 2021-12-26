package com.example.android_assignment2.presenters

import android.net.ConnectivityManager
import com.example.android_assignment2.models.pop.PopMusic
import com.example.android_assignment2.rest.MusicApi
import javax.inject.Inject

class PopMusicPresenter  @Inject constructor(
    var musicApi: MusicApi,
    var connectivityManager: ConnectivityManager
): IPopMusicPresenter{
    override fun initPopPresenter(viewContract: IPopMusicView) {
        TODO("Not yet implemented")
    }

    override fun getPopMusicFromServer() {
        TODO("Not yet implemented")
    }

    override fun checkNetworkState() {
        TODO("Not yet implemented")
    }

    override fun destroyPresenter() {
        TODO("Not yet implemented")
    }

}

interface IPopMusicPresenter{
    //This method will initialize the presenter
    fun initPopPresenter(viewContract: IPopMusicView)

    //This method gets the data from the server
    fun getPopMusicFromServer()

    //This method checks the network state to allow calls to it
    fun checkNetworkState()

    //This method destroys the presenter
    fun destroyPresenter()
}


interface IPopMusicView{
    //Method returns the success response to the view
    fun onSuccessMusicData(popMusicList: List<PopMusic>)

    //Method returns the error response to the view
    fun onErrorMusicData(error: Throwable)
}
