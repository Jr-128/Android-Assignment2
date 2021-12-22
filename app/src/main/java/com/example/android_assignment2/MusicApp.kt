package com.example.android_assignment2

import android.app.Application
import com.example.android_assignment2.di.MusicAppComponent

class MusicApp:Application() {

    override fun onCreate() {
        super.onCreate()

        musicAppComponent
    }

    companion object{
        //This property will be able to be accessed anywhere
        //in the app
        lateinit var musicAppComponent: MusicAppComponent
    }
}