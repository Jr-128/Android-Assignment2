package com.example.android_assignment2.di

import com.example.android_assignment2.MainActivity
import com.example.android_assignment2.views.ClassicFragment
import com.example.android_assignment2.views.PopFragment
import com.example.android_assignment2.views.RockFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        PresenterModule::class
    ]
)
@Singleton
interface MusicAppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(classicFragment: ClassicFragment)
    fun inject(popFragment: PopFragment)
    fun inject(rockFragment: RockFragment)
}