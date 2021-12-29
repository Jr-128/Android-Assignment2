package com.example.android_assignment2.di

import android.net.ConnectivityManager
import com.example.android_assignment2.presenters.ClassicMusicPresenter
import com.example.android_assignment2.presenters.IClassicMusicPresenter
import com.example.android_assignment2.presenters.PopMusicPresenter
import com.example.android_assignment2.presenters.RockMusicPresenter
import com.example.android_assignment2.rest.MusicApi
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    /**
     * This method will provide the presenter needed with the parameters
     * for connectivity manager and network API
     *
     * @param musicApi Network API
     * @param connectivityManager Manager to check the network connection
     */
    @Provides
    fun provideClassicMusicPresenter(
        musicApi: MusicApi,
        connectivityManager: ConnectivityManager
    ): IClassicMusicPresenter =
        ClassicMusicPresenter(musicApi, connectivityManager)


    @Provides
    fun provideRockMusicPresenter(
        musicApi: MusicApi,
        connectivityManager: ConnectivityManager
    ) = RockMusicPresenter(musicApi, connectivityManager)


    @Provides
    fun providePopMusicPresenter(
        musicApi: MusicApi,
        connectivityManager: ConnectivityManager
    ) = PopMusicPresenter(musicApi, connectivityManager)
}