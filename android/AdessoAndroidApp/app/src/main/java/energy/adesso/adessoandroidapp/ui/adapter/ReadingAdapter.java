package energy.adesso.adessoandroidapp.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.util.List;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.model.MeterKind;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Meter;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Reading;

public class ReadingAdapter extends ArrayAdapter<Reading> {
  private final Context context;
  private final List<Reading> values;
  private final Drawable icon;

  public ReadingAdapter(Context context, List<Reading> values, Drawable icon) {
    super(context, R.layout.list_element, values);
    this.context = context;
    this.values = values;
    this.icon = icon;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    // Inflate list element
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    LinearLayout listElement = (LinearLayout) inflater.inflate(R.layout.list_element,
        parent, false);

    // Set values
    Reading r = values.get(position);
    ((ImageView) listElement.getChildAt(0)).setImageDrawable(icon);

    LinearLayout childLayout = ((LinearLayout) listElement.getChildAt(1));
    ((TextView) childLayout.getChildAt(0)).setText(r.getCreatedAt().toLocalDate().
        toString("dd.MM.yyyy"));
    ((TextView) childLayout.getChildAt(1)).setText("");
    childLayout.getChildAt(1).setLayoutParams(
        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            0, 0.0f));

    ((TextView) listElement.getChildAt(2)).setText(r.getValue());

    listElement.setId(position + 1);
    return listElement;
  }
}