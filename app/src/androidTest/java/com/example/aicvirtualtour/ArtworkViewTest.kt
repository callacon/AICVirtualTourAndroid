package com.example.aicvirtualtour

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Test

class ArtworkViewTest: TestBase() {
    @Test
    fun artworkListSelection_DisplaysInArtworkView() {
        ActivityScenario.launch(MainActivity::class.java)
        // Select a department
        onView(withText("Modern Art")).waitUntilVisible(10000).perform(click())
        // Select an artwork
        onView(withText("The Old Guitarist")).waitUntilVisible(10000).perform(click())
        // Check art data is present
        onView(withText("The Old Guitarist")).waitUntilVisible(10000).check(matches(isDisplayed()))
        onView(withText("Pablo Picasso\nSpanish, active France, 1881–1973")).waitUntilVisible(10000).check(matches(isDisplayed()))
        onView(withText("Oil on panel")).waitUntilVisible(10000).check(matches(isDisplayed()))


    }
}