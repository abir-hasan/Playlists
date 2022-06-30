package com.abir.hasan.androidtdd.data.remote

import com.abir.hasan.androidtdd.data.Playlist

interface PlaylistAPI {

    suspend fun fetchAllPlaylists(): List<Playlist> {
        TODO("Not yet implemented")
    }

}
