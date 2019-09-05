package energy.adesso.adessoandroidapp.ui;

import android.util.Log;

import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

import energy.adesso.adessoandroidapp.logic.model.MeterKind;
import energy.adesso.adessoandroidapp.logic.model.exception.AdessoException;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Meter;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Reading;

public class MockDeliverer {
    public static boolean login(String username, String password) throws AdessoException
    {
        Log.println(Log.INFO, "", "Login with " + username + ", "  + password);

        if (username.equals("1234") && password.equals("."))
            return true;
        else
            return false;
    }

    public static List<Meter> getMockMeterList()
    {
        DateTime time = DateTime.now();
        String lastReading = "12345,987";
        return Arrays.asList(new Meter[] {
                new Meter("id",time, time, time,
                        "Hauptsitz", "98 762 244", MeterKind.ELECTRIC, "einowner", lastReading),
                new Meter("id",time, time, time,
                        "Hauptsitz", "98 762 245", MeterKind.GAS, "einowner", lastReading),
                new Meter("id",time, time, time,
                        "Hauptsitz", "98 762 246", MeterKind.WATER, "einowner", lastReading),
                new Meter("id",time, time, time,
                        "Hauptsitz2", "98 762 247", MeterKind.ELECTRIC, "einowner", lastReading),
                new Meter("id",time, time, time,
                        "Hauptsitz2", "98 762 248", MeterKind.GAS, "einowner", lastReading),
                new Meter("id",time, time, time,
                        "Hauptsitz2", "98 762 249", MeterKind.WATER, "einowner", lastReading),
        });
    }
}
