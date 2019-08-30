package energy.adesso.adessoandroidapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.controller.MainController;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //((Button)findViewById(R.id.floatingActionButton)).setText(MainController.getInstance().doStuff());
    }

    TextView CreateTextview(String text)
    {
        TextView t = new TextView(this);
        t.setText(text);
        return t;
    }

    public void onButtonClick(View view)
    {
        View childLayout = getLayoutInflater().inflate(R.layout.list_element, null);
        ((LinearLayout)findViewById(R.id.lay)).addView(childLayout);
    }
}
