package energy.adesso.adessoandroidapp.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.controller.MainController;
import energy.adesso.adessoandroidapp.logic.model.exception.AdessoException;
import energy.adesso.adessoandroidapp.ui.mock.MockController;

public class LoginActivity extends AppCompatActivity {
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
        TextView num = findViewById(R.id.number);
        TextView pass = findViewById(R.id.pass);

        showLoadingPopup();
        if (login(num.getText().toString(), pass.getText().toString())) {
            startActivity(new Intent(this, MainActivity.class).
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        } else {
            Toast.makeText(this, R.string.wrong_login, Toast.LENGTH_SHORT).show();
        }

        num.setText("");
        pass.setText("");
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
