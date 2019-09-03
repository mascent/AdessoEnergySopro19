package energy.adesso.adessoandroidapp.ui.parents;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import energy.adesso.adessoandroidapp.R;

public abstract class ListActivity extends ActivityDaddy {

    protected void clearList() {
        ((LinearLayout) findViewById(R.id.list)).removeAllViews();
    }

    protected void addListElement(Drawable icon, String place, String number, String usage) {
        addListElement(icon, place, number, usage, null);
    }
    protected void addListElement(Drawable icon, String place, String number, String usage, View.OnClickListener onClick) {
        LinearLayout listElement = (LinearLayout) getLayoutInflater().inflate(R.layout.list_element, null);
        listElement.setOnClickListener(onClick);

        ((ImageView) listElement.getChildAt(0)).setImageDrawable(icon);

        LinearLayout childLayout = ((LinearLayout) listElement.getChildAt(1));
        ((TextView) childLayout.getChildAt(0)).setText(place);
        ((TextView) childLayout.getChildAt(1)).setText(number);

        ((TextView) listElement.getChildAt(2)).setText(usage);

        ((LinearLayout) findViewById(R.id.list)).addView(listElement);
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

    protected String getListElementNumber(View view) {
        return ((TextView)
                ((LinearLayout) ((LinearLayout)view).getChildAt(1) ).
                getChildAt(1) ).
                getText().
                toString();
    }
    protected String getListElementUsage(View view) {
        return ((TextView) ((LinearLayout)view).getChildAt(2) ).
                getText().
                toString();
    }

}
