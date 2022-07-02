package com.abir.hasan.androidtdd.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abir.hasan.androidtdd.data.PlaylistDetails
import com.abir.hasan.androidtdd.data.remote.PlaylistDetailsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistDetailsViewModel @Inject constructor(
    private val service: PlaylistDetailsService
) : ViewModel() {

    private val _playListDetails: MutableLiveData<Result<PlaylistDetails>> by lazy {
        MutableLiveData()
    }
    val playListDetails: LiveData<Result<PlaylistDetails>>
        get() = _playListDetails

    fun getPlaylistDetails(playlistId: String) {
        viewModelScope.launch {
            service.fetchPlaylistDetails(playlistId).collect {
                _playListDetails.postValue(it)
            }
        }
    }

}