package com.example.aicvirtualtour

import android.util.Log
import android.view.KeyEvent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.not
import org.junit.Test
import java.util.concurrent.TimeoutException

class ArtworkListViewTest: TestBase() {
    @Test
    fun paginationArrows_GetNextPageOnClick() {
        val firstPageArtwork: List<String> = listOf(
            "Veranda Post (Opo Ogoga)",
            "Coffin and Mummy of Paankhenamun",
            "Male Figure (Nkisi Nkondi)"
        )
        val secondPageArtwork: List<String> = listOf(
            "Mummy Mask",
            "Statue of Horus",
            "Goldweight: Seated Figure"
        )

        ActivityScenario.launch(MainActivity::class.java)

        // Go to Arts of Africa
        onView(withText("Arts of Africa")).waitUntilVisible(5000).perform(click())
        // Check we're on the first page by looking at titles of art
        for (title in firstPageArtwork) {
            onView(withText(title)).check(matches(isDisplayed()))
        }

        // Press arrow to go to second page of artworks in department
        onView(withId(R.id.pageForwardButton)).waitUntilVisible(10000).perform(click())
        // Check we're on the second page by looking at titles of art
        for (title in secondPageArtwork) {
            onView(withText(title)).waitUntilVisible(5000).check(matches(isDisplayed()))
        }
    }

    @Test
    fun paginationArrows_DisabledAtPageLimits() {
        ActivityScenario.launch(MainActivity::class.java)

        // Go to AIC Archives
        onView(withText("AIC Archives")).waitUntilVisible(5000).perform(click())

        // This department only has a single page, so page buttons shouldn't work in either direction.
        onView(withId(R.id.pageForwardButton)).check(matches(not(isClickable())))
        onView(withId(R.id.pageBackButton)).check(matches(not(isClickable())))
    }
}