package com.billigeplaetze.atm4vi.domain.uc;

import android.content.Context;

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
    public PhotoTakenInteractor(OCRService ocrService, Context context) {
        this.ocrService = ocrService;
        this.context = context;
    }

    @Override
    public void submitPhoto(InputStream photo) {
         ReceivedData receivedData = ocrService.receiveImageData(photo);
         ScreenRecognizer recognizer = new ScreenRecognizer(receivedData, new IScreenChangedListener() {
             @Override
             public void onScreenChanged(Screen screen) {
                 ScreenRecognizer.appendLog("Changed Screens to: " + screen);
             }
         });
         ScreenRecognizer.appendLog("Screen Nr.:" + recognizer.recognize());

    }
}

