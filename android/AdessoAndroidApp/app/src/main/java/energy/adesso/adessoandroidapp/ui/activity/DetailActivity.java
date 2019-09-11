package energy.adesso.adessoandroidapp.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.model.MeterKind;
import energy.adesso.adessoandroidapp.logic.model.exception.AdessoException;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Meter;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Reading;
import energy.adesso.adessoandroidapp.ui.adapter.ReadingAdapter;

public class DetailActivity extends AppCompatActivity {
    Activity a = this;
    ReadingAdapter listAdapter;
    List<Reading> readings;
    Meter m;

    String meterKey = "METER_KEY";

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
          m = (Meter)savedInstanceState.getSerializable(meterKey);
          try {
            // TODO: Async
            readings = m.getReadings();
          } catch (AdessoException e) {
            e.printStackTrace();
          }
        }
        setContentView(R.layout.activity_detail);

        // Set up toolbar
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Setting the onClickEvent in XML results in an error
        CardView cardButton = ((CardView)findViewById(R.id.cardButton));
        cardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNewEntryClick(view);
            }
        });

        if (getIntent().hasExtra("meter"))
          m = (Meter)getIntent().getSerializableExtra("meter");
        updateTitleInfo();

        listReadings();
    }
    public void onNewEntryClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.new_input_title);

        // Set up textbox
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setPadding(24,24,24,24);
        input.layout(24,24,24,24);
        builder.setView(input);

        // Set up events
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newReading = input.getText().toString();
              // TODO: Async
                try { m.createReading(newReading);
                } catch (AdessoException e) {
                    Toast.makeText(a, R.string.generic_error_message, Toast.LENGTH_SHORT).show();
                }
                listReadings();

                // TODO: Thread network calls?
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
    public void onEditClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.detail_edit_name_title);
        //builder.setMessage(R.string.detail_edit_name_text);

        // Set up textbox
        LinearLayout l = (LinearLayout)getLayoutInflater().
                inflate(R.layout.dialog_edit, null);
        final EditText input = (EditText)l.findViewById(R.id.name);
        input.setText(m.getName());
        builder.setView(l);

        // Set up events
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                  // TODO: Async
                    m.setName(input.getText().toString());
                } catch (AdessoException e) {
                    Toast.makeText(a, R.string.generic_error_message,
                            Toast.LENGTH_SHORT).show();
                }
                updateTitleInfo();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
    public void onGraphClick(View view) {
      String unit = null;
      if (m.getKind().equals(MeterKind.ELECTRIC))
        unit = getString(R.string.elecUnit);
      else if (m.getKind().equals(MeterKind.GAS))
        unit = getString(R.string.gasUnit);
      else if (m.getKind().equals(MeterKind.WATER))
        unit = getString(R.string.waterUnit);

      startActivity(new Intent(this, GraphActivity.class).
          putExtra("readings", readings.toArray()).
          putExtra("unit", unit));
    }
    @Override public void onSaveInstanceState(Bundle outState) {
      outState.putSerializable(meterKey, m);
      super.onSaveInstanceState(outState);
    }
    @Override public void onRestoreInstanceState(Bundle savedInstanceState) { }

    void listReadings() {
        AdapterView.OnItemClickListener onAdapterElementClick = new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            showCorrectDialog(position);
          }
        };

        // Get the icon and set the unit TextView
        Drawable icon = null;
        TextView unit = ((TextView)findViewById(R.id.listElementRightText));
        if (m.getKind().equals(MeterKind.ELECTRIC)) {
            icon = getDrawable(R.drawable.icon_electricity);
            unit.setText(R.string.elecUnit);
        }
        else if (m.getKind().equals(MeterKind.GAS)) {
            icon = getDrawable(R.drawable.icon_gas);
            unit.setText(R.string.gasUnit);
        }
        else if (m.getKind().equals(MeterKind.WATER)) {
            icon = getDrawable(R.drawable.icon_water);
            unit.setText(R.string.waterUnit);
        }

        try {
            // Init the adapter and the list
          // TODO: Async
            readings = m.getReadings();
            listAdapter = new ReadingAdapter(this.getBaseContext(), readings, icon);
            ListView detailList = findViewById(R.id.detail_list);
            detailList.setAdapter(listAdapter);
            detailList.setOnItemClickListener(onAdapterElementClick);
            detailList.scrollTo(0,0);
        } catch (AdessoException e) {
            Toast.makeText(this, R.string.generic_error_message, Toast.LENGTH_SHORT).show();
        }
    }
    void showCorrectDialog(final int position) {
      AlertDialog.Builder builder = new AlertDialog.Builder(a);
      builder.setTitle(R.string.detail_correct_dialog_title);
      builder.setMessage(R.string.detail_correct_dialog_desc);

      // Set up textbox
      LinearLayout l = (LinearLayout)getLayoutInflater().
          inflate(R.layout.dialog_edit, null);
      final EditText input = (EditText)l.findViewById(R.id.name);
      input.setText(readings.get(position).getValue());
      ((TextView)l.findViewById(R.id.listElementBottomText)).setText("");
      builder.setView(l);

      // Set up events
      builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          try {
            // TODO: Async
            readings.get(position).correct(input.getText().toString());
          } catch (AdessoException e) {
            Toast.makeText(a, R.string.generic_error_message,
                Toast.LENGTH_SHORT).show();
          }
          listReadings();
        }
      });
      builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          dialog.cancel();
        }
      });

      builder.show();
    }
    void updateTitleInfo() {
        ((TextView)findViewById(R.id.name)).setText(m.getName());
        ((TextView)findViewById(R.id.number)).setText(m.getMeterNumber());
        if (m.getKind().equals(MeterKind.ELECTRIC))
            ((TextView)findViewById(R.id.usage)).setText(m.getLastReading().getValue() +  " " + getString(R.string.elecUnit));
        else if (m.getKind().equals(MeterKind.GAS))
            ((TextView)findViewById(R.id.usage)).setText(m.getLastReading().getValue() +  " " + getString(R.string.gasUnit));
        else if (m.getKind().equals(MeterKind.WATER))
            ((TextView)findViewById(R.id.usage)).setText(m.getLastReading().getValue() +  " " + getString(R.string.waterUnit));
    }
}
