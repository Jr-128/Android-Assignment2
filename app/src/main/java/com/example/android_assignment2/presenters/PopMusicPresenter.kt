package com.example.android_assignment2.presenters

import com.example.android_assignment2.models.pop.PopMusic

class PopMusicPresenter {

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
