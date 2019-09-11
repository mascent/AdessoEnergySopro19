package energy.adesso.adessoandroidapp.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.controller.MainController;
import energy.adesso.adessoandroidapp.logic.model.Pair;
import energy.adesso.adessoandroidapp.logic.model.exception.AdessoException;
import energy.adesso.adessoandroidapp.ui.mock.MockController;

public class LoginActivity extends AppCompatActivity {
    Activity a = this;
    AlertDialog loadingPopup = null;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        MainController.loadSharedPreferences(getPreferences(Context.MODE_PRIVATE));

        if (MockController.isLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class).
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        }
    }
    @Override protected void onResume() {
        if (loadingPopup != null)
            loadingPopup.dismiss();
        super.onResume();
    }
     public void onLoginClick(View view) {
        final TextView numView = findViewById(R.id.number);
        final TextView passView = findViewById(R.id.pass);

        final String num = numView.getText().toString();
        final String pass = passView.getText().toString();

        numView.setText("");
        passView.setText("");

        showLoadingPopup();
        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... p) {
                for (int i = 0; i < p.length; i++) {
                    String[] split = p[i].split("\n");
                    if (login(split[0], split[1]))
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
                loadingPopup.dismiss();
            }
        }.execute(num + "\n" + pass);
    }
    public void onForgotPasswordClick(final View view) {
        // TODO: Add pass_forgot
        Toast.makeText(this, R.string.not_implemented_message, Toast.LENGTH_SHORT).show();
    }

    boolean login(String username, String password) {
        try {
            MockController.login(username, password);
            return true;
        } catch (AdessoException e) {
            return false;
        }
    }
    void showLoadingPopup() {
        loadingPopup = new AlertDialog.Builder(this).
            setView(getLayoutInflater().
                inflate(R.layout.loading, null)).
            setCancelable(false).
            show();
    }
}
