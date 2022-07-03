package com.abir.hasan.androidtdd.details

import com.abir.hasan.androidtdd.data.PlaylistDetails
import com.abir.hasan.androidtdd.data.remote.PlaylistDetailsService
import com.abir.hasan.androidtdd.ui.details.PlaylistDetailsViewModel
import com.abir.hasan.androidtdd.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import petros.efthymiou.groovy.utils.captureValues
import petros.efthymiou.groovy.utils.getValueForTest

@ExperimentalCoroutinesApi
class PlaylistDetailsViewModelShould : BaseUnitTest() {

    private val service: PlaylistDetailsService = mock()
    private lateinit var viewModel: PlaylistDetailsViewModel
    private val playlistId = "1"
    private val playlistDetails: PlaylistDetails = mock()
    private val expected = Result.success(playlistDetails)
    private val error = Result.failure<PlaylistDetails>(RuntimeException("Something went wrong"))

    @Test
    fun getPlaylistDetailsFromService() = runTest {
        mockSuccessfulCase()
        viewModel.getPlaylistDetails(playlistId)
        //viewModel.playListDetails.getValueForTest()
        verify(service, times(1)).fetchPlaylistDetails(playlistId)
    }

    @Test
    fun emitPlaylistDetailsFromService() = runTest {
        mockSuccessfulCase()
        viewModel.getPlaylistDetails(playlistId)
        // service.fetchPlaylistDetails(playlistId).first() //Required if viewModelScope switches dispatcher
        assertEquals(expected, viewModel.playListDetails.getValueForTest())
    }

    @Test
    fun emitErrorWhenServiceFails() = runTest {
        mockErrorCase()
        assertEquals(error, viewModel.playListDetails.getValueForTest())
    }

    @Test
    fun showLoaderWhileFetchingDetails() = runTest {
        mockSuccessfulCase()
        viewModel.detailsLoader.captureValues {
            viewModel.getPlaylistDetails(playlistId)
            assertEquals(true, values[0])
        }
    }

    @Test
    fun hideLoaderAfterPlaylistDetailsHasBeenFetched() = runTest {
        mockSuccessfulCase()
        viewModel.detailsLoader.captureValues {

            viewModel.getPlaylistDetails(playlistId)
            assertEquals(false, values.last())
        }
    }

    private suspend fun mockErrorCase() {
        whenever(service.fetchPlaylistDetails(playlistId)).thenReturn(
            flow { emit(error) }
        )
        viewModel = PlaylistDetailsViewModel(service)
        viewModel.getPlaylistDetails(playlistId)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(service.fetchPlaylistDetails(playlistId)).thenReturn(
            flow { emit(expected) }
        )
        viewModel = PlaylistDetailsViewModel(service)
    }

}