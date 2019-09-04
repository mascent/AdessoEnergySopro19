package energy.adesso.adessoandroidapp.ui.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Toast;


import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.model.MeterKind;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Meter;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Reading;
import energy.adesso.adessoandroidapp.ui.parents.ListActivity;

public class MainActivity extends ListActivity {

    Drawable testIcon;
    List<Meter> meters;
    final Activity a = this;
    final View.OnClickListener onListElementClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(a, DetailActivity.class);
            intent.putExtra("number", getListElementNumber(view));
            intent.putExtra("usage", getListElementUsage(view));
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        testIcon = getDrawable(R.drawable.logo_drop_circle);

        DateTime time = DateTime.now();
        Reading lastReading = new Reading("id1","id","id2","123456");

        showData(Arrays.asList(new Meter[] {
                new Meter("id",time, time, time,
                        "Hauptsitz", "98 762 244", MeterKind.ELECTRIC, "einowner", lastReading),
                new Meter("id",time, time, time,
                "Hauptsitz", "98 762 244", MeterKind.ELECTRIC, "einowner", lastReading),
                new Meter("id",time, time, time,
                "Hauptsitz", "98 762 244", MeterKind.ELECTRIC, "einowner", lastReading),
                new Meter("id",time, time, time,
                "Hauptsitz", "98 762 244", MeterKind.ELECTRIC, "einowner", lastReading),
                new Meter("id",time, time, time,
                "Hauptsitz", "98 762 244", MeterKind.ELECTRIC, "einowner", lastReading),

        }));
        try {

        } catch (Exception e) {
            Toast.makeText(this, "Couldn't get meters!", Toast.LENGTH_LONG);
        }
    }

    public void onFABClick(View view) {
        final Activity t = this;
        new AlertDialog.Builder(this)
                .setTitle("Measurement")
                .setMessage("How do you want to measure your meter?")
                .setCancelable(true)
                .setPositiveButton("Take a photo", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(t, R.string.not_implemented_message, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Select from Gallery", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        openGallery();
                    }
                })
                .setIcon(R.drawable.logo_drop_circle)
                .show();
    }

    void showData(List<Meter> meters) {
        clearList();

        List<Meter> electricMeters = new ArrayList<Meter>();
        List<Meter> gasMeters = new ArrayList<Meter>();
        List<Meter> waterMeters = new ArrayList<Meter>();

        for (Meter m : meters)
        {
            if (m.getKind() == MeterKind.ELECTRIC)
                electricMeters.add(m);
            else if (m.getKind() == MeterKind.GAS)
                gasMeters.add(m);
            else if (m.getKind() == MeterKind.WATER)
                waterMeters.add(m);
        }

        addListTitle("Strom", "kWh");
        for (Meter m : electricMeters)
            addListElement(getDrawable(R.drawable.icon_electricity), m.getName(), m.getMeterNumber(), m.getLastReading(), onListElementClick);
        addListLine();
        addListTitle("Gas", "m³");
        for (Meter m : gasMeters)
            addListElement(getDrawable(R.drawable.icon_gas), m.getName(), m.getMeterNumber(), m.getLastReading(), onListElementClick);
        addListLine();
        addListTitle("Wasser", "m³");
        for (Meter m : waterMeters)
            addListElement(getDrawable(R.drawable.icon_water), m.getName(), m.getMeterNumber(), m.getLastReading(), onListElementClick);

        Log.println(Log.INFO, "", "Added list elements");
    }
    void buildTestList() {
        int i = 12345;

        addListTitle("Title1", "Unit1");
        addListElement(testIcon, "Hauptsitz6", "98 765 434", Integer.toString(i++), onListElementClick);
        addListElement(testIcon, "element2", "98 765 434", Integer.toString(i++), onListElementClick);
        addListLine();
        addListTitle("Title2", "Unit2");
        addListElement(testIcon, "1Hauptsitz", "98 765 434", Integer.toString(i++), onListElementClick);
        addListElement(testIcon, "element2", "98 765 434", Integer.toString(i++), onListElementClick);
        addListLine();
        addListTitle("Title3", "Unit3");
        addListElement(testIcon, "2Hauptsitz", "98 765 434", Integer.toString(i++), onListElementClick);
        addListElement(testIcon, "element2", "98 765 434", Integer.toString(i++), onListElementClick);
        addListLine();
        addListTitle("Title4", "Unit4");
        addListElement(testIcon, "2Hauptsitz", "98 765 434", Integer.toString(i++), onListElementClick);
        addListElement(testIcon, "element2", "98 765 434", Integer.toString(i++), onListElementClick);
        addListLine();
        addListTitle("Title5", "Unit5");
        addListElement(testIcon, "2Hauptsitz", "98 765 434", Integer.toString(i++), onListElementClick);
        addListElement(testIcon, "element2", "98 765 434", Integer.toString(i++), onListElementClick);
        addListLine();
        addListTitle("Title6", "Unit6");
        addListElement(testIcon, "2Hauptsitz", "98 765 434", Integer.toString(i++), onListElementClick);
        addListElement(testIcon, "element2", "98 765 434", Integer.toString(i++), onListElementClick);
    }
}
