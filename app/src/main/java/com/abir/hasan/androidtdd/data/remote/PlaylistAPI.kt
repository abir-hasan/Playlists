package com.abir.hasan.androidtdd.data.remote

import com.abir.hasan.androidtdd.data.PlaylistDetails
import com.abir.hasan.androidtdd.data.PlaylistRaw
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaylistAPI {

    @GET("playlists")
    suspend fun fetchAllPlaylists(): List<PlaylistRaw>

    @GET("playlist-details/{id}")
    suspend fun fetchPlaylistDetailsWithId(@Path("id") id: String): PlaylistDetails

}
