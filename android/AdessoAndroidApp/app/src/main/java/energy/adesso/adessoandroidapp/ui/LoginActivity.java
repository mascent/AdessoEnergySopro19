package energy.adesso.adessoandroidapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import energy.adesso.adessoandroidapp.R;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (false) { // TODO: Login Check
            startNewActivity(MainActivity.class);
        }

        // Prevent the background texture from getting recycled
        //((ImageView)findViewById(R.id.background)).setImageDrawable(getDrawable(R.drawable.topography));
    }

    public void onLoginClick(View view) {
        // TODO: Add Login Code

        startNewActivity(MainActivity.class);
    }

    public void onForgotPasswordClick(View view) {
        // TODO: Add pass_forgot Code
    }

    void startNewActivity(Class activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}
