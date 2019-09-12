package energy.adesso.adessoandroidapp.ui.mock;

import android.graphics.Bitmap;
import android.util.Log;

import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

import energy.adesso.adessoandroidapp.logic.controller.MainController;
import energy.adesso.adessoandroidapp.logic.model.MeterKind;
import energy.adesso.adessoandroidapp.logic.model.Pair;
import energy.adesso.adessoandroidapp.logic.model.exception.AdessoException;
import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Issue;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Meter;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Reading;

public class MockController extends MainController {
  static DateTime time = DateTime.now();
  static Reading lastReading = new Reading(null, 98762244L, 14134L, "12345,754");


  public static DateTime getTime() {
    time = time.plusDays(7);
    return time;
  }

  public static void setServer(String toString) {

  }

  public static Pair<Meter, String> azureAnalyze(Bitmap b) throws NetworkException {
    try {
      Thread.sleep(2500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return new Pair<>(new MockMeter("Name1", "12345", MeterKind.ELECTRIC, lastReading).toMeter(), "Mocked Man");
  }

}
