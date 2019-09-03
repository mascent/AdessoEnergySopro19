package energy.adesso.adessoandroidapp.ui.parents;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;


public abstract class ParentActivity extends AppCompatActivity {

    protected void startNewActivity(Class activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
    protected void startNewActivity(Class activity, int flags){
        Intent intent = new Intent(this, activity);
        intent.addFlags(flags);
        startActivity(intent);
    }

    protected void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),1234);
    }

    final int TAKE_PICTURE = 1;
    Uri imageUri;

    public void takePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        Uri imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    // TODO: Add photo support
}
