package com.example.android_assignment2.presenters

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.android_assignment2.models.classic.ClassicMusic
import com.example.android_assignment2.models.classic.ClassicMusicModel
import com.example.android_assignment2.rest.MusicApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * This class will be the presenter for classic music
 * portion and will handle all
 */
class ClassicMusicPresenter @Inject constructor(
    var musicApi: MusicApi,
    var connectivityManager: ConnectivityManager
) : IClassicMusicPresenter {

    //Nullable variable to hold the view contract
    private var iClassicMusicViewContract: IClassicMusicView? = null

    //Creating a disposable to dispose the observers from RxJava
    private val disposable by lazy {
        CompositeDisposable()
    }

    private var isNetworkAvailable = false

    //This method helps us initializing the view contract
    override fun initClassicPresenter(viewContract: IClassicMusicView) {
        //Assigning the view contract to the local variable
        iClassicMusicViewContract = viewContract
    }

    /**
     * This method retrieves the data from server overridden from the
     * presenter contract.
     */
    override fun getClassicMusicFromServer(){
        //Here we retrieve the classic music from server
        //then change to a worker thread aside from the main thread
        //observing the response on the main thread
        //finally subscribing to receive the response and get the data
        if (isNetworkAvailable){
            val musicDisposable = musicApi
                .getClassicMusic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    //Updated view when success occurs
                    { classicMusicList ->
                        iClassicMusicViewContract?.onSuccessMusicData(classicMusicList)
                    },
                    //Update view when error occurs
                    { throwable ->
                        iClassicMusicViewContract?.onErrorMusicData(throwable)
                    }
                )
            disposable.add(musicDisposable)
        }
    }

    //This method will check for network capabilities to determine
    //if the network is available
    override fun checkNetworkState() {
        isNetworkAvailable = getActiveNetworkCapabilities()?.let {
            it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    it.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        }?: false
    }


    //This method will destroy the presenter, resetting the view contract and clear the disposables
    override fun destroyPresenter() {
        disposable.clear()
        iClassicMusicViewContract = null
    }

    //This method will get the network capabilities from the connectivity manager
    private fun getActiveNetworkCapabilities(): NetworkCapabilities? {
        return connectivityManager.activeNetwork.let {
            connectivityManager.getNetworkCapabilities(it)
        }
    }
}

interface IClassicMusicPresenter{
    //This method will initialize the presenter
    fun initClassicPresenter(viewContract: IClassicMusicView)

    //This method gets the data from the server
    fun getClassicMusicFromServer()

    //This method checks the network state to allow calls to it
    fun checkNetworkState()

    //This method destroys the presenter
    fun destroyPresenter()

    //This method will be called to swipe to refresh the screen
    //may need to move it to the view contract
    //fun refresh()
}


interface IClassicMusicView{
    //Method returns the success response to the view
    fun onSuccessMusicData(classicMusicList: ClassicMusicModel)

    //Method returns the error response to the view
    fun onErrorMusicData(error: Throwable)
}

