package com.abir.hasan.androidtdd.playlist

import com.abir.hasan.androidtdd.data.Playlist
import com.abir.hasan.androidtdd.data.remote.PlaylistService
import com.abir.hasan.androidtdd.ui.playlist.PlaylistRepository
import com.abir.hasan.androidtdd.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class PlaylistRepositoryShould : BaseUnitTest() {

    private val service: PlaylistService = mock()
    private val playlists = mock<List<Playlist>>()
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPlaylistsFromService() = runTest {
        val repository = PlaylistRepository(service)
        repository.getPlaylists()
        verify(service, times(1)).fetchPlaylists()
    }

    @Test
    fun emitPlaylistsFromService() = runTest {
        val repository = mockSuccessfulCase()
        assertEquals(playlists, repository.getPlaylists().first().getOrNull())
    }

    private suspend fun mockSuccessfulCase(): PlaylistRepository {
        whenever(service.fetchPlaylists()).thenReturn(
            flow<Result<List<Playlist>>> {
                emit(Result.success(playlists))
            }
        )
        return PlaylistRepository(service)
    }

    @Test
    fun propagateErrors() = runTest {
        val repository = mockFailureCase()
        assertEquals(exception, repository.getPlaylists().first().exceptionOrNull())
    }

    private suspend fun mockFailureCase(): PlaylistRepository {
        whenever(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.failure<List<Playlist>>(exception))
            }
        )
        return PlaylistRepository(service)
    }

}