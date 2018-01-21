package com.billigeplaetze.atm4vi.domain.uc;

import com.billigeplaetze.atm4vi.domain.definitions.OCRService;
import com.billigeplaetze.atm4vi.domain.definitions.PhotoService;
import com.billigeplaetze.atm4vi.services.ocr.OCRServiceImpl;

/**
 * Created by dannynator on 21.01.18.
 */

public class StartUpInteractor implements IStartUpUseCase {

    private IPhotoTakenUseCase photoTakenInteractor;
    private OCRService ocrService;
    PhotoService photoService;

    @Override
    public void start(StartUpRequestModel requestModel) {
        this.ocrService = new OCRServiceImpl();
        this.photoTakenInteractor = new PhotoTakenInteractor(ocrService);
        startServices(requestModel);
    }

    private void startServices(StartUpRequestModel requestModel) {
        this.photoService = new com.billigeplaetze.atm4vi.services.photo.PhotoService();
        this.photoService.startService(requestModel.getTextureView(),requestModel.getContext(),photoTakenInteractor);
    }
}
