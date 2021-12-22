package com.example.android_assignment2.rest

import com.example.android_assignment2.models.classic.ClassicMusicModelResult
import com.example.android_assignment2.models.pop.PopMusicModelResult
import com.example.android_assignment2.models.rock.RockMusicModelResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApi {
    @GET(MUSIC_GENRE_CLASSIC)
    fun getClassicArtistsMusic(): Single<ClassicMusicModelResult>

    @GET(MUSIC_GENRE_ROCK)
    fun getRockArtistsMusic(): Single<PopMusicModelResult>

    @GET(MUSIC_GENRE_POP)
    fun getPopArtistsMusic(): Single<RockMusicModelResult>

    fun getClassicMusic(
        @Query("term") musicGenre: String = MUSIC_GENRE_CLASSIC,
        @Query("media") media: String = MEDIA,
        @Query("entity") entity: String = ENTITY,
        @Query("limit") limit: String = LIMIT
    ): Single<ClassicMusicModelResult>

    fun getPopMusic(
        @Query("term") musicGenre: String = MUSIC_GENRE_POP,
        @Query("media") media: String = MEDIA,
        @Query("entity") entity: String = ENTITY,
        @Query("limit") limit: String = LIMIT
    ): Single<PopMusicModelResult>

    fun getRockMusic(
        @Query("term") musicGenre: String = MUSIC_GENRE_ROCK,
        @Query("media") media: String = MEDIA,
        @Query("entity") entity: String = ENTITY,
        @Query("limit") limit: String = LIMIT
    ): Single<RockMusicModelResult>

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