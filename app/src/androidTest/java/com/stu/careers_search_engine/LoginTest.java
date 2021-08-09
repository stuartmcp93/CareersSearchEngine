package com.stu.careers_search_engine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
@RunWith(AndroidJUnit4.class)
public class LoginTest {

    private String username;
    private String password;

    @Rule
    public ActivityScenarioRule<Login> activityRule = new ActivityScenarioRule<>(Login.class);

    @Before
    public void initValidLoginDetails() {
        // Specify a valid login details.
        username = "stuartM";
        password = "password";
    }

    @Test
    public void test_login() {
        onView(withId(R.id.ET_username)).perform(typeText(username),
                ViewActions.closeSoftKeyboard());

        onView(withId(R.id.ET_password)).perform(typeText(password),
                ViewActions.closeSoftKeyboard());

        onView(withId(R.id.BTN_login)).perform(click());

        onView(withId(R.id.home_layout)).check(matches(isDisplayed()));
    }
}
