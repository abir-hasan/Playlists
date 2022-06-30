package com.abir.hasan.androidtdd.playlist

import com.abir.hasan.androidtdd.data.Playlist
import com.abir.hasan.androidtdd.data.remote.PlaylistAPI
import com.abir.hasan.androidtdd.data.remote.PlaylistService
import com.abir.hasan.androidtdd.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class PlaylistServiceShould : BaseUnitTest() {

    private val playlistAPI: PlaylistAPI = mock()
    private val playlists = mock<List<Playlist>>()
    private lateinit var playlistService: PlaylistService

    @Test
    fun fetchAllPlaylistsFromAPI() = runTest {
        playlistService = PlaylistService(playlistAPI)
        playlistService.fetchPlaylists().first() // Making sure api call is made
        verify(playlistAPI, times(1)).fetchAllPlaylists()
    }

    @Test
    fun convertListFromAPIToFlowAndEmitsThem() = runTest {
        playlistService = mockSuccessfulCase()
        assertEquals(Result.success(playlists), playlistService.fetchPlaylists().first())
    }

    private suspend fun mockSuccessfulCase(): PlaylistService {
        whenever(playlistAPI.fetchAllPlaylists()).thenReturn(
            playlists
        )
        return PlaylistService(playlistAPI)
    }

    @Test
    fun emitsErrorResultWhenNetworkFails() = runBlocking {
        playlistService = mockFailureCase()
        assertEquals(
            "Something went wrong",
            playlistService.fetchPlaylists().first().exceptionOrNull()?.message
        )
    }

    private suspend fun mockFailureCase(): PlaylistService {
        whenever(playlistAPI.fetchAllPlaylists()).thenThrow(RuntimeException("Damn backend devs"))
        return PlaylistService(playlistAPI)
    }
}