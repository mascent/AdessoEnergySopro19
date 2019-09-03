package energy.adesso.adessoandroidapp.ui.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.ui.parents.ListActivity;

public class MainActivity extends ListActivity {

    Drawable testIcon;
    final View.OnClickListener onListElementClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.println(Log.ASSERT, "", getListElementUsage(view));
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

    public void onToolbarClick(View view) {
        this.finish();
    }

    public void onFABClick(View view) {
        openGallery();
    }

    void buildTestList() {
        int i = 12345;

        addListTitle("OwO", "UwU");
        addListElement(testIcon, "Hauptsitz6", "98 765 434", Integer.toString(i++), onListElementClick);
        addListElement(testIcon, "ÚwÙ", "98 765 434", Integer.toString(i++), onListElementClick);
        addListLine();
        addListTitle("1OwO", "1UwU");
        addListElement(testIcon, "1Hauptsitz", "98 765 434", Integer.toString(i++), onListElementClick);
        addListElement(testIcon, "ÚwÙ", "98 765 434", Integer.toString(i++), onListElementClick);
        addListLine();
        addListTitle("2OwO", "2UwU");
        addListElement(testIcon, "2Hauptsitz", "98 765 434", Integer.toString(i++), onListElementClick);
        addListElement(testIcon, "ÚwÙ", "98 765 434", Integer.toString(i++), onListElementClick);
        addListLine();
        addListTitle("2OwO", "2UwU");
        addListElement(testIcon, "2Hauptsitz", "98 765 434", Integer.toString(i++), onListElementClick);
        addListElement(testIcon, "ÚwÙ", "98 765 434", Integer.toString(i++), onListElementClick);
        addListLine();
        addListTitle("2OwO", "2UwU");
        addListElement(testIcon, "2Hauptsitz", "98 765 434", Integer.toString(i++), onListElementClick);
        addListElement(testIcon, "ÚwÙ", "98 765 434", Integer.toString(i++), onListElementClick);
        addListLine();
        addListTitle("2OwO", "2UwU");
        addListElement(testIcon, "2Hauptsitz", "98 765 434", Integer.toString(i++), onListElementClick);
        addListElement(testIcon, "ÚwÙ", "98 765 434", Integer.toString(i++), onListElementClick);
    }
}
