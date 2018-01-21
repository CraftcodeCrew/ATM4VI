package com.billigeplaetze.atm4vi.services.photo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.TextureView;

import com.billigeplaetze.atm4vi.domain.uc.IPhotoTakenUseCase;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dannynator on 20.01.18.
 */

public class PhotoService implements com.billigeplaetze.atm4vi.domain.definitions.PhotoService, INextPhotoAvailabilityListener {

    private PhotoHelper photoHelper;
    private IPhotoTakenUseCase iPhotoTakenUseCase;

    private int mInterval = 5000; // 2 seconds by default, can be changed later
    private Handler mHandler;

    @Override
    public void startService(TextureView textureView, Context context, IPhotoTakenUseCase photoTakenUseCase) {
        this.iPhotoTakenUseCase =photoTakenUseCase;
        photoHelper = new PhotoHelper(textureView, context, this);
        mHandler = new Handler();
        mPhotoRunnable.run();
    }

    Runnable mPhotoRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                photoHelper.getPicture(); //this function can change value of mInterval.
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mPhotoRunnable, mInterval);
            }
        }
    };

    @Override
    public void nextImage(byte[] image) {
        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
        Bitmap mutableBitmap = bmp.copy(Bitmap.Config.ARGB_8888, true);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        mutableBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        InputStream is = new ByteArrayInputStream(stream.toByteArray());

        iPhotoTakenUseCase.submitPhoto(is);
    }

    @Override
    public void nextImageFaild(Exception ex) {

    }
}
