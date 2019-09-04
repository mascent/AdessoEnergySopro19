package energy.adesso.adessoandroidapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Meter;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String n = intent.getStringExtra("number");
        Meter m = MainActivity.getMeter(n);
        ((TextView)findViewById(R.id.name)).setText(m.name);
        ((TextView)findViewById(R.id.nummer)).setText(m.meterNumber);
        ((TextView)findViewById(R.id.usage)).setText(m.getLastReading());
    }

}
