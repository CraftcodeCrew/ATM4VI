package com.billigeplaetze.atm4vi.domain.uc;

import java.io.InputStream;

/**
 * Created by dannynator on 20.01.18.
 */

public interface IPhotoTakenUseCase {

    void submitPhoto(InputStream photo);

}
