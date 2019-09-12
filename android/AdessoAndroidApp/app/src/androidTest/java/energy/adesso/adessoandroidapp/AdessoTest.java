package energy.adesso.adessoandroidapp;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.test.platform.app.InstrumentationRegistry;

import energy.adesso.adessoandroidapp.logic.controller.MainController;
import energy.adesso.adessoandroidapp.logic.model.exception.AdessoException;
import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;

public abstract class AdessoTest {
  protected boolean hasChildren(int id, Activity a) {
    View v = null;
    try {
      v = ((ViewGroup)a.findViewById(id)).getChildAt(0);
    } catch (Exception e) { }
    return v != null;
  }
}
