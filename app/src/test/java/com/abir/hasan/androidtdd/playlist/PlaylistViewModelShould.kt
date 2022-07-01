package com.abir.hasan.androidtdd.playlist

import com.abir.hasan.androidtdd.data.Playlist
import com.abir.hasan.androidtdd.ui.playlist.PlaylistRepository
import com.abir.hasan.androidtdd.ui.playlist.PlaylistViewModel
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
class PlaylistViewModelShould : BaseUnitTest() {

    private val repository: PlaylistRepository = mock()
    private val playlists = mock<List<Playlist>>()
    private val expected = Result.success(playlists)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPlaylistsFroRepository() = runTest {
        val viewModel = mockSuccessfulCase()
        viewModel.playLists.getValueForTest()
        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitsPlaylistsFromRepository() = runTest {
        val viewModel = mockSuccessfulCase()
        assertEquals(expected, viewModel.playLists.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceivedError() = runTest {
        val viewModel = mockErrorCase()
        assertEquals(exception, viewModel.playLists.getValueForTest()!!.exceptionOrNull())
    }

    @Test
    fun showSpinnerWhileLoading() = runTest {
        val viewModel = mockSuccessfulCase()
        viewModel.loader.captureValues {
            viewModel.playLists.getValueForTest()
            assertEquals(true, values[0])
        }
    }

    @Test
    fun closeLoaderAfterPlaylistLoad() = runTest {
        val viewModel = mockSuccessfulCase()
        viewModel.loader.captureValues {
            viewModel.playLists.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    @Test
    fun closeLoaderAfterError() = runTest {
        val viewModel = mockErrorCase()
        viewModel.loader.captureValues {
            viewModel.playLists.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    private suspend fun mockSuccessfulCase(): PlaylistViewModel {
        whenever(repository.getPlaylists()).thenReturn(
            flow {
                emit(expected)
            }
        )
        return PlaylistViewModel(repository)
    }

    private suspend fun mockErrorCase(): PlaylistViewModel {
        whenever(repository.getPlaylists()).thenReturn(
            flow {
                emit(Result.failure<List<Playlist>>(exception))
            }
        )
        return PlaylistViewModel(repository)
    }

}