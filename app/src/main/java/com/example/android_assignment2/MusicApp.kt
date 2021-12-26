package com.example.android_assignment2

import android.app.Application
import com.example.android_assignment2.di.AppModule
import com.example.android_assignment2.di.DaggerMusicAppComponent
import com.example.android_assignment2.di.MusicAppComponent

class MusicApp : Application() {

    override fun onCreate() {
        super.onCreate()

        musicAppComponent = DaggerMusicAppComponent
            .builder()
            // I create the app module to be used
            .appModule(AppModule(this))
            // i build the dagger component
            .build()
    }

    companion object{
        //This property will be able to be accessed anywhere
        //in the app
        lateinit var musicAppComponent: MusicAppComponent
    }
}

