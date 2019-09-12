package energy.adesso.adessoandroidapp.ui.activity;

import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.DateFormat;
import java.util.Objects;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Reading;

public class GraphActivity extends AdessoActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_graph);

    // Set up toolbar
    Toolbar toolbar = findViewById(R.id.graphToolbar);
    setSupportActionBar(toolbar);
    // The detailActivity throws away all
    // its data on the arrow click for some reason, but not onBackClick so the arrow has to be hidden for now
    //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    // Get the data
    Reading[] values = (Reading[]) getIntent().getSerializableExtra("readings");
    String unit = getIntent().getStringExtra("unit");
    GraphView graph = findViewById(R.id.graphView);

    // Convert to points
    assert values != null;
    DataPoint[] points = new DataPoint[values.length];
    for (int i = 0; i < points.length; i++)
      points[i] = new DataPoint(values[i].getCreatedAt().getMillis(),
          Integer.parseInt(values[i].getValue()));

    // Put points into Graph
    LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
    graph.removeAllSeries();
    graph.addSeries(series);
    series.resetData(points);

    series.setDataPointsRadius(8);
    graph.getGridLabelRenderer().
        setLabelFormatter(new DateAsXAxisLabelFormatter(this,
            DateFormat.getDateInstance()));
    series.setTitle(getString(R.string.graph_title) + " " + unit);
  }
}
