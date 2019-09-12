package energy.adesso.adessoandroidapp.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.model.exception.AdessoException;
import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Meter;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Reading;
import energy.adesso.adessoandroidapp.ui.adapter.ReadingAdapter;

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
    Meter m = (Meter)getIntent().getSerializableExtra("meter");
    updateGraphAsync(m);
  }

  void updateGraphAsync(final Meter m) {
    showLoadingPopup();
    @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, List<Reading>> execute = new AsyncTask<Void, Void, List<Reading>>() {
      @Override
      protected List<Reading> doInBackground(Void... voids) {
        try {
          List<Reading> rs = m.getReadings();
          if (rs == null)
            return new ArrayList<Reading>();
          return rs;
        } catch (AdessoException e) {
          e.printStackTrace();
          return new ArrayList<Reading>();
        }
      }

      @Override
      protected void onPostExecute(List<Reading> values) {
        String unit = getIntent().getStringExtra("unit");
        GraphView graph = findViewById(R.id.graphView);

        if (values == null || values.size() == 0)
          return;

        // Convert to points
        DataPoint[] points = new DataPoint[values.size()];
        for (int i = 0; i < points.length; i++)
          points[i] = new DataPoint(values.get(i).getCreatedAt().getMillis(),
                  Integer.parseInt(values.get(i).getValue()));
        Arrays.sort(points, new Comparator<DataPoint>() {
          @Override
          public int compare(DataPoint d1, DataPoint d2) {
            return (int)(d1.getX() - d2.getX());
          }
        });

        // Put points into Graph
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        graph.removeAllSeries();
        graph.addSeries(series);
        series.resetData(points);

        series.setDataPointsRadius(80);
        graph.getGridLabelRenderer().
                setLabelFormatter(new DateAsXAxisLabelFormatter(a,
                        DateFormat.getDateInstance()));
        series.setTitle(getString(R.string.graph_title) + " " + unit);

        hideLoadingPopup();
      }
    }.execute();
  }
}
