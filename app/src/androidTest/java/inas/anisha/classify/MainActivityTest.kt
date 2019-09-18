package inas.anisha.classify

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class MainActivityTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun showUsernameEmptyError() {
        onView(withId(R.id.fragment_main))
        onView(withId(R.id.edit_text_name)).perform(typeText(""))
        onView(withId(R.id.button_submit)).perform(click())
        onView(withId(R.id.input_layout_name)).check(matches(hasTextInputLayoutHintText("Username cannot be empty")))
    }

    @Test
    fun showUsernameTooLongError() {
        onView(withId(R.id.fragment_main))
        onView(withId(R.id.edit_text_name)).perform(typeText("username username username username"))
        onView(withId(R.id.button_submit)).perform(click())
        onView(withId(R.id.input_layout_name)).check(matches(hasTextInputLayoutHintText("Username too long")))
    }

    @Test
    fun goToNextNavigation() {
        onView(withId(R.id.fragment_main))
        onView(withId(R.id.edit_text_name)).perform(typeText("username"))
        onView(withId(R.id.button_submit)).perform(click())
        onView(withId(R.id.fragment_schedule))
    }

    private fun hasTextInputLayoutHintText(expectedErrorText: String): Matcher<View> =
        object : TypeSafeMatcher<View>() {

            override fun describeTo(description: Description?) {}

            override fun matchesSafely(item: View?): Boolean {
                if (item !is TextInputLayout) return false
                val error = item.error ?: return false
                val hint = error.toString()
                return expectedErrorText == hint
            }
        }
}