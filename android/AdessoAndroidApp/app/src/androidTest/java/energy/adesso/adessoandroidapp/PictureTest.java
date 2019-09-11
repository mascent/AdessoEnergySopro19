package energy.adesso.adessoandroidapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;

import energy.adesso.adessoandroidapp.ui.activity.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PictureTest {
  @Rule
  public IntentsTestRule<MainActivity> mActivity = new IntentsTestRule<MainActivity>(MainActivity.class);

  // We only test photos here because the code is nearly identical for gallery calls besides the intent action
  @Test
  public void takePhoto() {
    // Create a bitmap we can use for our simulated camera image
    Bitmap icon = BitmapFactory.decodeResource(
        InstrumentationRegistry.getInstrumentation().getContext().getResources(),
        R.drawable.test_meter_photo);

    // Build a result to return from the Camera app
    Intent resultData = new Intent();
    resultData.putExtra("data", icon);
    Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

    // Stub the Camera Intent call
    intending(hasAction(equalTo(MediaStore.ACTION_IMAGE_CAPTURE))).respondWith(result);

    // Trigger the onPhotoButton event
    onView(allOf(withId(R.id.fab), isDisplayed())).perform(click());
    onView(allOf(withText(R.string.take_photo), isDisplayed())).perform(click());

    // Validate that an camera intent has been sent
    intended(hasAction(equalTo(MediaStore.ACTION_IMAGE_CAPTURE)));

    // Check for crashes
    assertFalse(mActivity.getActivity().isFinishing());
  }
}
