package by.startandroid.weatherforecastdemo

import android.view.Gravity
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.DrawerMatchers.isOpen
import androidx.test.espresso.contrib.NavigationViewActions.navigateTo
import by.startandroid.weatherforecastdemo.ui.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import androidx.test.espresso.contrib.DrawerActions.*
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import by.startandroid.weatherforecastdemo.util.EspressoIdlingResource
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class AppNavigationTest {
    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test
    fun drawerNavigation() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        onView(withId(R.id.drawerLayout)).check(matches(isClosed(Gravity.START))).perform(open())
        onView(withId(R.id.nav_view)).perform(navigateTo(R.id.weatherFragment))
        onView(withId(R.id.fr_container_weather)).check(matches(isDisplayed()))

        onView(withId(R.id.drawerLayout)).check(matches(isClosed(Gravity.START))).perform(open())
        onView(withId(R.id.nav_view)).perform(navigateTo(R.id.listCityFragment))
        onView(withId(R.id.fr_container_list_city)).check(matches(isDisplayed()))

        activityScenario.close()
    }

    @Test
    fun weatherDetailScreen_BackButton() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        onView(withId(R.id.drawerLayout)).check(matches(isClosed(Gravity.START))).perform(open())
        onView(withId(R.id.nav_view)).perform(navigateTo(R.id.weatherFragment))
        onView(withId(R.id.fr_container_weather)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.fr_container_list_city)).check(matches(isDisplayed()))

        activityScenario.close()
    }

    @Test
    fun listCityScreen_clickOnIcon_openNavigation() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        onView(withId(R.id.drawerLayout)).check(matches(isClosed(Gravity.START)))
        onView(withContentDescription(activityScenario.getToolbarNavigationContentDescription())).perform(click())
        onView(withId(R.id.drawerLayout)).check(matches(isOpen(Gravity.START)))

        activityScenario.close()
    }
}