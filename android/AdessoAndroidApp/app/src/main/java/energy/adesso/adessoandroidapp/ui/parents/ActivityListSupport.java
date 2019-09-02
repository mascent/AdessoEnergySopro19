package energy.adesso.adessoandroidapp.ui.parents;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import energy.adesso.adessoandroidapp.R;

public abstract class ActivityListSupport extends ActivityDaddy {

    protected void clearList() {
        ((LinearLayout) findViewById(R.id.list)).removeAllViews();
    }

    protected void addListElement(Drawable icon, String place, String number, String usage) {
        LinearLayout childLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.list_element, null);

        ((ImageView) childLayout.getChildAt(0)).setImageDrawable(icon);

        LinearLayout childsChildLayout = ((LinearLayout) childLayout.getChildAt(1));
        ((TextView) childsChildLayout.getChildAt(0)).setText(place);
        ((TextView) childsChildLayout.getChildAt(1)).setText(number);

        ((TextView) childLayout.getChildAt(2)).setText(usage);

        ((LinearLayout) findViewById(R.id.list)).addView(childLayout);
    }

    protected void addListTitle(String title, String unit) {
        LinearLayout childLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.list_title, null);
        ((TextView) childLayout.getChildAt(0)).setText(title);
        ((TextView) childLayout.getChildAt(1)).setText(unit);
        ((LinearLayout) findViewById(R.id.list)).addView(childLayout);
    }

    protected void addListLine() {
        View childLayout = getLayoutInflater().inflate(R.layout.list_line, null);
        ((LinearLayout) findViewById(R.id.list)).addView(childLayout);
    }
}
