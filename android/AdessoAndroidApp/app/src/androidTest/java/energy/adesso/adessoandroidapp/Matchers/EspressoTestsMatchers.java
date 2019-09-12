package energy.adesso.adessoandroidapp.Matchers;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

// Taken from: https://medium.com/@dbottillo/android-ui-test-espresso-matcher-for-imageview-1a28c832626f
public class EspressoTestsMatchers {

  public static Matcher<View> withDrawable(final int resourceId) {
    return new DrawableMatcher(resourceId);
  }

  public static Matcher<View> noDrawable() {
    return new DrawableMatcher(-1);
  }
}

