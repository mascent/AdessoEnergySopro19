package energy.adesso.adessoandroidapp.ui.activities;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Meter;
import energy.adesso.adessoandroidapp.ui.parents.ListActivity;

public class DetailActivity extends ListActivity {

    Meter m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
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

        addListTitle("Datum", "kWh");

        // TODO: Improve meter sending/receiving
        m = MainActivity.getMeter(getIntent().getStringExtra("number"));
        ((TextView)findViewById(R.id.name)).setText(m.name);
        ((TextView)findViewById(R.id.number)).setText(m.meterNumber);
        ((TextView)findViewById(R.id.usage)).setText(m.getLastReading().getValue() +  " kWh");

        listReadings();
    }

    public void onNewEntryClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.new_input_title);

        // Set up textbox
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setPadding(24,24,24,24);
        builder.setView(input);

        // Set up events
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newReading = input.getText().toString();
                listReadings();
                // TODO: Add newReading to m
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    void listReadings() {
        clearList();

        // TODO: Populate readings
    }
}
