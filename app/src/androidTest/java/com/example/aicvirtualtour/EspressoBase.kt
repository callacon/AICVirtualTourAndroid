package com.example.aicvirtualtour

import android.util.Log
import android.view.KeyEvent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Test
import java.util.concurrent.TimeoutException

/**
 * Base class for espresso tests
 */
abstract class EspressoBase {
    /**
     * Utility function to wait for a view to appear.
     * Sometimes Espresso gets antsy and tried to tap a view before the API returns its data.
     * Found in this discussion: https://stackoverflow.com/questions/21417954/espresso-thread-sleep
     */
    fun ViewInteraction.waitUntilVisible(timeout: Long): ViewInteraction {
        val startTime = System.currentTimeMillis()
        val endTime = startTime + timeout

        do {
            try {
                check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                return this
            } catch (e: NoMatchingViewException) {
                Thread.sleep(50)
            }
        } while (System.currentTimeMillis() < endTime)

        throw TimeoutException()
    }
}