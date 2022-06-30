package com.abir.hasan.androidtdd.ui.playlist

import com.abir.hasan.androidtdd.data.Playlist
import kotlinx.coroutines.flow.Flow

class PlaylistRepository {

    suspend fun getPlaylists(): Flow<Result<List<Playlist>>> {
        TODO("Not yet implemented")
    }

}
