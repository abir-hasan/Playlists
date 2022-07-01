package com.abir.hasan.androidtdd.data.remote

import com.abir.hasan.androidtdd.data.Playlist
import com.abir.hasan.androidtdd.data.PlaylistRaw
import retrofit2.http.GET

interface PlaylistAPI {

    @GET("playlists")
    suspend fun fetchAllPlaylists(): List<PlaylistRaw>

}
