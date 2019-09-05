package energy.adesso.adessoandroidapp.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.controller.MainController;
import energy.adesso.adessoandroidapp.logic.model.exception.AdessoException;
import energy.adesso.adessoandroidapp.ui.MockDeliverer;
import energy.adesso.adessoandroidapp.ui.parents.ParentActivity;

public class LoginActivity extends ParentActivity {
    final Activity a = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        MainController.getInstance().init(getPreferences(Context.MODE_PRIVATE));
        String s =MainController.getInstance().doStuff();
        Log.println(Log.INFO,"gutentag",s);

        if (false) { // TODO: Login Check
            startNewActivity(MainActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
    }

    public void onLoginClick(View view) {
        TextView num = (TextView)findViewById(R.id.number);
        TextView pass = (TextView)findViewById(R.id.pass);

        try {
            if (MockDeliverer.login(num.getText().toString(), pass.getText().toString())){
                startNewActivity(MainActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TASK);
            } else {
                Toast.makeText(a, R.string.wrong_login, Toast.LENGTH_SHORT).show();
            }
        } catch (AdessoException e)  { }

        num.setText("");
        pass.setText("");
    }

    public void onForgotPasswordClick(final View view) {
        // TODO: Add pass_forgot Code
        Toast.makeText(a, R.string.not_implemented_message, Toast.LENGTH_SHORT).show();
    }
}
