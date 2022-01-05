package com.example.android_assignment2.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.room.Room
import com.example.android_assignment2.database.ClassicMusicDatabase
import com.example.android_assignment2.database.PopMusicDatabase
import com.example.android_assignment2.database.RockMusicDatabase
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

    @Provides
    @Singleton
    fun provideClassicMusicDatabase(context: Context): ClassicMusicDatabase {
        return Room.databaseBuilder(
            context,
            ClassicMusicDatabase::class.java,
            "classicMusic-db"
        )
            .build()
    }
        @Provides
        @Singleton
        fun provideRockMusicDatabase(context: Context): RockMusicDatabase {
            return Room.databaseBuilder(
                context,
                RockMusicDatabase::class.java,
                "rockMusic-db")
                .build()
    }

        @Provides
        @Singleton
        fun providePopMusicDatabase(context: Context): PopMusicDatabase {
            return Room.databaseBuilder(
                context,
                PopMusicDatabase::class.java,
                "popMusic-db")
                .build()
        }
}