package com.example.android_assignment2.presenters

import com.example.android_assignment2.models.rock.RockMusic

class RockMusicPresenter {
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