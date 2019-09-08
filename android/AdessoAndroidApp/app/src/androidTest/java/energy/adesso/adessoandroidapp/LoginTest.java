package energy.adesso.adessoandroidapp;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import energy.adesso.adessoandroidapp.ui.activity.LoginActivity;
import energy.adesso.adessoandroidapp.ui.activity.MainActivity;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {
  @Rule
  public IntentsTestRule<LoginActivity> mActivity = new IntentsTestRule<LoginActivity>(LoginActivity.class);

  @Test
  public void correct_login() {
    closeSoftKeyboard();

    // Simple test to check if the activity is properly loaded, may seem redundant but ive seen things you people wouldn't believe. Attack ships on fire off the shoulder of Orion. I watched c-beams glitter in the dark near the Tannhäuser Gate. All those moments will be lost in time, like tears in rain. Time to die.
    onView(withId(R.id.login)).check(matches(withText(R.string.login_text)));

    onView(withId(R.id.number)).perform(replaceText("1234"));
    onView(withId(R.id.pass)).perform(replaceText("."));

    onView(withId(R.id.login)).perform(click());

    intended(hasComponent(MainActivity.class.getName()));
  }
}

