package energy.adesso.adessoandroidapp.ui.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.ui.parents.ActivityDaddy;
import energy.adesso.adessoandroidapp.ui.parents.ActivityListSupport;

public class MainActivity extends ActivityListSupport {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        addListTitle("OwO", "UwU");
        addListElement(getDrawable(R.drawable.logo_drop_circle), "Hauptsitz6", "98 765 434", "12345");
        addListElement(getDrawable(R.drawable.logo_drop_circle), "ÚwÙ", "98 765 434", "12345");
        addListLine();
        addListTitle("1OwO", "1UwU");
        addListElement(getDrawable(R.drawable.logo_drop_circle), "1Hauptsitz", "98 765 434", "12345");
        addListElement(getDrawable(R.drawable.logo_drop_circle), "ÚwÙ", "98 765 434", "12345");
        addListLine();
        addListTitle("2OwO", "2UwU");
        addListElement(getDrawable(R.drawable.logo_drop_circle), "2Hauptsitz", "98 765 434", "12345");
        addListElement(getDrawable(R.drawable.logo_drop_circle), "ÚwÙ", "98 765 434", "12345");
        addListLine();
        addListTitle("2OwO", "2UwU");
        addListElement(getDrawable(R.drawable.logo_drop_circle), "2Hauptsitz", "98 765 434", "12345");
        addListElement(getDrawable(R.drawable.logo_drop_circle), "ÚwÙ", "98 765 434", "12345");
        addListLine();
        addListTitle("2OwO", "2UwU");
        addListElement(getDrawable(R.drawable.logo_drop_circle), "2Hauptsitz", "98 765 434", "12345");
        addListElement(getDrawable(R.drawable.logo_drop_circle), "ÚwÙ", "98 765 434", "12345");
        addListLine();
        addListTitle("2OwO", "2UwU");
        addListElement(getDrawable(R.drawable.logo_drop_circle), "2Hauptsitz", "98 765 434", "12345");
        addListElement(getDrawable(R.drawable.logo_drop_circle), "ÚwÙ", "98 765 434", "12345");
    }
}
