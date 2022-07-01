package com.abir.hasan.androidtdd.ui.playlist

import androidx.lifecycle.*
import com.abir.hasan.androidtdd.data.Playlist

class PlaylistViewModel(
    private val repository: PlaylistRepository
) : ViewModel() {

    /*private val _playLists = liveData<Result<List<Playlist>>> {
        emitSource(repository.getPlaylists().asLiveData())
    }

    val playlists: LiveData<Result<List<Playlist>>>
        get() = _playLists*/

    val playLists: LiveData<Result<List<Playlist>>> = liveData {
        _loader.postValue(true)
        emitSource(repository.getPlaylists().asLiveData())
    }


    private val _loader: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val loader: LiveData<Boolean>
        get() = _loader

}