package com.billigeplaetze.atm4vi.services;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.billigeplaetze.atm4vi.R;
import com.billigeplaetze.atm4vi.domain.OCRService;
import com.google.gson.Gson;
import com.microsoft.projectoxford.vision.VisionServiceClient;
import com.microsoft.projectoxford.vision.VisionServiceRestClient;
import com.microsoft.projectoxford.vision.contract.LanguageCodes;
import com.microsoft.projectoxford.vision.contract.Line;
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
        VisionServiceClient client = new VisionServiceRestClient(ApiSecrets.apiKey,ApiSecrets.apiRoot);

        try {
            Gson gson = new Gson();
            OCR ocr = client.recognizeText(stream, LanguageCodes.German, true);
            return gson.toJson(ocr);
        } catch (VisionServiceException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static class OCRBackgroundTask extends AsyncTask<InputStream, Void, String> {

        @Override
        protected String doInBackground(InputStream... inputStreams) {
            OCRService ocrService = new OCRServiceImpl();
            return ocrService.receiveImageData(inputStreams[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO work
            Log.d("Test",result);

        }
    }

}


