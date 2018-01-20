package com.billigeplaetze.atm4vi.domain.definitions;

import com.billigeplaetze.atm4vi.services.ocr.pojo.ReceivedData;

import java.io.InputStream;

/**
 * Created by djuelg on 20.01.18.
 */

public interface OCRService {

    ReceivedData receiveImageData(InputStream stream);
}
