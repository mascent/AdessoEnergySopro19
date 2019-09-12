package energy.adesso.adessoandroidapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.test.espresso.core.internal.deps.guava.collect.Iterables;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import energy.adesso.adessoandroidapp.logic.controller.MainController;
import energy.adesso.adessoandroidapp.logic.controller.PersistenceController;
import energy.adesso.adessoandroidapp.logic.model.exception.AdessoException;
import energy.adesso.adessoandroidapp.ui.activity.LoginActivity;
import energy.adesso.adessoandroidapp.ui.activity.MainActivity;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class LoginTest extends AdessoTest {
  @Rule
  public IntentsTestRule<LoginActivity> mActivity = new IntentsTestRule<LoginActivity>(LoginActivity.class) {
    @Override
    protected void beforeActivityLaunched() {
      MainController.setUsePersistence(false);
    }
  };

  @Test
  public void correctLogin() {
    closeSoftKeyboard();

    // Simple test to check if the activity is properly loaded, may seem redundant but ive seen things you people wouldn't believe. Attack ships on fire off the shoulder of Orion. I watched c-beams glitter in the dark near the Tannhäuser Gate. All those moments will be lost in time, like tears in rain. Time to die.
    onView(withId(R.id.login)).check(matches(withText(R.string.login_text)));

    onView(withId(R.id.number)).perform(replaceText("jd172"));
    onView(withId(R.id.pass)).perform(replaceText("password"));

    onView(withId(R.id.login)).perform(click());

    intended(hasComponent(MainActivity.class.getName()));
  }

  @Test
  public void falseLogin() {
    pressBack();
    onView(allOf(withText(R.string.ok), isDisplayed())).perform(click());

    closeSoftKeyboard();

    onView(withId(R.id.number)).perform(replaceText("E"));
    onView(withId(R.id.pass)).perform(replaceText("..."));

    onView(withId(R.id.login)).perform(click());

    onView(withText(R.string.wrong_login)).
        inRoot(withDecorView(not(is(mActivity.getActivity().
            getWindow().getDecorView())))).check(matches(isDisplayed()));
  }
}

