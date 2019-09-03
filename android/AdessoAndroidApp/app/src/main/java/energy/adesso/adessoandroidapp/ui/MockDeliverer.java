package energy.adesso.adessoandroidapp.ui;

import android.util.Log;

import energy.adesso.adessoandroidapp.logic.model.exception.AdessoException;

public class MockDeliverer {
    public static boolean login(String username, String password) throws AdessoException
    {
        Log.println(Log.INFO, "", "Login with " + username + ", "  + password);

        if (username.equals("1234") && password.equals("."))
            return true;
        else
            return false;
    }
}
