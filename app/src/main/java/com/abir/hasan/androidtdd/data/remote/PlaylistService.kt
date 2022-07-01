package com.abir.hasan.androidtdd.data.remote

import com.abir.hasan.androidtdd.data.Playlist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlaylistService @Inject constructor(private val api: PlaylistAPI) {

    suspend fun fetchPlaylists(): Flow<Result<List<Playlist>>> {
        return flow {
            emit(Result.success(api.fetchAllPlaylists()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}
