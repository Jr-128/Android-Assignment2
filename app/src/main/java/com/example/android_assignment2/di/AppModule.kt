package com.example.android_assignment2.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    //This method will provide the application context to the Dagger
    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext

    //This method will provide the shared preferences file
    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)

    //This method will provide the connectivity manager
    @Provides
    @Singleton
    fun provideConnectivityManager(context: Context): ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}