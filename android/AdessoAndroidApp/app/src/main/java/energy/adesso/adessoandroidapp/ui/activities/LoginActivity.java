package energy.adesso.adessoandroidapp.ui.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.ui.parents.ActivityDaddy;

public class LoginActivity extends ActivityDaddy {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (false) { // TODO: Login Check
            startNewActivity(MainActivity.class);
        }
    }

    public void onLoginClick(View view) {
        // TODO: Add Login Code

        startNewActivity(MainActivity.class);
    }

    public void onForgotPasswordClick(View view) {
        // TODO: Add pass_forgot Code

        new AlertDialog.Builder(this)
                .setTitle("Owo?")
                .setMessage("Awe you suwe you want to dewete this UwU?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.println(Log.ASSERT, "","Positive!");
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(R.drawable.logo_drop_circle)
                .show();
    }
}
