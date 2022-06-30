package com.abir.hasan.androidtdd.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abir.hasan.androidtdd.data.Playlist

class PlaylistViewModel : ViewModel() {

    private val _playLists = MutableLiveData<Result<List<Playlist>>>()

    val playlists: LiveData<Result<List<Playlist>>>
        get() = _playLists

}