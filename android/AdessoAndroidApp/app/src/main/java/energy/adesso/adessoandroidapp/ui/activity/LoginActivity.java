package energy.adesso.adessoandroidapp.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.controller.MainController;
import energy.adesso.adessoandroidapp.logic.model.Pair;
import energy.adesso.adessoandroidapp.logic.model.exception.AdessoException;
import energy.adesso.adessoandroidapp.ui.mock.MockController;

public class LoginActivity extends AdessoActivity {
  Activity a = this;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    MainController.loadSharedPreferences(getPreferences(Context.MODE_PRIVATE));

    if (MainController.isLoggedIn()) {
      startActivity(new Intent(this, MainActivity.class).
          addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }
  }

  @Override
  protected void onResume() {
    hideLoadingPopup();
    super.onResume();
  }

  @SuppressWarnings("ALL")
  public void onLoginClick(View view) {
    final TextView numView = findViewById(R.id.number);
    final TextView passView = findViewById(R.id.pass);

    final String num = numView.getText().toString();
    final String pass = passView.getText().toString();

    numView.setText("");
    passView.setText("");

    doLoginAsync(new Pair<>(num, pass));
  }

  public void onChooseServerClick(final View view) {
    showChooseServerDialog();
  }


  boolean login(String usernumber, String password) {
    try {
      MainController.login(usernumber, password);
      return true;
    } catch (AdessoException e) {
      return false;
    }
  }

  void doLoginAsync(Pair<String, String> p) {
    showLoadingPopup();
    @SuppressLint("StaticFieldLeak") AsyncTask<Pair<String, String>, Void, Boolean> execute = new AsyncTask<Pair<String, String>, Void, Boolean>() {
      @Override
      protected Boolean doInBackground(Pair<String, String>... p) {
        for (int i = 0; i < p.length; i++) {
          if (login(p[i].first, p[i].second))
            return true;
        }
        return false;
      }

      @Override
      protected void onPostExecute(Boolean succ) {
        if (succ) {
          startActivity(new Intent(a, MainActivity.class).
              addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        } else {
          Toast.makeText(a, R.string.wrong_login, Toast.LENGTH_SHORT).show();
        }
        hideLoadingPopup();
      }
    }.execute(p);
  }
}
