package energy.adesso.adessoandroidapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.text.DateFormat;
import java.util.List;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.model.exception.AdessoException;
import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Meter;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Reading;
import energy.adesso.adessoandroidapp.ui.mock.MockMeter;

public class GraphActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_graph);

    // Set up toolbar
    Toolbar toolbar = findViewById(R.id.graphToolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    // Get the data
    Reading[] values = (Reading[])getIntent().getSerializableExtra("readings");
    String unit = getIntent().getStringExtra("unit");
    GraphView graph = findViewById(R.id.graphViewXML);

    // Convert to points
    DataPoint[] points = new DataPoint[values.length];
    for (int i = 0; i < points.length; i++)
      points[i] = new DataPoint(values[i].getCreatedAt().getMillis(), Integer.parseInt(values[i].getValue()));

    // Put points into Graph
    LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
    graph.removeAllSeries();
    graph.addSeries(series);
    series.setDataPointsRadius(3);
    series.resetData(points);
    graph.getGridLabelRenderer().
        setLabelFormatter(new DateAsXAxisLabelFormatter(this, DateFormat.getDateInstance()));
    series.setTitle(getString(R.string.graph_title) + " " + unit);
  }
}
