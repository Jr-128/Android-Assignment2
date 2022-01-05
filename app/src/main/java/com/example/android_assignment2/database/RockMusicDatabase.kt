package com.example.android_assignment2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_assignment2.models.rock.RockMusic

@Database(entities = [RockMusic::class], version = 1)
abstract class RockMusicDatabase : RoomDatabase() {
    abstract fun getRocksDao(): RockMusicDao

}