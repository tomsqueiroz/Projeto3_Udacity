package com.example.tom.projeto3_udacity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.tom.projeto3_udacity.Activities.MainActivity;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Tom on 30/04/2018.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void clickRecyclerViewItem_OpensDetailActivity(){

        onData(anything()).inAdapterView(withId(R.id.rv_main)).atPosition(1).perform(click());

        onView(withId(R.id.tv_description)).check(matches(withText("Ingredients List:")));


    }

}
