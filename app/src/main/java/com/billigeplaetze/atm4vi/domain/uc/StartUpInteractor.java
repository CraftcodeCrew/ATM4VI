package com.billigeplaetze.atm4vi.domain.uc;

import com.billigeplaetze.atm4vi.domain.definitions.IScreenChangedListener;
import com.billigeplaetze.atm4vi.domain.definitions.ITTSEngine;
import com.billigeplaetze.atm4vi.domain.definitions.OCRService;
import com.billigeplaetze.atm4vi.domain.definitions.PhotoService;
import com.billigeplaetze.atm4vi.services.ocr.OCRServiceImpl;
import com.billigeplaetze.atm4vi.services.ui.TTSService;

/**
 * Created by dannynator on 21.01.18.
 */

public class StartUpInteractor implements IStartUpUseCase {

    private IPhotoTakenUseCase photoTakenInteractor;
    private OCRService ocrService;
    private IScreenChangedListener listener;
    private ITTSEngine ttsEngine;
    PhotoService photoService;

    @Override
    public void start(StartUpRequestModel requestModel) {
        this.ocrService = new OCRServiceImpl();
        this.ttsEngine = new TTSService();
        ttsEngine.initialize(requestModel.getContext());
        this.listener = new ScreenChangedInteractor(ttsEngine, requestModel.getContext());
        this.photoTakenInteractor = new PhotoTakenInteractor(ocrService, requestModel.getContext(), listener);
        startServices(requestModel);
    }

    private void startServices(StartUpRequestModel requestModel) {
        this.photoService = new com.billigeplaetze.atm4vi.services.photo.PhotoService();
        this.photoService.startService(requestModel.getTextureView(),requestModel.getContext(),photoTakenInteractor);
    }
}
