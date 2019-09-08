package energy.adesso.adessoandroidapp.ui.parents;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.model.MeterKind;

public abstract class ListActivity extends ParentActivity {
    protected LinearLayout getList()
    {
        return (LinearLayout)findViewById(R.id.list);
    }

    protected void clearList() {
        getList().removeAllViews();
    }

    protected void addReadingListElement(MeterKind kin, String date, String usage) {
        Drawable icon = null;
        if (kin.equals(MeterKind.ELECTRIC))
            icon = getDrawable(R.drawable.icon_electricity);
        else if (kin.equals(MeterKind.GAS))
            icon = getDrawable(R.drawable.icon_gas);
        else if (kin.equals(MeterKind.WATER))
            icon = getDrawable(R.drawable.icon_water);

        addMeterListElement(icon, date, "", usage, null);
    }
    protected void addMeterListElement(Drawable icon, String name, String number, String usage, View.OnClickListener onClick) {
        LinearLayout listElement = (LinearLayout) getLayoutInflater().inflate(R.layout.list_element, null);
        listElement.setOnClickListener(onClick);

        ((ImageView) listElement.getChildAt(0)).setImageDrawable(icon);

        LinearLayout childLayout = ((LinearLayout) listElement.getChildAt(1));
        ((TextView) childLayout.getChildAt(0)).setText(name);
        ((TextView) childLayout.getChildAt(1)).setText(number);

        if (number.equals(""))
        {
            ((TextView) childLayout.getChildAt(0)).setTextSize(16);
            childLayout.removeViewAt(1);
        }

        ((TextView) listElement.getChildAt(2)).setText(usage);

        listElement.setId(getList().getChildCount() + 1);
        getList().addView(listElement);
    }
    protected void addListTitle(String title, String unit) {
        LinearLayout childLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.list_title, null);
        ((TextView) childLayout.getChildAt(0)).setText(title);
        ((TextView) childLayout.getChildAt(1)).setText(unit);
        getList().addView(childLayout);
    }
    protected void addListLine() {
        View childLayout = getLayoutInflater().inflate(R.layout.list_line, null);
        getList().addView(childLayout);
    }

    protected String getMeterListElementNumber(View elementView) {
        return ((TextView)
                ((LinearLayout) ((LinearLayout)elementView).getChildAt(1) ).
                getChildAt(1) ).
                getText().
                toString();
    }
}
