package energy.adesso.adessoandroidapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import energy.adesso.adessoandroidapp.R;

public class IssueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);
        Toolbar toolbar = findViewById(R.id.issue_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onSendClick(View view) {
        String name = ((TextView)findViewById(R.id.name)).getText().toString();
        String email = ((TextView)findViewById(R.id.email)).getText().toString();
        String subject = ((TextView)findViewById(R.id.subject)).getText().toString();
        String message = ((TextView)findViewById(R.id.message)).getText().toString();

        // TODO: Send stuff
    }
}
