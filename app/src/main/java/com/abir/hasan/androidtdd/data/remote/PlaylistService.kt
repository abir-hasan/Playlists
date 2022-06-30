package com.abir.hasan.androidtdd.data.remote

import com.abir.hasan.androidtdd.data.Playlist
import kotlinx.coroutines.flow.Flow

class PlaylistService {

    suspend fun fetchPlaylists(): Flow<Result<List<Playlist>>> {
        TODO("Not yet implemented")
    }

}
