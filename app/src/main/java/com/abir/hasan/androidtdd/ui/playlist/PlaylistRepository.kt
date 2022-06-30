package com.abir.hasan.androidtdd.ui.playlist

import com.abir.hasan.androidtdd.data.Playlist
import com.abir.hasan.androidtdd.data.remote.PlaylistService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlaylistRepository(
    private val service: PlaylistService
) {

    suspend fun getPlaylists(): Flow<Result<List<Playlist>>> {
        service.fetchPlaylists()
        return flow {  }
    }

}
