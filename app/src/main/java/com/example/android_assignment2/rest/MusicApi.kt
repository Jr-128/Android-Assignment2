package com.example.android_assignment2.rest

import com.example.android_assignment2.models.classic.ClassicMusicModel
import com.example.android_assignment2.models.pop.PopMusic
import com.example.android_assignment2.models.pop.PopMusicModel
import com.example.android_assignment2.models.rock.RockMusic
import com.example.android_assignment2.models.rock.RockMusicModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApi {
    @GET(MUSIC_GENRE_CLASSIC)
    fun getClassicMusic(): Single<ClassicMusicModel>

    @GET(MUSIC_GENRE_ROCK)
    fun getRockMusic(): Single<PopMusicModel>

    @GET(MUSIC_GENRE_POP)
    fun getPopMusic(): Single<RockMusicModel>

    fun getClassicMusic(
        @Query("term") musicGenre: String = MUSIC_GENRE_CLASSIC,
        @Query("media") media: String = MEDIA,
        @Query("entity") entity: String = ENTITY,
        @Query("limit") limit: String = LIMIT
    ): Single<ClassicMusicModel>

    fun getPopMusic(
        @Query("term") musicGenre: String = MUSIC_GENRE_POP,
        @Query("media") media: String = MEDIA,
        @Query("entity") entity: String = ENTITY,
        @Query("limit") limit: String = LIMIT
    ): Single<PopMusic>

    fun getRockMusic(
        @Query("term") musicGenre: String = MUSIC_GENRE_ROCK,
        @Query("media") media: String = MEDIA,
        @Query("entity") entity: String = ENTITY,
        @Query("limit") limit: String = LIMIT
    ): Single<RockMusic>

    companion object {
        const val BASE_URL = "https://itunes.apple.com/search"
        private const val MEDIA = "music"
        private const val ENTITY = "song"
        private const val LIMIT = "50"
        private const val MUSIC_GENRE_ROCK = "rock"
        private const val MUSIC_GENRE_CLASSIC = "classic"
        private const val MUSIC_GENRE_POP = "pop"
    }
}