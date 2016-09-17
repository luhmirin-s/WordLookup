package lv.luhmirin.wordlookup.ui;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import lv.luhmirin.wordlookup.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Check on UI state while dctionary is loading, in a separate file because
 * in this case we do not care about ilding resources.
 */
@RunWith(AndroidJUnit4.class)
public class LookupActivityTestNotLoading {

    @Rule
    public ActivityTestRule<LookupActivity> mActivityTestRule = new ActivityTestRule<>(LookupActivity.class);

    @Test
    public void showsPlaceHolderText_whenLoading() {
        onView(withId(R.id.lookup_placeholder)).check(matches(withText(R.string.lookup_placeholder_loading)));
        onView(withId(R.id.lookup_input)).check(matches(isDisplayed()));
    }

}
