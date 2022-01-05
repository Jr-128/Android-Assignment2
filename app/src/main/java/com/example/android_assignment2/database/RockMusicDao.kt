package com.example.android_assignment2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_assignment2.models.rock.RockMusic
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface RockMusicDao {

    @Query("select * from rockmusic where primaryGenreName like '%Rock%'")
    fun getRocksFromDb(): Single<List<RockMusic>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRocks(rocksToSave: List<RockMusic>): Completable
}