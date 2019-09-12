package energy.adesso.adessoandroidapp.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.IllegalFormatException;

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
    @SuppressWarnings("ALL") public void onLoginClick(View view) {
        final TextView numView = findViewById(R.id.number);
        final TextView passView = findViewById(R.id.pass);

        final String num = numView.getText().toString();
        final String pass = passView.getText().toString();

        numView.setText("");
        passView.setText("");

        showLoadingPopup();
        AsyncTask<Pair<String, String>, Void, Boolean> execute = new AsyncTask<Pair<String, String>, Void, Boolean>() {
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
                loadingPopup.dismiss();
            }
        }.execute(new Pair<>(num, pass));
    }
    public void onChooseServerClick(final View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.menu_main_choose_server_button);

        // Set up textbox
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setPadding(24,24,24,24);
        input.layout(24,24,24,24);
        builder.setView(input);

        // Set up events
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    MockController.setServer(input.getText().toString());
                } catch (IllegalFormatException e) {
                    Toast.makeText(a, R.string.generic_error_message, Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
        
    }

    boolean login(String usernumber, String password) {
        try {
            MockController.login(usernumber, password);
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
