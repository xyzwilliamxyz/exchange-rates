package com.exchangerates.ui.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.exchangerates.BaseInstrumentationTest
import com.exchangerates.R
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest : BaseInstrumentationTest() {

    @get:Rule
    val activityRule = ActivityTestRule(HomeActivity::class.java, false, false)

    private val mockServer = MockWebServer()

    @Before
    fun before() {
        mockServer.start(8080)
    }

    @After
    fun after() {
        mockServer.shutdown()
    }

    @Test
    fun launchActivityAndCheckCurrencyData_DataIsDisplayedAsExpected() {
        enqueueMockResponse(mockServer, "response_1")

        activityRule.launchActivity(null)
        onView(withId(R.id.tv_pln)).check(matches(withText("PLN")))
        onView(withId(R.id.tv_plnCurrency)).check(matches(withText("4.3038")))

        onView(withId(R.id.tv_usd)).check(matches(withText("USD")))
        onView(withId(R.id.tv_usdCurrency)).check(matches(withText("1.1171")))
    }

    @Test
    fun launchActivityWithApiError_ErrorMessageIsDisplayed() {
        enqueueMockError(mockServer)

        activityRule.launchActivity(null)
        allOf(withId(com.google.android.material.R.id.snackbar_text),
            withText(activityRule.activity.getString(R.string.errorMessage))).matches(isDisplayed())
    }

    @Test
    fun launchActivityWithApiErrorThenClickOnRetry_DataIsDisplayed() {
        enqueueMockError(mockServer)
        enqueueMockResponse(mockServer, "response_1")

        activityRule.launchActivity(null)
        onView(withId(R.id.cl_container)).check(matches(not(isDisplayed())))
        onView(allOf(withId(com.google.android.material.R.id.snackbar_action))).perform(ViewActions.click())
        onView(withId(R.id.cl_container)).check(matches(isDisplayed()))
    }
}
