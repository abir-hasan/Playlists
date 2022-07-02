package com.abir.hasan.androidtdd

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.abir.hasan.androidtdd.ui.MainActivity
import com.abir.hasan.androidtdd.ui.playlist.di.idlingResource
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test


class PlaylistFeature : BaseUITest() {

    // ActivityTestRule is deprecated
    val mActivityRule = ActivityScenarioRule(MainActivity::class.java)
        @Rule get

    @Test
    fun displayScreenTitle() {
        assertDisplayed("Playlists")
    }

    @Test
    fun displaysListOfPlaylists() {
        assertRecyclerViewItemCount(R.id.rvPlaylist, 10)

        onView(
            allOf(
                withId(R.id.tvPlaylistName),
                isDescendantOfA(nthChildOf(withId(R.id.rvPlaylist), 0))
            )
        )
            .check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.tvPlaylistCategory),
                isDescendantOfA(nthChildOf(withId(R.id.rvPlaylist), 0))
            )
        )
            .check(matches(withText("rock")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.ivPlaylistImage),
                isDescendantOfA(nthChildOf(withId(R.id.rvPlaylist), 1))
            )
        )
            .check(matches(withDrawable(R.mipmap.ic_playlist)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displaysLoaderWhileFetchingThePlaylist() {
        // Resuming the UI thread
        IdlingRegistry.getInstance().unregister(idlingResource)
        assertDisplayed(R.id.loader)
    }

    @Test
    fun hidesLoader() {
        assertNotDisplayed(R.id.loader)
    }

    @Test
    fun displaysRockImageForRockListItems() {
        onView(
            allOf(
                withId(R.id.ivPlaylistImage),
                isDescendantOfA(nthChildOf(withId(R.id.rvPlaylist), 0))
            )
        )
            .check(matches(withDrawable(R.mipmap.ic_rock)))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.ivPlaylistImage),
                isDescendantOfA(nthChildOf(withId(R.id.rvPlaylist), 3))
            )
        )
            .check(matches(withDrawable(R.mipmap.ic_rock)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun navigateToDetailScreen() {
        onView(
            allOf(
                withId(R.id.ivPlaylistImage),
                isDescendantOfA(nthChildOf(withId(R.id.rvPlaylist), 0))
            )
        )
            .perform(click())
        assertDisplayed(R.id.playlistDetailsRoot)
    }
}