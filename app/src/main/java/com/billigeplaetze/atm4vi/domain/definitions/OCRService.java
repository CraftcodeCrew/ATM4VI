package com.billigeplaetze.atm4vi.domain.definitions;

import java.io.InputStream;

/**
 * Created by djuelg on 20.01.18.
 */

public interface OCRService {

    String receiveImageData(InputStream stream);
}