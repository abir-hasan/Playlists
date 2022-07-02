package com.abir.hasan.androidtdd.details

import com.abir.hasan.androidtdd.data.PlaylistDetails
import com.abir.hasan.androidtdd.data.remote.PlaylistAPI
import com.abir.hasan.androidtdd.data.remote.PlaylistDetailsService
import com.abir.hasan.androidtdd.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class PlaylistDetailsServiceShould : BaseUnitTest() {

    private lateinit var playlistDetailsService: PlaylistDetailsService
    private val api: PlaylistAPI = mock()
    private val id = "1"
    private val playlistDetails: PlaylistDetails = mock()

    @Test
    fun fetchPlaylistDetailsFromAPI() = runTest {
        playlistDetailsService = mockSuccessfulCase()
        playlistDetailsService.fetchPlaylistDetails(id).single() // Make sure api call is made
        verify(api, times(1)).fetchPlaylistDetailsWithId(id)
    }

    @Test
    fun convertValuesToFlowResultAndEmitThem() = runTest {
        playlistDetailsService = mockSuccessfulCase()
        assertEquals(
            Result.success(playlistDetails),
            playlistDetailsService.fetchPlaylistDetails(id).first()
        )
    }

    @Test
    fun emitErrorResultWhenNetworkFails() = runTest {
        playlistDetailsService = mockErrorCase()
        assertEquals(
            "Something went wrong",
            playlistDetailsService.fetchPlaylistDetails(id).first().exceptionOrNull()?.message
        )
    }

    private suspend fun mockErrorCase(): PlaylistDetailsService {
        whenever(api.fetchPlaylistDetailsWithId(id)).thenThrow(RuntimeException("Something went wrong"))
        return PlaylistDetailsService(api)
    }

    private suspend fun mockSuccessfulCase(): PlaylistDetailsService {
        whenever(api.fetchPlaylistDetailsWithId(id)).thenReturn(playlistDetails)
        return PlaylistDetailsService(api)
    }

}