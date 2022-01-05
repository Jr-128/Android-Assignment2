package com.example.android_assignment2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_assignment2.models.classic.ClassicMusic

@Database(entities = [ClassicMusic::class], version = 1)
abstract class ClassicMusicDatabase : RoomDatabase() {
    abstract fun getClassicsDao(): ClassicMusicDao

}