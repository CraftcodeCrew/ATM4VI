package com.billigeplaetze.atm4vi.services.photo;

import android.content.Context;
import android.os.Handler;
import android.view.TextureView;

import com.billigeplaetze.atm4vi.domain.definitions.IPhotoTakenUseCase;

import java.io.ByteArrayInputStream;

/**
 * Created by dannynator on 20.01.18.
 */

public class PhotoService implements com.billigeplaetze.atm4vi.domain.definitions.PhotoService, INextPhotoAvailabilityListener {

    private PhotoHelper photoHelper;
    private IPhotoTakenUseCase iPhotoTakenUseCase;

    @Override
    public void startService(TextureView textureView, Context context, IPhotoTakenUseCase photoTakenUseCase) {
        this.iPhotoTakenUseCase =photoTakenUseCase;
        photoHelper = new PhotoHelper(textureView, context, this);
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                photoHelper.getPicture();
            }
        },2000);
    }

    @Override
    public void nextImage(byte[] image) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(image);
        iPhotoTakenUseCase.submitPhoto(inputStream);
    }

    @Override
    public void nextImageFaild(Exception ex) {

    }
}
