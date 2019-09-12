package energy.adesso.adessoandroidapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.model.exception.AdessoException;
import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Issue;
import energy.adesso.adessoandroidapp.ui.mock.MockIssue;

public class IssueActivity extends AdessoActivity {
  Activity a = this;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_issue);
    Toolbar toolbar = findViewById(R.id.issue_toolbar);
    setSupportActionBar(toolbar);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  public void onSendClick(View view) {
    Issue i = new Issue(null);

    String name = ((TextView) findViewById(R.id.name)).getText().toString();
    String email = ((TextView) findViewById(R.id.email)).getText().toString();
    String subject = ((TextView) findViewById(R.id.subject)).getText().toString();
    String message = ((TextView) findViewById(R.id.message)).getText().toString();

    i.setName(name);
    i.setEmail(email);
    i.setSubject(subject);
    i.setMessage(message);

    sendIssueAsync(i);
  }

  void sendIssueAsync(Issue i) {
    showLoadingPopup();
    @SuppressLint("StaticFieldLeak") AsyncTask<Issue, Void, AdessoException> execute = new AsyncTask<Issue, Void, AdessoException>() {
      @Override
      protected AdessoException doInBackground(Issue... issues) {
        for (Issue i : issues) {
          try {
            i.send();
          } catch (AdessoException e) {
            return e;
          }
        }
        return null;
      }

      @Override
      protected void onPostExecute(AdessoException e) {
        if (e != null) {
          e.printStackTrace();
          Toast.makeText(a, R.string.generic_error_message,
              Toast.LENGTH_SHORT).show();
        } else {
          Toast.makeText(a, R.string.issue_send_successfully,
              Toast.LENGTH_SHORT).show();
        }

        hideLoadingPopup();
        try {
          a.finish();
        } catch (Throwable t) { }
      }
    }.execute(i);
  }
}
