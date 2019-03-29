package com.example.bmi


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val heightEditText = onView(withId(R.id.editText))
        heightEditText.perform(replaceText("170"), closeSoftKeyboard())

        val weightEditText = onView(withId(R.id.editText2))
        weightEditText.perform(replaceText("65"), closeSoftKeyboard())

        val appCompatButton = onView(withId(R.id.button))
        appCompatButton.perform(click())

        val textView = onView(
                withId(R.id.textView2))
        textView.check(matches(withText("22.49")))
    }
}
