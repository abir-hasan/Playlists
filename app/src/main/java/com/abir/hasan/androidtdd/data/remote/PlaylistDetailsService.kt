package com.abir.hasan.androidtdd.data.remote

import com.abir.hasan.androidtdd.data.PlaylistDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaylistDetailsService @Inject constructor() {

    suspend fun fetchPlaylistDetails(playlistId: String): Flow<Result<PlaylistDetails>> {
        TODO("Not yet implemented")
    }
}