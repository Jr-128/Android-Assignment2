package com.example.android_assignment2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_assignment2.models.pop.PopMusic

@Database(entities = [PopMusic::class], version = 1)
abstract class PopMusicDatabase : RoomDatabase() {
    abstract fun getPopsDao(): PopMusicDao

}