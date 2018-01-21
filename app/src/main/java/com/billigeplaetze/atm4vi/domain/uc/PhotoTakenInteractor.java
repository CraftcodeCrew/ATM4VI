package com.billigeplaetze.atm4vi.domain.uc;

import android.content.Context;

import com.billigeplaetze.LoggerDirtySt;
import com.billigeplaetze.atm4vi.domain.ScreenRecognizer;
import com.billigeplaetze.atm4vi.domain.definitions.IScreenChangedListener;
import com.billigeplaetze.atm4vi.domain.definitions.OCRService;
import com.billigeplaetze.atm4vi.domain.definitions.Screen;
import com.billigeplaetze.atm4vi.services.ocr.pojo.ReceivedData;

import java.io.InputStream;

/**
 * Created by dannynator on 21.01.18.
 */

public class PhotoTakenInteractor implements IPhotoTakenUseCase {
    private final OCRService ocrService;
    private final Context context;
    private final IScreenChangedListener listener;

    public PhotoTakenInteractor(OCRService ocrService, Context context, IScreenChangedListener listener) {
        this.ocrService = ocrService;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void submitPhoto(InputStream photo) {
         ReceivedData receivedData = ocrService.receiveImageData(photo);
         ScreenRecognizer recognizer = new ScreenRecognizer(receivedData, listener);
         LoggerDirtySt.getInstance().appendLog("Screen Nr.:" + recognizer.recognize());

    }
}

