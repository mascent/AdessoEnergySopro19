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
    protected void startNewActivity(Class activity, String key, String data){
        Intent intent = new Intent(this, activity);
        intent.putExtra(key, data);
        startActivity(intent);
    }

}
