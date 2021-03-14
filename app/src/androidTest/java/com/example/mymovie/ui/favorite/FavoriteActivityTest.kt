package com.example.mymovie.ui.favorite

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.mymovie.R
import com.example.mymovie.core.utils.EspressoIdlingResource
import com.example.mymovie.ui.home.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteActivityTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun openFavoriteMovie() {
        Espresso.onView(ViewMatchers.withId(R.id.buttonBottomSheetPersistent)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("Popular")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        2,
                        ViewActions.click()
                )
        )
        Espresso.onView(ViewMatchers.withId(R.id.card_view_detail_movie))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_review))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_favorite)).perform(ViewActions.click())
        Espresso.onView(isRoot()).perform(pressBack())
        Espresso.onView(ViewMatchers.withId(R.id.mm_favorite)).perform(ViewActions.click())

    }
}