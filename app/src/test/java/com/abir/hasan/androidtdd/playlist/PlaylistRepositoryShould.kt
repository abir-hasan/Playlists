package com.abir.hasan.androidtdd.playlist

import com.abir.hasan.androidtdd.data.remote.PlaylistService
import com.abir.hasan.androidtdd.ui.playlist.PlaylistRepository
import com.abir.hasan.androidtdd.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class PlaylistRepositoryShould : BaseUnitTest() {

    private val service: PlaylistService = mock()

    @Test
    fun getPlaylistsFromService() = runTest {
        val repository = PlaylistRepository(service)
        repository.getPlaylists()
        verify(service, times(1)).fetchPlaylists()
    }

}