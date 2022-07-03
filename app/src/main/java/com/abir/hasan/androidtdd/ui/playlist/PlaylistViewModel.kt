package com.abir.hasan.androidtdd.ui.playlist

import androidx.lifecycle.*
import com.abir.hasan.androidtdd.data.Playlist
import kotlinx.coroutines.flow.onEach

class PlaylistViewModel(
    private val repository: PlaylistRepository
) : ViewModel() {

    val playLists: LiveData<Result<List<Playlist>>> = liveData {
        _loader.postValue(true)
        emitSource(repository.getPlaylists()
            .onEach { _loader.postValue(false) }
            .asLiveData())
    }


    private val _loader: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val loader: LiveData<Boolean>
        get() = _loader

}