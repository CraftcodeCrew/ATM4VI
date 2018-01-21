package com.billigeplaetze.atm4vi.services.photo;

import android.content.Context;
import android.os.Handler;
import android.view.TextureView;

import com.billigeplaetze.atm4vi.domain.uc.IPhotoTakenUseCase;

import java.io.ByteArrayInputStream;

/**
 * Created by dannynator on 20.01.18.
 */

public class PhotoService implements com.billigeplaetze.atm4vi.domain.definitions.PhotoService, INextPhotoAvailabilityListener {

    private PhotoHelper photoHelper;
    private IPhotoTakenUseCase iPhotoTakenUseCase;

    private int mInterval = 2000; // 2 seconds by default, can be changed later
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
        ByteArrayInputStream inputStream = new ByteArrayInputStream(image);
        iPhotoTakenUseCase.submitPhoto(inputStream);
    }

    @Override
    public void nextImageFaild(Exception ex) {

    }
}
