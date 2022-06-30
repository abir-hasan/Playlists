package com.abir.hasan.androidtdd.data.remote

import com.abir.hasan.androidtdd.data.Playlist
import retrofit2.http.GET

interface PlaylistAPI {

    @GET("playlists")
    suspend fun fetchAllPlaylists(): List<Playlist>

}
