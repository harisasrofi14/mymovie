package com.example.mymovie.core.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private val RESOURCE: String? ="GLOBAL"
    private val esspressoTestIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment(){
        esspressoTestIdlingResource.increment()
    }
    fun decrement(){
        esspressoTestIdlingResource.decrement()
    }
    fun getEspressoIdlingResource(): IdlingResource {
        return esspressoTestIdlingResource
    }

}