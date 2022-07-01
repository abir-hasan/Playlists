package com.abir.hasan.androidtdd.data

import com.abir.hasan.androidtdd.R
import javax.inject.Inject

class PlaylistMapper @Inject constructor() : Function1<List<PlaylistRaw>, List<Playlist>> {

    override fun invoke(playlistRaw: List<PlaylistRaw>): List<Playlist> {
        return playlistRaw.map {
            val image = if (it.category == "rock") R.mipmap.ic_rock else R.mipmap.ic_playlist
            Playlist(it.id, it.name, it.category, image)
        }
    }

}
