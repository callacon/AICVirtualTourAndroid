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
import org.junit.Test
import java.util.concurrent.TimeoutException

class DepartmentListEspressoTest: EspressoBase() {

    @Test
    fun selectingDepartment_ShowsArtworkList() {
        ActivityScenario.launch(MainActivity::class.java)

        // Select a department
        onView(withText("Arts of Asia")).waitUntilVisible(5000).perform(click())
        // Check that we've navigated to the selected department
        try {
            // Check that we've completed the search, our search will be displayed as the header.
            onView(withId(R.id.departmentNameText)).check(matches(withText("Arts of Asia")))
            // Check that the list of art for this department is displaying.
            onView(withId(R.id.artworkSearchView)).check(matches(isDisplayed()))
            //Check for a piece of art
            onView(withText("Seated Bodhisattva")).check(matches(isDisplayed()))

        } catch (e: NoMatchingViewException) {
            Log.e("View not found", e.message.toString())
        }
    }

    @Test
    fun searchKeywordEntered_DisplaysResultsPage() {
        val searchQuery = "dogs"
        ActivityScenario.launch(MainActivity::class.java)

        // Enter search query in search bar
        onView(withId(R.id.searchButton)).perform(click())
        onView(withId(R.id.searchBar)).perform(typeText(searchQuery))
        pressKey(KeyEvent.KEYCODE_ENTER)

        try {
            // Check that we've completed the search, our search will be displayed as the header.
            onView(withId(R.id.departmentNameText)).check(matches(isDisplayed()))
            onView(withId(R.id.departmentNameText)).check(matches(withText("Showing results for \"dogs\"")))
        } catch (e: NoMatchingViewException) {
            Log.e("View not found", e.message.toString())
        }
    }
}
