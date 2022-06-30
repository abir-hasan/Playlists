package com.abir.hasan.androidtdd.data

import com.abir.hasan.androidtdd.R

data class Playlist(
    val id: String,
    val name: String,
    val category: String,
    val image: Int = R.mipmap.ic_playlist
)
