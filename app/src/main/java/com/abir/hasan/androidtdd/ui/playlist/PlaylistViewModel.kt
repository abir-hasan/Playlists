package com.abir.hasan.androidtdd.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.abir.hasan.androidtdd.data.Playlist

class PlaylistViewModel(
    private val repository: PlaylistRepository
) : ViewModel() {

    private val _playLists = liveData<Result<List<Playlist>>> {
        emitSource(repository.getPlaylists().asLiveData())
    }

    val playlists: LiveData<Result<List<Playlist>>>
        get() = _playLists


}