package energy.adesso.adessoandroidapp;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;

import energy.adesso.adessoandroidapp.ui.activity.LoginActivity;
import energy.adesso.adessoandroidapp.ui.activity.MainActivity;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class IssueTest {
  @Rule
  public IntentsTestRule<MainActivity> mActivity = new IntentsTestRule<>(MainActivity.class);

  @Test
  public void sendIssue() {
    // Navigate to Issue form
    openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
    onView(withText(R.string.issue_toolbar_title))
        .perform(click());

    // Type in data
    onView(withId(R.id.name)).perform(typeText("John Doe"));
    closeSoftKeyboard();
    onView(withId(R.id.email)).perform(typeText("john.doe@gmail.com"));
    closeSoftKeyboard();
    onView(withId(R.id.subject)).perform(replaceText("\uD83D\uDE29\uD83D\uDE29Test1234" +
        "\uD83D\uDE29\uD83D\uDE29"));
    closeSoftKeyboard();
    onView(withId(R.id.message)).perform(typeText("Hello Adesso Team,\n\n" +
        "I'm just testing your software, nothing to worry about.\n" +
        "Please do not respond, or do but im just a test I wont answer.\n\n" +
        "Did you ever hear the tragedy of Darth Plagueis The Wise? I thought not. It’s not a story " +
        "the Jedi would tell you. It’s a Sith legend. Darth Plagueis was a Dark Lord of the Sith, " +
        "so powerful and so wise he could use the Force to influence the midichlorians to create " +
        "life… He had such a knowledge of the dark side that he could even keep the ones he cared " +
        "about from dying. The dark side of the Force is a pathway to many abilities some consider " +
        "to be unnatural. He became so powerful… the only thing he was afraid of was losing his power, " +
        "which eventually, of course, he did. Unfortunately, he taught his apprentice everything he " +
        "knew, then his apprentice killed him in his sleep. Ironic. He could save others from death, " +
        "but not himself. \n\n" +
        "Sincerely,\n" +
        "A test."));
    closeSoftKeyboard();

    onView(withId(R.id.send)).perform(click());


  }
}