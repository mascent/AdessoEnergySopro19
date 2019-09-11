package energy.adesso.adessoandroidapp.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.FileNotFoundException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.model.exception.AdessoException;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Meter;
import energy.adesso.adessoandroidapp.logic.model.Pair;
import energy.adesso.adessoandroidapp.ui.adapter.MeterAdapter;
import energy.adesso.adessoandroidapp.ui.mock.MockController;

public class MainActivity extends AppCompatActivity {
    final Activity a = this;
    final int CAMERA_REQUEST_IMAGE_BITMAP = 1;
    final int CAMERA_REQUEST_IMAGE_URI = 2;
    final int GALLERY_REQUEST_IMAGE_BITMAP = 10;
    List<Meter> meters;
    List<Meter> electricMeters;
    List<Meter> gasMeters;
    List<Meter> waterMeters;
    MeterAdapter listAdapter;
    Drawable[] meterIcons;

    // Events
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        meterIcons = new Drawable[] {
                getDrawable(R.drawable.icon_electricity),
                getDrawable(R.drawable.icon_gas),
                getDrawable(R.drawable.icon_water) };

        try {
            meters = MockController.getOverview();
            showMeters(meters);
        } catch (Exception e) {
            Toast.makeText(this, "Couldn't get meters!", Toast.LENGTH_LONG);
        }
    }
    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.println(Log.INFO, "", "ActivityResult: " +
                Integer.toString(requestCode)  + " " + Integer.toString(resultCode) + " ");

        if (requestCode == CAMERA_REQUEST_IMAGE_BITMAP && resultCode == RESULT_OK) {
            try {
                Bundle extras = data.getExtras();
                onImageReceived((Bitmap)extras.get("data"));
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, R.string.generic_error_message, Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == GALLERY_REQUEST_IMAGE_BITMAP && resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                onImageReceived(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, R.string.generic_error_message, Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override public void onBackPressed() {
        showLogoutMenu();
    }
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.issue:
                startActivity(new Intent(a, IssueActivity.class));
                return true;
            case R.id.logout:
                showLogoutMenu();
                return true;
            case R.id.choose_server:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.menu_main_choose_server_button);

                // Set up textbox
                final EditText input = new EditText(this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setPadding(24,24,24,24);
                input.layout(24,24,24,24);
                builder.setView(input);

                // Set up events
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            MockController.setServer(input.getText().toString());
                        } catch (IllegalFormatException e) {
                            Toast.makeText(a, R.string.generic_error_message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override public void onTopResumedActivityChanged(boolean isTopResumedActivity) {
        if (isTopResumedActivity)
            showMeters(meters);
    }
    public void onFABClick(View view) {
        new AlertDialog.Builder(this)
            .setTitle(R.string.new_input_title)
            .setMessage(R.string.new_input_messsage)
            .setCancelable(true)
            .setPositiveButton(R.string.take_photo, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) { onPhotoButtonClick();
                }
            })
            .setNegativeButton(R.string.select_from_gallery, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) { onGalleryButtonClick();
                }
            })
            .setIcon(R.drawable.logo_drop)
            .show();
    }
    void onPhotoButtonClick() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAMERA_REQUEST_IMAGE_BITMAP);
        }
    }
    void onGalleryButtonClick() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),
            GALLERY_REQUEST_IMAGE_BITMAP);
    }
    void onImageReceived(Bitmap b) {
        try {
            LinearLayout l = (LinearLayout)getLayoutInflater().inflate(R.layout.dialog_reading_check,null);
            Pair<Meter, String> p = MockController.azureAnalyze(b);

            // TODO: remind richard that azureAnalyze should return the meter number and not the mid
            if (p.first == null)
                throw new AdessoException();

            ((TextView)l.findViewById(R.id.number)).setText(p.first.getMeterNumber());
            ((TextView)l.findViewById(R.id.usage)).setText(p.second);

            new AlertDialog.Builder(this)
                .setTitle(R.string.check_image)
                .setCancelable(true)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { }
                })
                .setNegativeButton(R.string.cancel, null)
                .setIcon(R.drawable.logo_drop)
                .setView(l)
                .show();
        } catch (AdessoException e) {
            Toast.makeText(this, R.string.generic_error_message, Toast.LENGTH_SHORT).show();
        }
    }
    final AdapterView.OnItemClickListener onAdapterElecElementClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            startActivity(new Intent(a, DetailActivity.class).
                    putExtra("meter", electricMeters.get(position)));
        }
    };
    final AdapterView.OnItemClickListener onAdapterGasElementClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            startActivity(new Intent(a, DetailActivity.class).
                putExtra("meter", gasMeters.get(position)));
        }
    };
    final AdapterView.OnItemClickListener onAdapterWaterElementClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            startActivity(new Intent(a, DetailActivity.class).
                putExtra("meter", waterMeters.get(position)));
        }
    };

    void showLogoutMenu() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.logout_title)
                .setMessage(R.string.logout_text)
                .setCancelable(true)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            MockController.logOut();
                        } catch (AdessoException e) { }
                        finish();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { }
                })
                .setIcon(R.drawable.logo_drop)
                .show();
    }
    void showMeters(List<Meter> meters) {
        electricMeters = new ArrayList<Meter>();
        gasMeters = new ArrayList<Meter>();
        waterMeters = new ArrayList<Meter>();

        for (Meter m : meters)
        {
            if (m.getType().toUpperCase().equals("ELECTRIC"))
                electricMeters.add(m);
            else if (m.getType().toUpperCase().equals("GAS"))
                gasMeters.add(m);
            else if (m.getType().toUpperCase().equals("WATER"))
                waterMeters.add(m);
        }

        // Electricity
        listAdapter = new MeterAdapter(this.getBaseContext(), electricMeters, meterIcons);
        ListView elecList = findViewById(R.id.elecList);
        elecList.setAdapter(listAdapter);
        elecList.setOnItemClickListener(onAdapterElecElementClick);

        // Gas
        listAdapter = new MeterAdapter(this.getBaseContext(), gasMeters, meterIcons);
        ListView gasList = findViewById(R.id.GasList);
        gasList.setAdapter(listAdapter);
        gasList.setOnItemClickListener(onAdapterGasElementClick);

        // Water
        listAdapter = new MeterAdapter(this.getBaseContext(), waterMeters, meterIcons);
        ListView waterList = findViewById(R.id.WaterList);
        waterList.setAdapter(listAdapter);
        waterList.setOnItemClickListener(onAdapterWaterElementClick);
    }
}