package energy.adesso.adessoandroidapp.ui;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import energy.adesso.adessoandroidapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "OwO wat dis", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // TODO: Get Zähler here
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

    public void addListElement(Drawable icon, String place, String number, String usage)
    {
        LinearLayout childLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.list_element, null);

        ((ImageView)childLayout.getChildAt(0)).setImageDrawable(icon);

        LinearLayout childsChildLayout = ((LinearLayout) childLayout.getChildAt(1));
        ((TextView)childsChildLayout.getChildAt(0)).setText(place);
        ((TextView)childsChildLayout.getChildAt(1)).setText(number);

        ((TextView)childLayout.getChildAt(2)).setText(usage);

        ((LinearLayout)findViewById(R.id.list)).addView(childLayout);
    }

    public void addListTitle(String title, String unit)
    {
        LinearLayout childLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.list_title, null);
        ((TextView)childLayout.getChildAt(0)).setText(title);
        ((TextView)childLayout.getChildAt(1)).setText(unit);
        ((LinearLayout)findViewById(R.id.list)).addView(childLayout);
    }

    public void addListLine()
    {
        View childLayout = getLayoutInflater().inflate(R.layout.list_line, null);
        ((LinearLayout)findViewById(R.id.list)).addView(childLayout);
    }
}
