package com.abir.hasan.androidtdd.playlist

import com.abir.hasan.androidtdd.R
import com.abir.hasan.androidtdd.data.Playlist
import com.abir.hasan.androidtdd.data.PlaylistMapper
import com.abir.hasan.androidtdd.data.PlaylistRaw
import com.abir.hasan.androidtdd.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

@ExperimentalCoroutinesApi
class PlaylistMapperShould : BaseUnitTest() {

    private val playlistRaw = PlaylistRaw(
        "1", "da name", "default category"
    )
    private val playlistRawRock = PlaylistRaw(
        "1", "da name", "rock"
    )

    private val mapper = PlaylistMapper()

    private val playLists: List<Playlist> = mapper(listOf(playlistRaw))

    private val playlist: Playlist = playLists[0]
    private val playlistRock: Playlist = mapper(listOf(playlistRawRock))[0]

    @Test
    fun keepSameId() {
        assertEquals(playlistRaw.id, playlist.id)
    }

    @Test
    fun keepSameName() {
        assertEquals(playlistRaw.name, playlist.name)
    }

    @Test
    fun keepSameCategory() {
        assertEquals(playlistRaw.category, playlist.category)
    }

    @Test
    fun mapDefaultImageWhenCategoryIsNotRock() {
        assertEquals(R.mipmap.ic_playlist, playlist.image)
    }

    @Test
    fun mapRockImageWhenCategoryIsRock() {
        assertEquals(R.mipmap.ic_rock, playlistRock.image)
    }
}