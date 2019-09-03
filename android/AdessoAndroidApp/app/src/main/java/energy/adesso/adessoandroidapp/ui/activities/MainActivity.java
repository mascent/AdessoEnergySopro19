package energy.adesso.adessoandroidapp.ui.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.ui.parents.ListActivity;

public class MainActivity extends ListActivity {

    Drawable testIcon;
    final View.OnClickListener onListElementClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Snackbar.make(view, getListElementUsage(view), Snackbar.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        testIcon = getDrawable(R.drawable.logo_drop_circle);

        // TODO: Use this snackbar code somewhere
        //Snackbar.make(view, "OwO wat dis", Snackbar.LENGTH_LONG)
        //                        .setAction("Action", null).show();

        buildTestList();

        // TODO: Get Zähler here
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
