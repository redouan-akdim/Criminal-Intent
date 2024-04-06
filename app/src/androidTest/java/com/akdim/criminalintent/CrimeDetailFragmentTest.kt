package com.akdim.criminalintent

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CrimeDetailFragmentTest{
    private lateinit var scenario: FragmentScenario<CrimeDetailFragment>

    @Before
    fun setUp(){
        // Launch the fragment in container using the appropriate theme
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_CriminalIntent)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun testElementsHookup(){
        lateinit var crime:Crime        // Contains the crime object of the fragment

        // Perform UI actions
        onView(withId(R.id.crime_title)).perform(typeText("Test crime"))
        onView(withId(R.id.crime_solved)).perform(click())

        scenario.onFragment{
                fragment -> crime = fragment.crime          // Access crime object of fragment
        }

        // Check 'state' of the UI elements
        onView(withId(R.id.crime_title)).check(matches(withText(crime.title)))

        onView(withId(R.id.crime_solved)).check(matches(isChecked()))
        assertEquals(crime.isSolved, true)          // Check if isSolved attribute is set to true
    }
}