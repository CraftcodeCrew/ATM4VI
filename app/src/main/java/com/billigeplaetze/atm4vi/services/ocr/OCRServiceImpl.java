package com.billigeplaetze.atm4vi.services.ocr;

import com.billigeplaetze.atm4vi.domain.definitions.OCRService;
import com.billigeplaetze.atm4vi.services.ocr.pojo.ReceivedData;
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
    public ReceivedData receiveImageData(InputStream stream) {
        VisionServiceClient client = new VisionServiceRestClient(ApiSecrets.apiKey,ApiSecrets.apiRoot);

        try {
            Gson gson = new Gson();
            OCR ocr = client.recognizeText(stream, LanguageCodes.German, true);
            return new Gson().fromJson(gson.toJson(ocr), ReceivedData.class);
        } catch (VisionServiceException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}


