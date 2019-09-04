package energy.adesso.adessoandroidapp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import energy.adesso.adessoandroidapp.ui.activities.LoginActivity;
import energy.adesso.adessoandroidapp.ui.activities.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MyFirstTest {

    private String stringToBetyped;

    @Rule
    public IntentsTestRule<LoginActivity> intentsRule
            = new IntentsTestRule<>(LoginActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        stringToBetyped = "Espresso";
    }

    @Test
    public void correct_login() {
        onView(withId(R.id.number)).perform(replaceText("1234"));
        onView(withId(R.id.pass)).perform(replaceText("."));
        onView(withId(R.id.login)).perform(click());

        intended(hasComponent(MainActivity.class.getName()));
    }
}
