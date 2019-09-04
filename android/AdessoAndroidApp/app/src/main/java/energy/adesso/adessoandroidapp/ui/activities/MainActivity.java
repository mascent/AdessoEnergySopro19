package energy.adesso.adessoandroidapp.ui.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.List;

import energy.adesso.adessoandroidapp.R;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Meter;
import energy.adesso.adessoandroidapp.ui.MockDeliverer;
import energy.adesso.adessoandroidapp.ui.parents.ListActivity;

public class MainActivity extends ListActivity {

    final Activity a = this;
    static List<Meter> meters;
    final int CAMERA_REQUEST_IMAGE_BITMAP = 1;
    final int CAMERA_REQUEST_IMAGE_URI = 2;
    final int GALLERY_REQUEST_IMAGE_BITMAP = 10;

    // Events
    final View.OnClickListener onListElementClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startNewActivity(DetailActivity.class, "number", getListElementNumber(view)); }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            // TODO: Get actual Meters
            meters = MockDeliverer.getMockMeterList();
            showMeters(meters);
        } catch (Exception e) {
            Toast.makeText(this, "Couldn't get meters!", Toast.LENGTH_LONG);
        }
    }
    public void onFABClick(View view) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.new_input_title)
                .setMessage(R.string.new_input_messsage)
                .setCancelable(true)
                .setPositiveButton(R.string.take_photo, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { takePhoto();
                    }
                })
                .setNegativeButton(R.string.select_from_gallery, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { openGallery();
                    }
                })
                .setIcon(R.drawable.logo_drop)
                .show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.println(Log.INFO, "", "ActivityResult: " +
                Integer.toString(requestCode)  + " " + Integer.toString(resultCode) + " ");

        if (requestCode == CAMERA_REQUEST_IMAGE_BITMAP && resultCode == RESULT_OK) {
            try {
                Bundle extras = data.getExtras();
                checkImage((Bitmap)extras.get("data"));
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, R.string.generic_error_message, Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == GALLERY_REQUEST_IMAGE_BITMAP && resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                checkImage(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, R.string.generic_error_message, Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.logout_title)
                .setMessage(R.string.logout_text)
                .setCancelable(true)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { // TODO: Logout?
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { }
                })
                .setIcon(R.drawable.logo_drop)
                .show();
    }

    // image
    void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAMERA_REQUEST_IMAGE_BITMAP);
        }
    }
    void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                GALLERY_REQUEST_IMAGE_BITMAP);
    }
    void checkImage(Bitmap b) {
        LinearLayout l = (LinearLayout)getLayoutInflater().inflate(R.layout.reading_check_layout,null);
        // TODO: Get Image data
        ((TextView)l.findViewById(R.id.number)).setText(R.string.not_implemented_message);
        ((TextView)l.findViewById(R.id.usage)).setText(R.string.not_implemented_message);

        new AlertDialog.Builder(this)
                .setTitle(R.string.check_image)
                .setCancelable(true)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: Send Image
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .setIcon(R.drawable.logo_drop)
                .setView(l)
                .show();
    }

    // list
    void buildTestList() {
        int i = 12345;
        Drawable testIcon = testIcon = getDrawable(R.drawable.logo_drop_circle);

        addListTitle("Title1", "Unit1");
        addListElement(testIcon, "Hauptsitz6", "98 765 434", Integer.toString(i++), onListElementClick);
        addListElement(testIcon, "element2", "98 765 434", Integer.toString(i++), onListElementClick);
        addListLine();
        addListTitle("Title2", "Unit2");
        addListElement(testIcon, "1Hauptsitz", "98 765 434", Integer.toString(i++), onListElementClick);
        addListElement(testIcon, "element2", "98 765 434", Integer.toString(i++), onListElementClick);
        addListLine();
        addListTitle("Title3", "Unit3");
        addListElement(testIcon, "2Hauptsitz", "98 765 434", Integer.toString(i++), onListElementClick);
        addListElement(testIcon, "element2", "98 765 434", Integer.toString(i++), onListElementClick);
        addListLine();
        addListTitle("Title4", "Unit4");
        addListElement(testIcon, "2Hauptsitz", "98 765 434", Integer.toString(i++), onListElementClick);
        addListElement(testIcon, "element2", "98 765 434", Integer.toString(i++), onListElementClick);
        addListLine();
        addListTitle("Title5", "Unit5");
        addListElement(testIcon, "2Hauptsitz", "98 765 434", Integer.toString(i++), onListElementClick);
        addListElement(testIcon, "element2", "98 765 434", Integer.toString(i++), onListElementClick);
        addListLine();
        addListTitle("Title6", "Unit6");
        addListElement(testIcon, "2Hauptsitz", "98 765 434", Integer.toString(i++), onListElementClick);
        addListElement(testIcon, "element2", "98 765 434", Integer.toString(i++), onListElementClick);
    }
    void showMeters(List<Meter> meters) {
        clearList();

        List<Meter> electricMeters = new ArrayList<Meter>();
        List<Meter> gasMeters = new ArrayList<Meter>();
        List<Meter> waterMeters = new ArrayList<Meter>();

        for (Meter m : meters)
        {
            if (m.getType().toUpperCase().equals("ELECTRIC"))
                electricMeters.add(m);
            else if (m.getType().toUpperCase().equals("GAS"))
                gasMeters.add(m);
            else if (m.getType().toUpperCase().equals("WATER"))
                waterMeters.add(m);
        }

        addListTitle("Strom", "kWh");
        for (Meter m : electricMeters)
            addListElement(getDrawable(R.drawable.icon_electricity), m.getName(), m.getMeterNumber(), m.getLastReading(), onListElementClick);
        addListLine();
        addListTitle("Gas", "m³");
        for (Meter m : gasMeters)
            addListElement(getDrawable(R.drawable.icon_gas), m.getName(), m.getMeterNumber(), m.getLastReading(), onListElementClick);
        addListLine();
        addListTitle("Wasser", "m³");
        for (Meter m : waterMeters)
            addListElement(getDrawable(R.drawable.icon_water), m.getName(), m.getMeterNumber(), m.getLastReading(), onListElementClick);

        Log.println(Log.INFO, "", "Added list elements");
    }
    public static Meter getMeter(String number) {
        for (Meter m : meters)
            if (m.meterNumber.equals(number))
                return m;
        return null;
    }
}