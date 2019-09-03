package energy.adesso.adessoandroidapp.ui.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.controller.MainController;
import energy.adesso.adessoandroidapp.logic.model.exception.AdessoException;
import energy.adesso.adessoandroidapp.logic.model.internal.Meter;
import energy.adesso.adessoandroidapp.logic.model.internal.MeterKind;
import energy.adesso.adessoandroidapp.ui.parents.ListActivity;

import org.mockito.*;

public class MainActivity extends ListActivity {

    Drawable testIcon;
    Activity a = this;
    List<Meter> meters;

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

        //buildTestList();

        // TODO: Get Zähler here
        try {
            MainController m = Mockito.mock

            meters = MainController.getInstance().getOverview();

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
                addListElement(getDrawable(R.drawable.icon_electricity), m.getName(), m.getMeterNumber(), m.getLastReading());
            addListLine();
            addListTitle("Gas", "m³");
            for (Meter m : gasMeters)
                addListElement(getDrawable(R.drawable.icon_gas), m.getName(), m.getMeterNumber(), m.getLastReading());
            addListLine();
            addListTitle("Wasser", "m³");
            for (Meter m : waterMeters)
                addListElement(getDrawable(R.drawable.icon_water), m.getName(), m.getMeterNumber(), m.getLastReading());
        } catch (AdessoException e) {
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
