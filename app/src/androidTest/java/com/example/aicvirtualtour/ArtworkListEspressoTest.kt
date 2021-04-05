package com.example.aicvirtualtour

import android.util.Log
import android.view.KeyEvent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.not
import org.junit.Test
import java.util.concurrent.TimeoutException

class ArtworkListEspressoTest: EspressoBase() {
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