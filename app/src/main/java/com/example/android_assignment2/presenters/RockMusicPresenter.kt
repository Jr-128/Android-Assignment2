package com.example.android_assignment2.presenters

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.android_assignment2.database.RockMusicDatabase
import com.example.android_assignment2.models.rock.RockMusic
import com.example.android_assignment2.rest.MusicApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * This class will be the presenter for rock music
 * portion and will handle all
 */
class RockMusicPresenter @Inject constructor(
    var musicApi: MusicApi,
    var connectivityManager: ConnectivityManager,
    var rockMusicDatabase: RockMusicDatabase
) : IRockMusicPresenter {

    //Nullable variable to hold the view contract
    private var iRockMusicViewContract: IRockMusicView? = null

    //Creating a disposable to dispose the observers from RxJava
    private val disposable by lazy {
        CompositeDisposable()
    }

    private var isNetworkAvailable = false

    //This method helps us initializing the view contract
    override fun initRockPresenter(viewContract: IRockMusicView) {
        //Assigning the view contract to the local variable
        iRockMusicViewContract = viewContract
    }

    /**
     * This method retrieves the data from server overridden from the
     * presenter contract.
     */
    override fun getRockMusicFromServer() {

        //Here we retrieve the rock music from server
        //then change to a worker thread aside from the main thread
        //observing the response on the main thread
        //finally subscribing to receive the response and get the data
        if (isNetworkAvailable) {
            val musicDisposable = musicApi
                .getRockMusic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    //Updated view when success occurs
                    {
                        iRockMusicViewContract?.onSuccessMusicData(it.rockMusicList)

                    },
                    //Update view when error occurs
                    {
                        iRockMusicViewContract?.onErrorMusicData(it)
                    }
                )
            disposable.add(musicDisposable)
        } else {
            iRockMusicViewContract?.onErrorNetwork()
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
        iRockMusicViewContract = null
    }

    override fun saveRocksToDb(rocksToSave: List<RockMusic>) {
        // Here I am going to use RxJava to change to a worker thread
        val rockMusicDatabaseDisposable = rockMusicDatabase
            .getRocksDao()
            .insertRocks(rocksToSave)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { Log.d("success", "presenter's saveRocksToDb() successful") },
                { Log.e("error", "saveRocksToDb() error: "+ it.localizedMessage) }
            )
        disposable.add(rockMusicDatabaseDisposable)
    }

    override fun getLocalRocks() {
        Log.d("getLocalRocks", "presenter getLocalRocks called")
        val rockMusicDatabaseDisposable = rockMusicDatabase
            .getRocksDao()
            .getRocksFromDb()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    iRockMusicViewContract?.onSuccessMusicData(it)
                    Log.d("success", "Retrieved rocks, updating the view")
                },
                { Log.e("error", "ERROR in presenter's getLocalRocks()") }
            )
        disposable.add(rockMusicDatabaseDisposable)
    }


    //This method will get the network capabilities from the connectivity manager
    private fun getActiveNetworkCapabilities(): NetworkCapabilities? {
        return connectivityManager.activeNetwork.let {
            connectivityManager.getNetworkCapabilities(it)
        }
    }
}

interface IRockMusicPresenter {
    //This method will initialize the presenter
    fun initRockPresenter(viewContract: IRockMusicView)

    //This method gets the data from the server
    fun getRockMusicFromServer()

    //This method checks the network state to allow calls to it
    fun checkNetworkState()

    //This method destroys the presenter
    fun destroyPresenter()

    //This method saves the data to the server
    fun saveRocksToDb(rocksToSave: List<RockMusic>)

    //This method gets the data saved on the server
    fun getLocalRocks()

    //This method will be called to swipe to refresh the screen
    //may need to move it to the view contract
    //fun refresh()
}


interface IRockMusicView {
    //Method returns the success response to the view
    fun onSuccessMusicData(rockMusicList: List<RockMusic>)

    //Method returns the error response to the view
    fun onErrorMusicData(error: Throwable)

    //Method returns the error response to the view
    fun onErrorNetwork()
}

