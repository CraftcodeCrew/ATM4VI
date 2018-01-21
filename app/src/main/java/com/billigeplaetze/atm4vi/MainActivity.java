package com.billigeplaetze.atm4vi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;

import com.billigeplaetze.atm4vi.domain.uc.IStartUpUseCase;
import com.billigeplaetze.atm4vi.domain.uc.StartUpInteractor;
import com.billigeplaetze.atm4vi.domain.uc.StartUpRequestModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {



                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        1234);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.

        } else {
            TextureView tv = (TextureView) findViewById(R.id.textureview);
            IStartUpUseCase startUpUseCase = new StartUpInteractor();
            startUpUseCase.start(new StartUpRequestModel(this,tv,this));
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1234: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    TextureView tv = (TextureView) findViewById(R.id.textureview);
                    IStartUpUseCase startUpUseCase = new StartUpInteractor();
                    startUpUseCase.start(new StartUpRequestModel(this,tv,this));
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO Remove this
        Drawable drawable = getResources().getDrawable(R.drawable.atm4);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmap  = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        InputStream is = new ByteArrayInputStream(stream.toByteArray());

        OCRBackgroundTask task = new OCRBackgroundTask();
        task.execute(is);
    }
}
