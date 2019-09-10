package energy.adesso.adessoandroidapp;

import android.view.View;
import android.view.ViewGroup;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;

import energy.adesso.adessoandroidapp.ui.activity.DetailActivity;
import energy.adesso.adessoandroidapp.ui.activity.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static energy.adesso.adessoandroidapp.Matchers.EspressoTestsMatchers.withDrawable;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class ListElementClickTest {
  @Rule
  public IntentsTestRule<MainActivity> mActivity = new IntentsTestRule<MainActivity>(MainActivity.class);

  @Test
  public void clickElec() {
    if (hasChildren(R.id.elecList)) {
      onView(allOf(
          isDescendantOfA(withId(R.id.elecList)),
          withId(1))).perform(click());

      // check if meter is passed on
      intended(allOf(
              hasComponent(DetailActivity.class.getName()),
              hasExtraWithKey("meter")));

      // check for right icon
      onView(allOf(withId(R.id.listElementImage), isDescendantOfA(withId(1)))).
          check(matches(withDrawable(R.drawable.icon_electricity)));

      // check for right unit string
      onView(allOf(withId(R.id.listElementRightText), isDescendantOfA(withId(R.id.DetailListTitle)))).
          check(matches(withText(R.string.elecUnit)));
    }
  }

  @Test
  public void clickGas() {
    if (hasChildren(R.id.GasList)) {
      onView(allOf(
          isDescendantOfA(withId(R.id.GasList)),
          withId(1))).perform(click());

      // check if meter is passed on
      intended(allOf(
              hasComponent(DetailActivity.class.getName()),
              hasExtraWithKey("meter")));

      // check for right icon
      onView(allOf(withId(R.id.listElementImage), isDescendantOfA(withId(1)))).
          check(matches(withDrawable(R.drawable.icon_gas)));

      // check for right unit string
      onView(allOf(withId(R.id.listElementRightText), isDescendantOfA(withId(R.id.DetailListTitle)))).
          check(matches(withText(R.string.gasUnit)));
    }
  }

  @Test
  public void clickWater() {
    if (hasChildren(R.id.WaterList)) {
      // click on first element of the WaterList
      onView(allOf(
          isDescendantOfA(withId(R.id.WaterList)),
          withId(1))).
              perform(click());

      // check if a meter has been passed on
      intended(allOf(
              hasComponent(DetailActivity.class.getName()),
              hasExtraWithKey("meter")));

      // check for right icon
      onView(allOf(withId(R.id.listElementImage), isDescendantOfA(withId(1)))).
          check(matches(withDrawable(R.drawable.icon_water)));

      // check for right unit string
      onView(allOf(
              withId(R.id.listElementRightText),
              isDescendantOfA(withId(R.id.DetailListTitle)))).
          check(matches(withText(R.string.waterUnit)));
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
