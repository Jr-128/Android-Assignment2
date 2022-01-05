package com.example.android_assignment2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_assignment2.models.pop.PopMusic
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PopMusicDao {

    @Query("select * from popmusic where primaryGenreName like '%Pop%'")
    fun getPopsFromDb(): Single<List<PopMusic>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPops(popsToSave: List<PopMusic>): Completable
}