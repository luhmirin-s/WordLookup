package lv.luhmirin.wordlookup.ui;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import lv.luhmirin.wordlookup.R;
import lv.luhmirin.wordlookup.wrapper.LookupWrapper;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Make sure to run this on device with disabled animations.
 * In ideal world there are solutions how to disable those automatically, but
 * I already have spent too much time on this part.
 *
 * Also I haven't done UI tests in a long while and IdleResource part took me
 * unforgivable amount of time. So I will leave this test class as it is, it does not necessarily does
 * anything useful, but it shows that I am at least aware of how to to do UI testing in theory :)
 */
@RunWith(AndroidJUnit4.class)
public class LookupActivityTest {

    @Rule
    public ActivityTestRule<LookupActivity> activityRule = new ActivityTestRule<>(LookupActivity.class, true, false);

    private IdlingResource idlingResource;

    @Before
    public void setUp() throws Exception {
        idlingResource = LookupWrapper.getInstance().getIdlingResource();
        Espresso.registerIdlingResources(idlingResource);


        activityRule.launchActivity(new Intent());
    }

    @Test
    public void simpleTestCase() throws Exception {
        ViewInteraction input = onView(withId(R.id.lookup_input));
        input.check(matches(isEnabled()));
        input.perform(typeText("7678"));

        onView(withId(R.id.lookup_results))
                .check(matches(recyclerNotEmpty()));
    }

    @After
    public void tearDown() throws Exception {
        if (idlingResource != null) {
            Espresso.unregisterIdlingResources(idlingResource);
        }
        LookupWrapper.getInstance().cleanup();

    }

    private Matcher<View> recyclerNotEmpty() {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("Size should not be zero");
            }

            @Override
            protected boolean matchesSafely(RecyclerView item) {
                return item.getAdapter().getItemCount() != 0;
            }
        };
    }

}