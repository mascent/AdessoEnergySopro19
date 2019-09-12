package energy.adesso.adessoandroidapp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.model.MeterKind;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Meter;

public class MeterAdapter extends ArrayAdapter<Meter> {
  private final Context context;
  private final List<Meter> values;
  private final Drawable[] icons;

  public MeterAdapter(Context context, List<Meter> values, Drawable[] icons) {
    super(context, R.layout.list_element, values);
    this.context = context;
    this.values = values;
    this.icons = icons;

    if (icons.length != 3)
      throw new IllegalArgumentException();
  }

  @SuppressLint("ViewHolder")
  @NotNull
  @Override
  public View getView(int position, View convertView, @NotNull ViewGroup parent) {
    // Inflate list element
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    assert inflater != null;
    LinearLayout listElement = (LinearLayout) inflater.inflate(R.layout.list_element, parent, false);

    // Get data
    Meter m = values.get(position);
    Drawable icon = null;
    if (m.getKind().equals(MeterKind.ELECTRIC))
      icon = icons[0];
    else if (m.getKind().equals(MeterKind.GAS))
      icon = icons[1];
    else if (m.getKind().equals(MeterKind.WATER))
      icon = icons[2];

    // Set data
    ((ImageView) listElement.findViewById(R.id.listElementImage)).setImageDrawable(icon);
    ((TextView) listElement.findViewById(R.id.listElementTopText)).setText(m.getName());
    ((TextView) listElement.findViewById(R.id.listElementBottomText)).setText(m.getMeterNumber());
    ((TextView) listElement.findViewById(R.id.listElementRightText)).setText(m.getLastReading().getValue());

    // Set id for easier testing
    listElement.setId(position + 1);

    return listElement;
  }
}