package com.example.mymovie.ui.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.mymovie.R
import com.example.mymovie.core.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {


    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadPopularMovie() {
        onView(withId(R.id.buttonBottomSheetPersistent)).perform(ViewActions.click())
        onView(withText("Popular")).perform(ViewActions.click())
        onView(withId(R.id.rv_movie))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadTopRatedMovie() {
        onView(withId(R.id.buttonBottomSheetPersistent)).perform(ViewActions.click())
        onView(withText("Top Rated")).perform(ViewActions.click())
        onView(withId(R.id.rv_movie))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadNowPlayingMovie() {
        onView(withId(R.id.buttonBottomSheetPersistent)).perform(ViewActions.click())
        onView(withText("Now Playing")).perform(ViewActions.click())
        onView(withId(R.id.rv_movie))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}
