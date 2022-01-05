package com.example.android_assignment2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_assignment2.models.classic.ClassicMusic
import com.example.android_assignment2.models.classic.ClassicMusicModel
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ClassicMusicDao {

    @Query("select * from classicmusic where primaryGenreName like '%Classic%'")
    fun getClassicsFromDb(): Single<List<ClassicMusic>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClassics(classicsToSave: List<ClassicMusic>): Completable
}