package energy.adesso.adessoandroidapp.ui.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

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

    public void onForgotPasswordClick(final View view) {
        // TODO: Add pass_forgot Code
        final Activity t = this;
        new AlertDialog.Builder(this)
                .setTitle("Owo?")
                .setMessage("Awe you suwe you want to dewete this UwU?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(t, "OwO", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(R.drawable.logo_drop_circle)
                .show();
    }
}
