package com.billigeplaetze.atm4vi.services.ocr;

import android.os.AsyncTask;

import com.billigeplaetze.atm4vi.domain.ScreenRecognizer;
import com.billigeplaetze.atm4vi.domain.definitions.IScreenChangedListener;
import com.billigeplaetze.atm4vi.domain.definitions.OCRService;
import com.billigeplaetze.atm4vi.domain.definitions.Screen;
import com.billigeplaetze.atm4vi.services.ocr.pojo.ReceivedData;

import java.io.InputStream;

/**
 * Created by djuelg on 20.01.18.
 */

public class OCRBackgroundTask extends AsyncTask<InputStream, Void, ReceivedData> {

    @Override
    protected ReceivedData doInBackground(InputStream... inputStreams) {
        OCRService ocrService = new OCRServiceImpl();
        return ocrService.receiveImageData(inputStreams[0]);
    }

    @Override
    protected void onPostExecute(ReceivedData result) {
        // TODO work

        ScreenRecognizer recognizer = new ScreenRecognizer(result, new IScreenChangedListener() {
            @Override
            public void onScreenChanged(Screen screen) {

            }
        });
        ScreenRecognizer.appendLog("Screen Nr.:" + recognizer.recognize());
    }
}