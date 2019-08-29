package energy.adesso.adessoandroidapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButton2Click(View view)
    {
        AlertDialog.Builder dia = new AlertDialog.Builder(this);
        dia.setTitle("OwO wat dis");

        EditText diaText = new EditText(this);
        diaText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        dia.setView(diaText);

        dia.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.println(Log.INFO, "", "OwO wat dis!!!!");
            }
        });

        dia.setNegativeButton("Not OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.println(Log.INFO, "", "UwU");
            }
        });

        dia.show();
    }
}
