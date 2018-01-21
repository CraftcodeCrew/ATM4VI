package com.billigeplaetze.atm4vi.domain.uc;

import android.util.Log;

import com.billigeplaetze.atm4vi.domain.definitions.OCRService;
import com.billigeplaetze.atm4vi.services.ocr.pojo.ReceivedData;

import java.io.ByteArrayInputStream;

/**
 * Created by dannynator on 21.01.18.
 */

public class PhotoTakenInteractor implements IPhotoTakenUseCase {
    private final OCRService ocrService;

    public PhotoTakenInteractor(OCRService ocrService) {
        this.ocrService = ocrService;
    }

    @Override
    public void submitPhoto(ByteArrayInputStream photo) {
        ReceivedData receivedData = ocrService.receiveImageData(photo);
    }
}
