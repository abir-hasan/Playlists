package com.abir.hasan.androidtdd.ui.playlist

import com.abir.hasan.androidtdd.data.Playlist
import com.abir.hasan.androidtdd.data.remote.PlaylistService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
    private val service: PlaylistService
) {

    suspend fun getPlaylists(): Flow<Result<List<Playlist>>> {
        return service.fetchPlaylists()
    }

}
