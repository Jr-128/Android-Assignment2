package com.example.android_assignment2.rest

import com.example.android_assignment2.models.classic.ClassicMusicModel
import com.example.android_assignment2.models.pop.PopMusicModel
import com.example.android_assignment2.models.rock.RockMusicModel
import io.reactivex.Single
import retrofit2.http.GET

interface MusicApi {
    @GET(MUSIC_GENRE_CLASSIC)
    fun getClassicMusic(): Single<ClassicMusicModel>

    @GET(MUSIC_GENRE_ROCK)
    fun getRockMusic(): Single<RockMusicModel>

    @GET(MUSIC_GENRE_POP)
    fun getPopMusic(): Single<PopMusicModel>

    companion object {
        const val BASE_URL = "https://itunes.apple.com/search/"
        private const val MUSIC_GENRE_CLASSIC =
            "https://itunes.apple.com/search/?term=classic&amp;media=music&amp;entity=song&amp;limit=50"
        private const val MUSIC_GENRE_ROCK =
            "https://itunes.apple.com/search/?term=rock&amp;media=music&amp;entity=song&amp;limit=50"
        private const val MUSIC_GENRE_POP =
            "https://itunes.apple.com/search/?term=pop&amp;media=music&amp;entity=song&amp;limit=50"
    }
}