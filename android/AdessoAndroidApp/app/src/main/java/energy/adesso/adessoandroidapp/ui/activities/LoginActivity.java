package energy.adesso.adessoandroidapp.ui.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.controller.MainController;
import energy.adesso.adessoandroidapp.logic.model.exception.AdessoException;
import energy.adesso.adessoandroidapp.ui.parents.ActivityParent;

public class LoginActivity extends ActivityParent {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (false) { // TODO: Login Check
            startNewActivity(MainActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
    }

    public void onLoginClick(View view) {
        try {
            if (MainController.getInstance().
                    login(((TextView)findViewById(R.id.login)).getText().toString(),
                            ((TextView)findViewById(R.id.pass)).getText().toString()))
                startNewActivity(MainActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TASK);
        } catch (AdessoException e)  { }
    }

    public void onForgotPasswordClick(final View view) {
        // TODO: Add pass_forgot Code
        final Activity t = this;
        new AlertDialog.Builder(this)
                .setTitle("OwO?")
                .setMessage("Did you forget your password?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(t, R.string.not_implemented_message, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(R.drawable.logo_drop_circle)
                .show();
    }
}
