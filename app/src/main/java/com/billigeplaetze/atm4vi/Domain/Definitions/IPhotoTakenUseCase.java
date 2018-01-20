package com.billigeplaetze.atm4vi.Domain.Definitions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by dannynator on 20.01.18.
 */

public interface IPhotoTakenUseCase {

    void submitPhoto(ByteArrayInputStream photo);

}
