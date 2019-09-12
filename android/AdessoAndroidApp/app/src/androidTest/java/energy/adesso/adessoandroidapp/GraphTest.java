package energy.adesso.adessoandroidapp;

import android.view.View;
import android.view.ViewGroup;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;

import energy.adesso.adessoandroidapp.ui.activity.DetailActivity;
import energy.adesso.adessoandroidapp.ui.activity.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static energy.adesso.adessoandroidapp.Matchers.EspressoTestsMatchers.withDrawable;
import static org.hamcrest.core.AllOf.allOf;

public class GraphTest {
  @Rule
  public IntentsTestRule<MainActivity> mActivity = new IntentsTestRule<MainActivity>(MainActivity.class);

  @Test
  // Check if the graph crashes
  public void clickThroughGraph() {
    if (hasChildren(R.id.elecList)) {
      onView(allOf(
          isDescendantOfA(withId(R.id.elecList)),
          withId(1))).perform(click());

      onView(allOf(
          withId(R.id.buttonStonks),
          isDisplayed())).perform(click());

      pressBack();
    }
  }

  private boolean hasChildren(int id) {
    View v = null;
    try {
      v = ((ViewGroup)mActivity.getActivity().findViewById(id)).getChildAt(0);
    } catch (Exception e) { }
    return v != null;
  }
}
