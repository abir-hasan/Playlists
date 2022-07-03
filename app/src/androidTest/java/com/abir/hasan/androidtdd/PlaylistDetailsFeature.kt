package com.abir.hasan.androidtdd

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.abir.hasan.androidtdd.di.idlingResource
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import org.hamcrest.CoreMatchers.allOf
import org.junit.Test

class PlaylistDetailsFeature : BaseUITest() {

    @Test
    fun displayPlaylistNameAndDetails() {
        navigateToPlaylistDetails()
        /*onView(withId(R.id.tvPlaylistName)).check(matches(withText("Hard Rock Cafe")))
        onView(withId(R.id.tvPlaylistDetails)).check(matches(withText("Rock your senses with this timeless signature vibe list. \n\n • Poison \n • You shook me all night \n • Zombie \n • Rock'n Me \n • Thunderstruck \n • I Hate Myself for Loving you \n • Crazy \n • Knockin' on Heavens Door")))*/
        assertDisplayed("Hard Rock Cafe")
        assertDisplayed("Rock your senses with this timeless signature vibe list. \n\n • Poison \n • You shook me all night \n • Zombie \n • Rock'n Me \n • Thunderstruck \n • I Hate Myself for Loving you \n • Crazy \n • Knockin' on Heavens Door")
    }

    @Test
    fun hidesLoader() {
        navigateToPlaylistDetails()
        assertNotDisplayed(R.id.pbDetails)
    }

    @Test
    fun displaysLoaderWhileFetchingThePlaylistDetails() {
        IdlingRegistry.getInstance().unregister(idlingResource) // Unblocking UI
        // Making sure list api response is collected
        // And unregistering the idling before going to the next screen
        // Otherwise even before unregistering the loader live data
        // emits false. and this test fails. For this extreme case
        // We have to use Thread.sleep
        Thread.sleep(2000)
        navigateToPlaylistDetails()
        assertDisplayed(R.id.pbDetails)
    }

    private fun navigateToPlaylistDetails() {
        onView(
            allOf(
                withId(R.id.ivPlaylistImage), isDescendantOfA(
                    nthChildOf(withId(R.id.rvPlaylist), 0)
                )
            )
        ).perform(click())
    }
}