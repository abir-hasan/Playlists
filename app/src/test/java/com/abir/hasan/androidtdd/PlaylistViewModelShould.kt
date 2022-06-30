package com.abir.hasan.androidtdd

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.abir.hasan.androidtdd.ui.playlist.PlaylistRepository
import com.abir.hasan.androidtdd.ui.playlist.PlaylistViewModel
import com.abir.hasan.androidtdd.utils.MainCoroutineScopeRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import petros.efthymiou.groovy.utils.getValueForTest

@ExperimentalCoroutinesApi
class PlaylistViewModelShould {

    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    // This rule allows the execution of live data instantly
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: PlaylistRepository = mock()
    private val viewModel: PlaylistViewModel = PlaylistViewModel(repository)

    @Test
    fun getPlaylistsFroRepository() {
        viewModel.playlists.getValueForTest()
        verify(repository, times(1)).getPlaylists()
    }

}