package com.example.android_assignment2.rest

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApi {

    @GET(MUSIC_GENRE_ROCK)
    fun getRockArtistsMusic(): Single<ArtistsMusicModelList>

    @GET(MUSIC_GENRE_CLASSIC)
    fun getClassicArtistsMusic(): Single<ArtistsMusicModelList>

    @GET(MUSIC_GENRE_POP)
    fun getPopArtistsMusic(): Single<ArtistsMusicModelList>

    fun getArtistsMusic(
        @Query("term") musicGenre: String,

        @Query("media") media: String = MEDIA,

        @Query("entity") entity: String = ENTITY,

        @Query("limit") limit: String = LIMIT
    ): Single<ArtistsMusicModelList>

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