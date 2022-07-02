package com.abir.hasan.androidtdd.data.remote

import com.abir.hasan.androidtdd.data.PlaylistDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlaylistDetailsService @Inject constructor(private val api: PlaylistAPI) {

    suspend fun fetchPlaylistDetails(playlistId: String): Flow<Result<PlaylistDetails>> {
        return flow {
            emit(Result.success(api.fetchPlaylistDetailsWithId(playlistId)))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}