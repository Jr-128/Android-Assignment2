package com.example.android_assignment2.presenters

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.android_assignment2.database.PopMusicDatabase
import com.example.android_assignment2.models.pop.PopMusic
import com.example.android_assignment2.rest.MusicApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * This class will be the presenter for pop music
 * portion and will handle all
 */
class PopMusicPresenter @Inject constructor(
    var musicApi: MusicApi,
    var connectivityManager: ConnectivityManager,
    var popMusicDatabase: PopMusicDatabase
) : IPopMusicPresenter {

    //Nullable variable to hold the view contract
    private var iPopMusicViewContract: IPopMusicView? = null

    //Creating a disposable to dispose the observers from RxJava
    private val disposable by lazy {
        CompositeDisposable()
    }

    private var isNetworkAvailable = false

    //This method helps us initializing the view contract
    override fun initPopPresenter(viewContract: IPopMusicView) {
        //Assigning the view contract to the local variable
        iPopMusicViewContract = viewContract
    }

    /**
     * This method retrieves the data from server overridden from the
     * presenter contract.
     */
    override fun getPopMusicFromServer() {

        //Here we retrieve the pop music from server
        //then change to a worker thread aside from the main thread
        //observing the response on the main thread
        //finally subscribing to receive the response and get the data
        if (isNetworkAvailable) {
            val musicDisposable = musicApi
                .getPopMusic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    //Updated view when success occurs
                    {
                        iPopMusicViewContract?.onSuccessMusicData(it.popMusicList)

                    },
                    //Update view when error occurs
                    {
                        iPopMusicViewContract?.onErrorMusicData(it)
                    }
                )
            disposable.add(musicDisposable)
        } else {
            iPopMusicViewContract?.onErrorNetwork()
        }
    }

    //This method will check for network capabilities to determine
    //if the network is available
    override fun checkNetworkState() {
        isNetworkAvailable = getActiveNetworkCapabilities()?.let {
            it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    it.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        } ?: false
    }


    //This method will destroy the presenter, resetting the view contract and clear the disposables
    override fun destroyPresenter() {
        disposable.clear()
        iPopMusicViewContract = null
    }

    override fun savePopsToDb(popsToSave: List<PopMusic>) {
        // Here I am going to use RxJava to change to a worker thread
        val popMusicDatabaseDisposable = popMusicDatabase
            .getPopsDao()
            .insertPops(popsToSave)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { Log.d("success", "presenter's savePopsToDb() successful") },
                { Log.e("error", "savePopsToDb() error: "+ it.localizedMessage) }
            )
        disposable.add(popMusicDatabaseDisposable)
    }

    override fun getLocalPops() {
        Log.d("getLocalPops", "presenter getLocalPops called")
        val popMusicDatabaseDisposable = popMusicDatabase
            .getPopsDao()
            .getPopsFromDb()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    iPopMusicViewContract?.onSuccessMusicData(it)
                    Log.d("success", "Retrieved pops, updating the view")
                },
                { Log.e("error", "ERROR in presenter's getLocalPops()") }
            )
        disposable.add(popMusicDatabaseDisposable)
    }


    //This method will get the network capabilities from the connectivity manager
    private fun getActiveNetworkCapabilities(): NetworkCapabilities? {
        return connectivityManager.activeNetwork.let {
            connectivityManager.getNetworkCapabilities(it)
        }
    }
}

interface IPopMusicPresenter {
    //This method will initialize the presenter
    fun initPopPresenter(viewContract: IPopMusicView)

    //This method gets the data from the server
    fun getPopMusicFromServer()

    //This method checks the network state to allow calls to it
    fun checkNetworkState()

    //This method destroys the presenter
    fun destroyPresenter()

    //This method saves the data to the server
    fun savePopsToDb(popsToSave: List<PopMusic>)

    //This method gets the data saved on the server
    fun getLocalPops()

    //This method will be called to swipe to refresh the screen
    //may need to move it to the view contract
    //fun refresh()
}


interface IPopMusicView {
    //Method returns the success response to the view
    fun onSuccessMusicData(popMusicList: List<PopMusic>)

    //Method returns the error response to the view
    fun onErrorMusicData(error: Throwable)

    //Method returns the error response to the view
    fun onErrorNetwork()
}

