package com.stu.careers_search_engine;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * This class tests a user signing up for an account
 *
 * @Result successful user sign up
 * @Author Stuart McPherson
 */
@RunWith(AndroidJUnit4.class)
public class SignUpTest {

    private String username;
    private String password;
    private String password2;

    @Rule
    public ActivityScenarioRule<SignUp> activityRule = new ActivityScenarioRule<>(SignUp.class);


    @Before
    public void initValidLoginDetails() {
        // Specify a valid login details.
        username = "stuartM";
        password = "password";
        password2 = "password";
    }

    @Test
    public void test_signUp() {
        onView(withId(R.id.ET_username)).perform(typeText(username),
                ViewActions.closeSoftKeyboard());

        onView(withId(R.id.ET_password)).perform(typeText(password),
                ViewActions.closeSoftKeyboard());

        onView(withId(R.id.BTN_login)).perform(click());

        onView(withId(R.id.home_layout)).check(matches(isDisplayed()));
    }
}