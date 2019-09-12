package energy.adesso.adessoandroidapp.ui.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import energy.adesso.adessoandroidapp.R;

public abstract class AdessoActivity extends AppCompatActivity {
  private AlertDialog loadingPopup = null;

  protected void showLoadingPopup() {
    loadingPopup = new AlertDialog.Builder(this).
        setView(getLayoutInflater().
            inflate(R.layout.loading, null)).
        setCancelable(false).
        show();
  }

  protected void hideLoadingPopup() {
    if (loadingPopup != null)
      loadingPopup.dismiss();
  }
}
