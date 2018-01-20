package com.billigeplaetze.atm4vi.domain;

import com.google.gson.Gson;

import java.io.InputStream;

/**
 * Created by djuelg on 20.01.18.
 */

public interface OCRService {

    String receiveImageData(InputStream stream);
}
