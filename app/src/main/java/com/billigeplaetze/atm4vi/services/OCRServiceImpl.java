package com.billigeplaetze.atm4vi.services;

import com.billigeplaetze.atm4vi.domain.OCRService;
import com.google.gson.Gson;
import com.microsoft.projectoxford.vision.VisionServiceClient;
import com.microsoft.projectoxford.vision.VisionServiceRestClient;
import com.microsoft.projectoxford.vision.contract.LanguageCodes;
import com.microsoft.projectoxford.vision.contract.OCR;
import com.microsoft.projectoxford.vision.rest.VisionServiceException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by djuelg on 20.01.18.
 */

public class OCRServiceImpl implements OCRService {

    @Override
    public String receiveImageData(InputStream stream) {
        VisionServiceClient client = new VisionServiceRestClient("","");
        try {
            Gson gson = new Gson();
            OCR ocr = client.recognizeText(stream, LanguageCodes.German, false);
            String result = gson.toJson(ocr);
            return result;
        } catch (VisionServiceException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
